package com.ideal.practice.part6.server;

import com.ideal.base.GenericNettyServer;
import com.ideal.practice.part12.*;
import com.ideal.practice.part13.Spliter;
import com.ideal.practice.part15.AuthHandler;
import com.ideal.practice.part17.CreateGroupRequestHandler;
import com.ideal.practice.part17.LogoutRequestHandler;
import com.ideal.practice.part18.JoinGroupResponseHandler;
import com.ideal.practice.part18.ListGroupMemberRequestHandler;
import com.ideal.practice.part18.QuitGroupRequestHandler;
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
                        pipeline.addLast(new Spliter());
                        //解码
                        pipeline.addLast(new PacketDecoder());
                        //登陆请求处理器
                        pipeline.addLast(new LoginRequestHandler());
                        //登陆认证处理器
                        pipeline.addLast(new AuthHandler());
                        //发消息请求处理器
                        pipeline.addLast(new MessageRequestHandler());
                        //建群请求处理器
                        pipeline.addLast(new CreateGroupRequestHandler());
                        //加群请求处理器
                        pipeline.addLast(new JoinGroupResponseHandler());
                        //退群请求处理器
                        pipeline.addLast(new QuitGroupRequestHandler());
                        //显示群成员请求处理器
                        pipeline.addLast(new ListGroupMemberRequestHandler());
                        //登出请求处理器
                        pipeline.addLast(new LogoutRequestHandler());
                        //编码
                        pipeline.addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap,8000);
    }
}
