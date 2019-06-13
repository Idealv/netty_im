package com.ideal.netty_im.codec;

import com.ideal.netty_im.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //将ByteBuf解码成用户定义的Packet对象
        out.add(PacketCodeC.INSTANCE.decode(in));
    }
}
