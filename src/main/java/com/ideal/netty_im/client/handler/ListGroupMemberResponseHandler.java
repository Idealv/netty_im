package com.ideal.netty_im.client.handler;

import com.ideal.netty_im.protocol.response.ListGroupMemberResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

@ChannelHandler.Sharable
public class ListGroupMemberResponseHandler extends SimpleChannelInboundHandler<ListGroupMemberResponsePacket> {
    public static final ListGroupMemberResponseHandler INSTANCE = new ListGroupMemberResponseHandler();

    private ListGroupMemberResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberResponsePacket listGroupMemberResponsePacket) throws Exception {
        System.out.println("群[" + listGroupMemberResponsePacket.getGroupId() + "]成员有: " +
                listGroupMemberResponsePacket.getSessionList());
    }
}
