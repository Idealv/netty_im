package com.ideal.practice.part12;

import com.ideal.practice.part10.LoginUtil;
import com.ideal.practice.part8.protocol.command.LoginRequestPacket;
import com.ideal.practice.part8.protocol.command.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //编码和解码由PacketEncoder和PacketDecoder来做
        log.info(new Date() + "客户端开始登录");
        //构造登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("IdealV");
        loginRequestPacket.setPassword("hadoop");

        ctx.channel().writeAndFlush(loginRequestPacket);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) throws Exception {
        //客户端接受服务端发回的登录响应结果
        if (loginResponsePacket.isSuccess()) {
            LoginUtil.markAsLogin(ctx.channel());
            log.info(new Date() + "收到服务端返回的响应,登陆成功");
        } else {
            log.info(new Date() + "收到服务端返回的响应,登陆失败->" +
                    loginResponsePacket.getReason());
        }
    }
}
