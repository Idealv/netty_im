package com.ideal.netty_im.codec;

import com.ideal.netty_im.protocol.Packet;
import com.ideal.netty_im.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        //将packet编码成为ByteBuf对象
        PacketCodeC.INSTANCE.encode(byteBuf, packet);
    }
}
