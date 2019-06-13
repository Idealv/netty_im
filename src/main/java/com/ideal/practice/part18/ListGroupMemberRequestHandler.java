package com.ideal.practice.part18;

import com.ideal.practice.part16.Session;
import com.ideal.practice.part16.SessionUtil;
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
