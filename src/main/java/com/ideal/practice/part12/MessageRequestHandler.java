package com.ideal.practice.part12;

import com.ideal.practice.part10.MessageRequestPacket;
import com.ideal.practice.part10.MessageResponsePacket;
import com.ideal.practice.part16.Session;
import com.ideal.practice.part16.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {
        //从当前连接获取session
        Session session = SessionUtil.getSession(ctx.channel());

        //通过消息发起方构造MessageResponsePacket对象
        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setMessage(messageRequestPacket.getMessage());
        messageResponsePacket.setFromUserId(session.getUserId());
        messageResponsePacket.setFromUsername(session.getUsername());

        //获取消息接收方连接
        Channel toChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

        if (toChannel!=null&&SessionUtil.hasLogin(toChannel)){
            toChannel.writeAndFlush(messageResponsePacket);
        }else {
            log.info("[" + messageRequestPacket.getToUserId() + "]不在线,消息发送失败");
        }
    }
}
