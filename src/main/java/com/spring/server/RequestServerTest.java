package com.spring.server;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.timeout.ReadTimeoutHandler;
import lombok.extern.slf4j.Slf4j;

import java.nio.channels.SeekableByteChannel;

@Slf4j
public class RequestServerTest {

    public static String host = "erp.test.jimuitech.com";
    public static Integer port = 80;


    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(loopGroup)
                .handler((new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast("downTimeout", new ReadTimeoutHandler(10000 * 60));
                        channel.pipeline().addLast("httpCodec", new HttpClientCodec());
                        channel.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                            private SeekableByteChannel fileChannel;

                            @Override
                            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                                super.channelActive(ctx);
                                log.info("channel激活 ");
                                for(int i=0;i<100;i++) {
                                    ctx.writeAndFlush(HttpUtils.genLocalRequest());
                                }
                            }

                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                HttpUtils.genReadChannel(msg);
                            }


                            @Override
                            public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                log.error("exceptionCaught", cause);
                            }

                            @Override
                            public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
                                log.error("释放链接channelUnregistered" + ctx.name());
                            }
                        });
                    }
                }));


        ChannelFuture cf = bootstrap.connect(host, port).sync();

//        cf.addListener((ChannelFutureListener) future -> {
//            if (future.isSuccess()) {
//                log.info("链接成功");
//                for (int i = 0; i < 1000; i++) {
//                    future.channel().writeAndFlush(HttpUtils.genRequest());
//                    Thread.sleep(1000);
//                }
//            }
//        });

        cf.channel().closeFuture().sync();
        System.out.println("close");

    }


}
