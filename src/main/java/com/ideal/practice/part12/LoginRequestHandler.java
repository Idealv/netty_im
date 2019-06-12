package com.ideal.practice.part12;

import com.ideal.practice.part10.LoginUtil;
import com.ideal.practice.part8.protocol.command.LoginRequestPacket;
import com.ideal.practice.part8.protocol.command.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket loginRequestPacket) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(loginRequestPacket.getVersion());
        //构造服务端响应对象
        if (valid(loginRequestPacket)){
            loginResponsePacket.setSuccess(true);
            //标识为登录状态
            LoginUtil.markAsLogin(ctx.channel());
            log.info(new Date()+":登陆成功!");
        }else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("登陆密码错误");
            log.error(new Date()+"登陆失败");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        return true;
    }


}
