package com.ideal.netty_im.client;

import com.ideal.netty_im.client.handler.*;
import com.ideal.netty_im.codec.PacketCodecHandler;
import com.ideal.netty_im.codec.PacketDecoder;
import com.ideal.netty_im.codec.PacketEncoder;
import com.ideal.netty_im.codec.Spliter;
import com.ideal.netty_im.handler.IMIdleStateHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient extends GenericNettyClient {
    private static final int CHILD_MAX_RETRY=5;
    private static final String HOST ="127.0.0.1";
    private static final int PORT=8000;

    public NettyClient(){
        super(CHILD_MAX_RETRY);
    }

    public static void main(String[] args) {
        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup worker = new NioEventLoopGroup();

        bootstrap.group(worker)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE,true)
                .option(ChannelOption.TCP_NODELAY,true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        //channel.pipeline().addLast(new FirstClientHandler());
                        //添加拆包器
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new IMIdleStateHandler());
                        pipeline.addLast(new Spliter());
                        //解码器
                        pipeline.addLast(new PacketDecoder());
                        //登陆响应处理器
                        pipeline.addLast(LoginResponseHandler.INSTANCE);
                        //发消息响应处理器
                        pipeline.addLast(MessageResponseHandler.INSTANCE);
                        //建群响应处理器
                        pipeline.addLast(CreateGroupResponseHandler.INSTANCE);
                        //加群响应处理器
                        pipeline.addLast(JoinGroupResponseHandler.INSTANCE);
                        //退群响应处理器
                        pipeline.addLast(QuitGroupResponseHandler.INSTANCE);
                        //显示群成员响应处理器
                        pipeline.addLast(ListGroupMemberResponseHandler.INSTANCE);
                        //群消息响应处理器
                        pipeline.addLast(GroupMessageResponseHandler.INSTANCE);
                        //登出响应处理器
                        pipeline.addLast(LogoutResponseHandler.INSTANCE);
                        //译码器
                        pipeline.addLast(new PacketEncoder());
                        //心跳定时器
                        pipeline.addLast(new HeartBeatTimerHandler());

                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

}
