package com.spring.server.demo;

import com.spring.server.HttpUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TimeServerHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        Integer count = 0;

        for (int i = 0; i < 1000000; i++) {
//            ctx.channel().writeAndFlush(HttpUtils.genRequest());
            ByteBuf time = ctx.alloc().buffer();
            StringBuilder sb = new StringBuilder("abc");
            sb.append(System.getProperty("line.separator"));
            time.writeBytes(sb.toString().getBytes());
            ctx.writeAndFlush(time);
            System.out.println("发送请求。。。。" + count++);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        HttpUtils.genReadChannel(msg);
    }


    @Override

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

}
