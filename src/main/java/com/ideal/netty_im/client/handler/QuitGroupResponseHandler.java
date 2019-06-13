package com.ideal.netty_im.client.handler;

import com.ideal.netty_im.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    public static final QuitGroupResponseHandler INSTANCE=new QuitGroupResponseHandler();

    private QuitGroupResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        String groupId = quitGroupResponsePacket.getGroupId();
        if (quitGroupResponsePacket.isSuccess())
            log.info("退出群[" + groupId + "]成功");
        else
            log.error("退出群[" + groupId + "]失败");
    }
}
