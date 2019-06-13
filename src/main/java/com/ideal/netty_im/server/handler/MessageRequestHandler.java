package com.ideal.netty_im.server.handler;

import com.ideal.netty_im.protocol.request.MessageRequestPacket;
import com.ideal.netty_im.protocol.response.MessageResponsePacket;
import com.ideal.netty_im.session.Session;
import com.ideal.netty_im.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@ChannelHandler.Sharable
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    public static final MessageRequestHandler INSTANCE=new MessageRequestHandler();

    private MessageRequestHandler(){}

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
