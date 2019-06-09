package com.ideal.practice.part6.server;

import com.ideal.base.GenericNettyServer;
import com.ideal.practice.part12.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyServer extends GenericNettyServer {

    public static void main(String[] args) {
        //监听端口，accept新连接的线程组
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
                        //ch.pipeline().addLast(new FirstServerHandler());
                        //添加登录功能
                        //ch.pipeline().addLast(new ServerHandler());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginRequestHandler());
                        ch.pipeline().addLast(new MessageRequestHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap,8000);
    }
}
