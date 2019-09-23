package com.spring.server;

import com.google.common.collect.Maps;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.resolver.NoopAddressResolverGroup;
import io.netty.util.internal.StringUtil;
import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.SSLException;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class RangeDownload {

    /**
     * 待下载的链接
     */
    private static String MY_URL = "https://officecdn-microsoft-com.akamaized.net/pr/C1297A47-86C4-4C1F-97FA-950631F94777/MacAutoupdate/Microsoft_Office_16.29.19090802_Installer.pkg";

    public static Pattern pattern = Pattern.compile("^(?:https?://)?(?<host>[^:]*)(?::(?<port>\\d+))?(/.*)?$");

    /**
     * netty 客户端请求处理器
     */
    private transient volatile NioEventLoopGroup loopGroup;

    /**
     * 文件存储位置
     */
    private static String STORE_PATH = "/Users/jiahangchun/Desktop/test";

    private static SslContext sslContext;

    /**
     * ssl
     *
     * @return
     * @throws SSLException
     */
    public static SslContext getSslContext() throws SSLException {
        if (sslContext == null) {
            synchronized (RangeDownload.class) {
                if (sslContext == null) {
                    sslContext = SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build();
                }
            }
        }
        return sslContext;
    }


    public void downLoad() throws MalformedURLException, InterruptedException {
        //文件存储位置更新
        genTargetFile();

        //生成配置信息
        Map<String, Object> config = genUrl(MY_URL);
        Integer port = (Integer) config.get("port");
        String host = "officecdn-microsoft-com.akamaized.net";
        Boolean isSsl = (Boolean) config.get("isSsl");

        //1.判断请求的url是否是chunked编码

        //2.目前先是chunked方式（非chunked方式暂时先放放）
        loopGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(loopGroup)
                .handler((new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        //代理设置
                        //SSL设置
//                        if (isSsl) {
//                            channel.pipeline().addLast(RangeDownload.getSslContext().newHandler(channel.alloc(),host, port));
//                        }
                        channel.pipeline().addLast("downTimeout", new ReadTimeoutHandler(10000 * 60));
                        channel.pipeline().addLast("httpCodec", new HttpClientCodec());
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            private SeekableByteChannel fileChannel;

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                try {
                                    if (msg instanceof HttpContent) {
                                        log.info("write file");
                                        //写文件
                                        HttpContent httpContent = (HttpContent) msg;
                                        ByteBuf byteBuf = httpContent.content();
                                        int size = byteBuf.readableBytes();
                                        fileChannel.write(byteBuf.nioBuffer());
                                    } else {
                                        log.info("create file");
                                        //FIXME 为什么是这里进行初始化的？
                                        HttpResponse httpResponse = (HttpResponse) msg;
                                        Integer responseCode = httpResponse.status().code();
                                        if (responseCode < 200 || responseCode >= 300) {
                                            throw new RuntimeException(" status error");
                                        }
                                        fileChannel = Files.newByteChannel(Paths.get(STORE_PATH), StandardOpenOption.WRITE);
//                                        if (response.isSupportRange()) {
//                                            fileChannel.position(connectInfo.getStartPosition() + connectInfo.getDownSize());
//                                        }
                                    }
                                } catch (Exception e) {
                                    log.error(" read error :{}", e.getMessage(), e);
                                }
                            }


                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                log.error("exceptionCaught", cause);
                                //关闭旧的链接
                                if (channel != null && channel.isOpen()) {
                                    ctx.close();
                                }
                                if (fileChannel != null) {
                                    fileChannel.close();
                                }
                                //创建新的链接进行尝试
                                downLoad();
                            }

                            @Override
                            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                                log.error("释放链接channelUnregistered" + ctx.name());
                            }
                        });
                    }
                }));
//      new HttpDownInitializer(requestProto.getSsl(), connectInfo));

        //代理服务器解析DNS和连接 ??FIXME 没明白为什么这个设置代理服务器
        //bootstrap.resolver(NoopAddressResolverGroup.INSTANCE);

        String method = "GET";
        String url = MY_URL;
        Map<String, String> heads = Maps.newHashMap();
        String body = "";

        HttpRequestInfo request = buildRequest(method, url, heads, body);


        ChannelFuture cf = bootstrap.connect(host, port);
        cf.addListener((ChannelFutureListener) future -> {
            if (future.isSuccess()) {
                log.info("链接成功");
//                if (response.isSupportRange()) {
//                    request.headers().set(HttpHeaderNames.RANGE, "bytes=" + connectInfo.getStartPosition() + "-" + connectInfo.getEndPosition());
//                } else {
                request.headers().remove(HttpHeaderNames.RANGE);
//                }
                future.channel().writeAndFlush(request);
                System.out.println("发送成功");
            } else {
                log.error("链接失败 for " + future.cause().getMessage(), future.cause());
                future.channel().close();
            }
        });
//        cf.channel().closeFuture().sync();
    }


    public static HttpRequestInfo buildRequest(String method, String url, Map<String, String> heads, String body)
            throws MalformedURLException {
        URL u = new URL(url);
        HttpHeadsInfo headsInfo = new HttpHeadsInfo();
        headsInfo.add("Host", u.getHost());
        headsInfo.add("Connection", "keep-alive");
        headsInfo.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.75 Safari/537.36");
        headsInfo.add("Accept", "application/json,text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        headsInfo.add("Referer", u.getHost());
        if (heads != null) {
            for (Map.Entry<String, String> entry : heads.entrySet()) {
                headsInfo.set(entry.getKey(), entry.getValue() == null ? "" : entry.getValue());
            }
        }
        byte[] content = null;
        if (body != null && body.length() > 0) {
            content = body.getBytes();
            headsInfo.add("Content-Length", content.length);
        }
        HttpMethod httpMethod = StringUtil.isNullOrEmpty(method) ? HttpMethod.GET : HttpMethod.valueOf(method.toUpperCase());
        HttpRequestInfo requestInfo = new HttpRequestInfo(HttpRequestInfo.HttpVer.HTTP_1_1, httpMethod, u.getFile(), headsInfo, content);
        requestInfo.setRequestProto(parseRequestProto(u));
        return requestInfo;
    }

    public static RequestProto parseRequestProto(URL url) {
        int port = url.getPort() == -1 ? url.getDefaultPort() : url.getPort();
        return new RequestProto(url.getHost(), port, url.getProtocol().equalsIgnoreCase("https"));
    }


    private void genTargetFile() {
        File targetFile = new File(STORE_PATH);
        if (targetFile.exists()) {
            targetFile.delete();
        }
        targetFile.mkdir();
    }


    private Map<String, Object> genUrl(String url) {
        String host = "";
        String portTemp = null;
        Integer port = -1;
        Matcher matcher = pattern.matcher(MY_URL);
        if (matcher.find()) {
            host = matcher.group("host");
            portTemp = matcher.group("port");
            if (portTemp == null) {
                matcher = pattern.matcher(MY_URL);
                if (matcher.find()) {
                    portTemp = matcher.group("port");
                }
            }
        }
        if (portTemp != null) {
            port = Integer.parseInt(portTemp);
        }
        boolean isSsl = MY_URL.indexOf("https") == 0 || MY_URL.indexOf("https") == 0;
        if (port == -1) {
            if (isSsl) {
                port = 443;
            } else {
                port = 80;
            }
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("host", host);
        map.put("port", port);
        map.put("isSsl", isSsl);
        return map;
    }


    public static void main(String[] args) throws Exception {
        new RangeDownload().downLoad();
    }


}
