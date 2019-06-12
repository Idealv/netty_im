package com.ideal.practice.part12;

import com.ideal.practice.part16.Session;
import com.ideal.practice.part16.SessionUtil;
import com.ideal.practice.part8.protocol.command.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

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
