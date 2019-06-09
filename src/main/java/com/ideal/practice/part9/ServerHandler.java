package com.ideal.practice.part9;

import com.ideal.practice.part10.MessageRequestPacket;
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

//@Slf4j
//public class ServerHandler extends ChannelInboundHandlerAdapter {
//
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        //System.out.println(new Date() + "服务端接收到客户端要登录的消息");
//
//        ByteBuf buf = (ByteBuf) msg;
//        //解码
//        Packet packet = PacketCodeC.INSTANCE.decode(buf);
//
//        if (packet instanceof LoginRequestPacket){
//            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
//            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
//            loginResponsePacket.setVersion(packet.getVersion());
//            //构造服务端响应对象
//            if (valid(loginRequestPacket)){
//                loginResponsePacket.setSuccess(true);
//                log.info(new Date()+":登陆成功!");
//            }else {
//                loginResponsePacket.setSuccess(false);
//                loginResponsePacket.setReason("登陆密码错误");
//                log.error(new Date()+"登陆失败");
//            }
//            //向客户端发出响应对象
//            ByteBuf resBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), loginResponsePacket);
//            ctx.channel().writeAndFlush(resBuf);
//        }else if (packet instanceof MessageRequestPacket){
//            MessageRequestPacket msgReqPacket = (MessageRequestPacket) packet;
//            log.info(new Date() + "服务端收到客户端消息:[" +
//                    msgReqPacket.getMessage() + "]");
//
//            MessageResponsePacket msgResPacket = new MessageResponsePacket();
//            msgResPacket.setMessage("服务端回复客户端:[" + msgReqPacket.getMessage() + "]");
//            ByteBuf msgResBuf = PacketCodeC.INSTANCE.encode(ctx.alloc(), msgResPacket);
//            ctx.channel().writeAndFlush(msgResBuf);
//        }
//    }
//
//    private boolean valid(LoginRequestPacket loginRequestPacket){
//        return true;
//    }
//}
