package com.ideal.practice.part9;

import com.ideal.practice.part10.LoginUtil;
import com.ideal.practice.part10.MessageResponsePacket;
import com.ideal.practice.part8.protocol.command.LoginRequestPacket;
import com.ideal.practice.part8.protocol.command.LoginResponsePacket;
import com.ideal.practice.part8.protocol.command.Packet;
import com.ideal.practice.part8.protocol.command.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class ClientHandler extends ChannelInboundHandlerAdapter {
    //连接上客户端立刻调用此方法
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info(new Date() + "客户端开始登录");
        //构造登录对象
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserId(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("IdealV");
        loginRequestPacket.setPassword("hadoop");
        //构造编码对象
        ByteBuf buf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginRequestPacket);

        ctx.channel().writeAndFlush(buf);
    }

    //接受服务端响应
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        Packet packet = PacketCodeC.INSTANCE.decode(buf);

        if (packet instanceof LoginResponsePacket) {
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket) packet;

            if (loginResponsePacket.isSuccess()) {
                LoginUtil.markAsLogin(ctx.channel());
                log.info(new Date() + "收到服务端返回的响应,登陆成功");
            } else {
                log.info(new Date() + "收到服务端返回的响应,登陆失败->" +
                        loginResponsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket){
            MessageResponsePacket msgResPacket = (MessageResponsePacket) packet;
            log.info(new Date() + "客户端收到服务端返回的响应->" +
                    msgResPacket.getMessage());
        }
    }
}
