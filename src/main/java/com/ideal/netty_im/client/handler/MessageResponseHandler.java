package com.ideal.netty_im.client.handler;

import com.ideal.netty_im.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    public static final MessageResponseHandler INSTANCE = new MessageResponseHandler();

    private MessageResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) throws Exception {
        String fromUserId = messageResponsePacket.getFromUserId();
        String fromUsername = messageResponsePacket.getFromUsername();
        System.out.println(fromUserId + ":" + fromUsername + "->" + messageResponsePacket.getMessage());
    }
}
