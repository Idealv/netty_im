package com.ideal.netty_im.server.handler;

import com.ideal.netty_im.protocol.request.ListGroupMemberRequestPacket;
import com.ideal.netty_im.protocol.response.ListGroupMemberResponsePacket;
import com.ideal.netty_im.session.Session;
import com.ideal.netty_im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

public class ListGroupMemberRequestHandler extends SimpleChannelInboundHandler<ListGroupMemberRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ListGroupMemberRequestPacket listGroupMemberRequestPacket) throws Exception {
        String groupId = listGroupMemberRequestPacket.getGroupId();

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        List<Session> sessionList = new ArrayList<>();

        for (Channel channel:
             channelGroup) {
            Session session = SessionUtil.getSession(channel);
            sessionList.add(session);
        }

        ListGroupMemberResponsePacket listGroupMemberResponsePacket = new ListGroupMemberResponsePacket();
        listGroupMemberResponsePacket.setGroupId(groupId);
        listGroupMemberResponsePacket.setSessionList(sessionList);

        ctx.channel().writeAndFlush(listGroupMemberResponsePacket);
    }
}
