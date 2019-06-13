package com.ideal.netty_im.client;

import com.ideal.netty_im.client.handler.LoginResponseHandler;
import com.ideal.netty_im.client.handler.MessageResponseHandler;
import com.ideal.netty_im.codec.PacketDecoder;
import com.ideal.netty_im.codec.PacketEncoder;
import com.ideal.netty_im.codec.Spliter;
import com.ideal.netty_im.client.handler.CreateGroupResponseHandler;
import com.ideal.netty_im.client.handler.LogoutResponseHandler;
import com.ideal.netty_im.client.handler.JoinGroupResponseHandler;
import com.ideal.netty_im.client.handler.ListGroupMemberResponseHandler;
import com.ideal.netty_im.client.handler.QuitGroupResponseHandler;
import com.ideal.netty_im.client.handler.GroupMessageResponseHandler;
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
                        pipeline.addLast(new Spliter());
                        //解码器
                        pipeline.addLast(new PacketDecoder());
                        //登陆响应处理器
                        pipeline.addLast(new LoginResponseHandler());
                        //发消息响应处理器
                        pipeline.addLast(new MessageResponseHandler());
                        //建群响应处理器
                        pipeline.addLast(new CreateGroupResponseHandler());
                        //加群响应处理器
                        pipeline.addLast(new JoinGroupResponseHandler());
                        //退群响应处理器
                        pipeline.addLast(new QuitGroupResponseHandler());
                        //显示群成员响应处理器
                        pipeline.addLast(new ListGroupMemberResponseHandler());
                        //群消息响应处理器
                        pipeline.addLast(new GroupMessageResponseHandler());
                        //登出响应处理器
                        pipeline.addLast(new LogoutResponseHandler());
                        //译码器
                        pipeline.addLast(new PacketEncoder());

                    }
                });
        connect(bootstrap, HOST, PORT, MAX_RETRY);
    }

}
