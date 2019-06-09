package com.ideal.practice.part6.client;

import com.ideal.base.GenericNettyClient;
import com.ideal.practice.part12.LoginResponseHandler;
import com.ideal.practice.part12.MessageResponseHandler;
import com.ideal.practice.part12.PacketDecoder;
import com.ideal.practice.part12.PacketEncoder;
import com.ideal.practice.part13.Spliter;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
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
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());

                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

}
