package com.ideal.netty_im.server.handler;

import com.ideal.netty_im.protocol.request.JoinGroupRequestPacket;
import com.ideal.netty_im.protocol.response.JoinGroupResponsePacket;
import com.ideal.netty_im.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
        channelGroup.add(ctx.channel());

        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        joinGroupResponsePacket.setGroupId(groupId);
        joinGroupResponsePacket.setSuccess(true);

        ctx.channel().writeAndFlush(joinGroupResponsePacket);
    }
}
