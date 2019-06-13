package com.ideal.netty_im.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

//连接假死:
//1.假死:连接断开，但是另一端没有收到四次挥手数据包或RST数据包
//2.非假死,但没发数据包
//解决1:客户端和服务端都需要检测对方是否假死
//解决2:定期服务端和客户端发心跳信息:防止客户端没断连接，只是没发数据包的情况
//这里自动定期发送心跳信息，防止这种情况
public class IMIdleStateHandler extends IdleStateHandler {

    private static final int READER_IDLE_TIME = 15;

    public IMIdleStateHandler() {
        super(READER_IDLE_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        System.out.println(READER_IDLE_TIME + "秒内未读到数据，关闭连接");
        ctx.channel().close();
    }
}
