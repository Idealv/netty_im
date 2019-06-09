package com.ideal.practice.part6.server;

import com.ideal.base.GenericHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstServerHandler extends GenericHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        System.out.println(new Date() + "服务端读取到数据:" +
                buf.toString(Charset.forName("utf-8")));

        System.out.println(new Date() + "服务端向客户端写入数据!");
        ByteBuf out = getByte(ctx, "你好这里是服务端");
        ctx.channel().writeAndFlush(out);
    }
}
