package com.ideal.practice.part18;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()){
            log.info("加入群聊[" + joinGroupResponsePacket.getGroupId() + "]成功");
        }else {
            log.info("加入群聊[" + joinGroupResponsePacket.getGroupId() + "]失败,原因:"
                    + joinGroupResponsePacket.getReason());
        }
    }
}
