package com.ideal.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

public abstract class GenericHandler extends ChannelInboundHandlerAdapter {
    protected ByteBuf getByte(ChannelHandlerContext ctx){
        //获取ByteBuf内存管理器
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = "你好,IdealV".getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }

    protected ByteBuf getByte(ChannelHandlerContext ctx,String msg){
        //获取ByteBuf内存管理器
        ByteBuf buffer = ctx.alloc().buffer();

        byte[] bytes = msg.getBytes(Charset.forName("utf-8"));
        buffer.writeBytes(bytes);
        return buffer;
    }
}
