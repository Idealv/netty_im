package com.ideal.netty_im.server;

import com.ideal.netty_im.codec.PacketCodecHandler;
import com.ideal.netty_im.handler.IMIdleStateHandler;
import com.ideal.netty_im.server.handler.*;
import com.ideal.netty_im.codec.Spliter;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer extends GenericNettyServer {

    public static void main(String[] args) {
        //监听端口，accept新连接的线程组    1
        NioEventLoopGroup boss = new NioEventLoopGroup();
        //处理每一条连接数据读写的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup();

        //引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //NioServerSocket->ServerSocket
        //NioSocket->Socket
        serverBootstrap
                .group(boss, worker)
                .channel(NioServerSocketChannel.class)//配置服务端IO模型位NIO
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new IMIdleStateHandler());
                        pipeline.addLast(new Spliter());
                        pipeline.addLast(PacketCodecHandler.INSTANCE);
                        //解码
                        //pipeline.addLast(new PacketDecoder());
                        //登陆请求处理器
                        pipeline.addLast(LoginRequestHandler.INSTANCE);
                        pipeline.addLast(HeartBeatRequestHandler.INSTANCE);
                        //登陆认证处理器
                        pipeline.addLast(AuthHandler.INSTANCE);
                        pipeline.addLast(IMHandler.INSTANCE);
//                        //发消息请求处理器
//                        pipeline.addLast(MessageRequestHandler.INSTANCE);
//                        //建群请求处理器
//                        pipeline.addLast(CreateGroupRequestHandler.INSTANCE);
//                        //加群请求处理器
//                        pipeline.addLast(JoinGroupRequestHandler.INSTANCE);
//                        //退群请求处理器
//                        pipeline.addLast(QuitGroupRequestHandler.INSTANCE);
//                        //显示群成员请求处理器
//                        pipeline.addLast(ListGroupMemberRequestHandler.INSTANCE);
//                        //群消息请求处理器
//                        pipeline.addLast(GroupMessageRequestHandler.INSTANCE);
//                        //登出请求处理器
//                        pipeline.addLast(LogoutRequestHandler.INSTANCE);
//                        //编码
//                        //pipeline.addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap,8000);
    }
}
