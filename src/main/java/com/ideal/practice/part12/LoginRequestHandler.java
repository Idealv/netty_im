package com.ideal.practice.part12;

import com.ideal.practice.part16.Session;
import com.ideal.practice.part16.SessionUtil;
import com.ideal.practice.part8.protocol.command.LoginRequestPacket;
import com.ideal.practice.part8.protocol.command.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        loginResponsePacket.setUsername(loginRequestPacket.getUsername());

        //构造服务端响应对象
        if (valid(loginRequestPacket)){
            loginResponsePacket.setSuccess(true);
            String userId=randomUserId();
            loginResponsePacket.setUserId(userId);
            log.info(new Date()+":"+loginRequestPacket.getUsername()+":登陆成功!");
            SessionUtil.bindSession(new Session(userId,loginRequestPacket.getUsername()),ctx.channel());
        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登陆密码错误");
            log.error(new Date()+"登陆失败");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private String randomUserId() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
    }
}
