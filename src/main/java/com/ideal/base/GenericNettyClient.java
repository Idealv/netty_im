package com.ideal.base;

import com.ideal.practice.part10.LoginUtil;
import com.ideal.practice.part10.MessageRequestPacket;
import com.ideal.practice.part8.protocol.command.PacketCodeC;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class GenericNettyClient{
    protected static int MAX_RETRY=5;

    protected GenericNettyClient(final int MAX_RETRY){
        this.MAX_RETRY = MAX_RETRY;
    }

    protected static void connect(Bootstrap bootstrap, String host, int port, int retry){
        bootstrap.connect(host,port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("连接成功!");
                Channel ch = ((ChannelFuture) future).channel();
                //开启读线程读取客户端输入发往客户端
                startConsoleThread(ch);
            }else if (retry==0){
                System.err.println("连接次数已用完,放弃连接");
            }else {
                int order=(MAX_RETRY-retry)+1;
                int delay=1<<order;
                System.err.println(new Date()+"第["+order+"]连接失败,尝试重连!");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

    public static void startConsoleThread(Channel channel){
        new Thread(()->{
            while (!Thread.interrupted()){
                if (LoginUtil.hasLogin(channel)){
                    System.out.println("客户端发送消息到服务端:");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();

                    MessageRequestPacket requestPacket = new MessageRequestPacket();
                    requestPacket.setMessage(line);
                    //decode
                    ByteBuf buf = PacketCodeC.INSTANCE.encode(channel.alloc(), requestPacket);
                    channel.writeAndFlush(buf);
                }
            }
        }).start();
    }
}
