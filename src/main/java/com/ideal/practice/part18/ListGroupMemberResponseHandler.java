package com.ideal.practice.part18;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket listGroupMemberResponsePacket) throws Exception {
        System.out.println("群[" + listGroupMemberResponsePacket.getGroupId() + "]成员有: " +
                listGroupMemberResponsePacket.getSessionList());
    }
}
