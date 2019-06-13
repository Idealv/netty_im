package com.ideal.netty_im.client.handler;

import com.ideal.netty_im.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    public static final JoinGroupResponseHandler INSTANCE = new JoinGroupResponseHandler();

    private JoinGroupResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket joinGroupResponsePacket) throws Exception {
        if (joinGroupResponsePacket.isSuccess()) {
            log.info("加入群聊[" + joinGroupResponsePacket.getGroupId() + "]成功");
        } else {
            log.info("加入群聊[" + joinGroupResponsePacket.getGroupId() + "]失败,原因:"
                    + joinGroupResponsePacket.getReason());
        }
    }
}
