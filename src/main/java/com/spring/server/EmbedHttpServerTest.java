//package com.spring.server;
//
//import io.netty.bootstrap.ServerBootstrap;
//import io.netty.channel.*;
//import io.netty.channel.nio.NioEventLoopGroup;
//import io.netty.channel.socket.nio.NioServerSocketChannel;
//import io.netty.handler.codec.http.*;
//import lombok.extern.slf4j.Slf4j;
//
//import java.net.URI;
//import java.util.HashMap;
//import java.util.Map;
//
//@Slf4j
//public class EmbedHttpServerTest {
//
//    public static void main(String[] args) {
//        NioEventLoopGroup bossGroup = new NioEventLoopGroup(2);
//        NioEventLoopGroup workGroup = new NioEventLoopGroup(2);
//        try {
//            ServerBootstrap bootstrap = new ServerBootstrap().group(bossGroup, workGroup)
//                    .channel(NioServerSocketChannel.class)
//                    .childHandler(new ChannelInitializer<Channel>() {
//                        @Override
//                        protected void initChannel(Channel ch) throws Exception {
//                            ch.pipeline().addLast("httpCodec", new HttpServerCodec());
//                            ch.pipeline().addLast(new HttpObjectAggregator(4194304));
//                            ch.pipeline()
//                                    .addLast("serverHandle", new SimpleChannelInboundHandler<FullHttpRequest>() {
//
//                                        @Override
//                                        protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest request)
//                                                throws Exception {
//                                            URI uri = new URI(request.uri());
//                                            FullHttpResponse httpResponse = invoke(uri.getPath(), ctx.channel(), request);
//                                            if (httpResponse != null) {
//                                                httpResponse.headers().set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
//                                                httpResponse.headers().set(HttpHeaderNames.CONTENT_LENGTH, httpResponse.content().readableBytes());
//                                                ch.writeAndFlush(httpResponse);
//                                            }
//                                        }
//
//                                        @Override
//                                        public void channelUnregistered(ChannelHandlerContext ctx) {
//                                            ctx.channel().close();
//                                        }
//
//                                        @Override
//                                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
//                                            log.error("native request error", cause.getCause() == null ? cause : cause.getCause());
//                                            Map<String, Object> data = new HashMap<>();
//                                            data.put("error", cause.getCause().toString());
//                                            FullHttpResponse httpResponse = HttpHandlerUtil.buildJson(data);
//                                            httpResponse.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
//                                            ctx.channel().writeAndFlush(httpResponse);
//                                        }
//                                    });
//                        }
//                    });
//            ChannelFuture f = bootstrap.bind("127.0.0.1", 8099).sync();
//            if (startedListener != null) {
//                f.addListener(startedListener);
//            }
//            f.channel().closeFuture().sync();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            bossGroup.shutdownGracefully();
//            workGroup.shutdownGracefully();
//        }
//    }
//}
