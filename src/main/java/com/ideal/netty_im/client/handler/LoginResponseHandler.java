package com.ideal.netty_im.client.handler;

import com.ideal.netty_im.session.Session;
import com.ideal.netty_im.util.SessionUtil;
import com.ideal.netty_im.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@ChannelHandler.Sharable
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    public static final LoginResponseHandler INSTANCE = new LoginResponseHandler();

    private LoginResponseHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        String userId = loginResponsePacket.getUserId();
        String username = loginResponsePacket.getUsername();

        if (loginResponsePacket.isSuccess()){
            log.info("[" + username + "]登陆成功,id为:" + userId);
            SessionUtil.bindSession(new Session(userId, username), ctx.channel());
        }else {
            log.info("["+username+"]登陆失败,原因为:"+loginResponsePacket.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("客户端连接断开");
    }
}
