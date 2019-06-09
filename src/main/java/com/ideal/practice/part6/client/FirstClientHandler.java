package com.ideal.practice.part6.client;

import com.ideal.base.GenericHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.Charset;
import java.util.Date;

public class FirstClientHandler extends GenericHandler {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(new Date() + ":客户端写入数据");
        ByteBuf buffer = getByte(ctx);
        //将数据写入服务端
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + "客户端读入数据->" + buf.toString(Charset.forName("utf-8")));
    }
}
