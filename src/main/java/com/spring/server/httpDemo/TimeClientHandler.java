package com.spring.server.httpDemo;

import com.spring.server.HttpUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class TimeClientHandler extends ChannelInboundHandlerAdapter {

    public Integer count=0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf m = (ByteBuf) msg;
        System.out.println("read msg");
        try {
            HttpUtils.genReadChannel(m);
            count++;
            System.out.println("接收请求     "+count);
        } finally {
            m.release();
        }
    }

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        Integer count = 0;

        for (int i = 0; i < 1; i++) {
            ctx.channel().writeAndFlush(HttpUtils.genLocalSpringRequest());
            System.out.println("发送请求。。。。" + count++);
        }
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}