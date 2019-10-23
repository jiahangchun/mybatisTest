package com.spring.server.httpDemo;

import com.alipay.sofa.rpc.common.utils.JSONUtils;
import com.spring.server.HttpRequestInfo;
import com.spring.server.HttpUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author jiahangchun
 */
public class TimeClientHandler extends ChannelInboundHandlerAdapter {


    private AtomicInteger receiveCount = new AtomicInteger(0);
    private AtomicInteger sendCount = new AtomicInteger(0);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("read msg " + receiveCount.incrementAndGet());
        HttpUtils.genReadChannel(msg);
//        channelActive(ctx);
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
//        HttpRequestInfo httpRequestInfo = HttpUtils.genLocalSpringRequest();
//        String httpRequestInfoStr = JSONUtils.toJSONString(httpRequestInfo);
        String httpRequestInfoStr=HttpUtils.genHttpRequestStr();
        for (int i = 0; i < 1; i++) {
            ctx.channel().writeAndFlush(httpRequestInfoStr);
            System.out.println("发送请求。。。。" + (sendCount.incrementAndGet()) + "\r\n" + httpRequestInfoStr);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}