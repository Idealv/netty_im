package com.ideal.netty_im.server.handler;

import com.ideal.netty_im.protocol.request.CreateGroupRequestPacket;
import com.ideal.netty_im.protocol.response.CreateGroupResponsePacket;
import com.ideal.netty_im.util.IDUtil;
import com.ideal.netty_im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket createGroupRequestPacket) throws Exception {
        List<String> userIdList = createGroupRequestPacket.getUserIdList();
        List<String> usernameList = new ArrayList<>();
        ChannelGroup channelGroup = new DefaultChannelGroup(ctx.executor());

        for (String userId:
             userIdList) {
            Channel channel = SessionUtil.getChannel(userId);
            if (channel!=null){
                channelGroup.add(channel);
                usernameList.add(SessionUtil.getSession(channel).getUsername());
            }
        }
        String groupId = IDUtil.randomId();
        CreateGroupResponsePacket createGroupResponsePacket = new CreateGroupResponsePacket();
        createGroupResponsePacket.setSuccess(true);
        createGroupResponsePacket.setGroupId(groupId);
        createGroupResponsePacket.setUsernameList(usernameList);

        channelGroup.writeAndFlush(createGroupResponsePacket);

        log.info("群创建成功:群号为:[" + createGroupResponsePacket.getGroupId() + "]");
        log.info("群里有:[" + usernameList + "]");

        SessionUtil.bindChannelGroup(groupId,channelGroup);
    }
}
