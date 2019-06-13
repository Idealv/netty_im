package com.ideal.practice.part18;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        String groupId = quitGroupResponsePacket.getGroupId();
        if (quitGroupResponsePacket.isSuccess())
            log.info("退出群[" + groupId + "]成功");
        else
            log.error("退出群[" + groupId + "]失败");
    }
}
