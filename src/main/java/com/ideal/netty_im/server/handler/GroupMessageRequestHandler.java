package com.ideal.netty_im.server.handler;

import com.ideal.netty_im.protocol.request.GroupMessageRequestPacket;
import com.ideal.netty_im.protocol.response.GroupMessageResponsePacket;
import com.ideal.netty_im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket packet) throws Exception {
        String toGroupId = packet.getToGroupId();

        GroupMessageResponsePacket groupMessageResponsePacket = new GroupMessageResponsePacket();
        groupMessageResponsePacket.setFromGroupId(toGroupId);
        groupMessageResponsePacket.setMessage(packet.getMessage());
        groupMessageResponsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));

        ChannelGroup channelGroup = SessionUtil.getChannelGroup(toGroupId);
        channelGroup.writeAndFlush(groupMessageResponsePacket);
    }
}
