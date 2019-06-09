package com.ideal.base;

public class NettyServer {
//    private static void bind(final ServerBootstrap bootstrap,final int port){
//        bootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
//            @Override
//            public void operationComplete(Future<? super Void> future) throws Exception {
//                if (future.isSuccess()){
//                    System.out.println("端口:["+port+"]绑定成功");
//                }else {
//                    System.out.println("端口:["+port+"]绑定成功");
//                    bind(bootstrap,port+1);
//                }
//            }
//        });
//    }
//
//    public static void main(String[] args) {
//        //监听端口，accept新连接的线程组
//        NioEventLoopGroup boss = new NioEventLoopGroup();
//        //处理每一条连接数据读写的线程组
//        NioEventLoopGroup worker = new NioEventLoopGroup();
//
//        //引导类
//        ServerBootstrap serverBootstrap = new ServerBootstrap();
//        //NioServerSocket->ServerSocket
//        //NioSocket->Socket
//        serverBootstrap
//                .group(boss, worker)
//                .channel(NioServerSocketChannel.class)//配置服务端IO模型位NIO
//                .childHandler(new ChannelInitializer<NioSocketChannel>() {
//                    @Override
//                    protected void initChannel(NioSocketChannel ch) throws Exception {
//                        System.out.println("服务端启动");
//
//                        ch.pipeline().addLast(new StringDecoder());
//                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
//                            @Override
//                            protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s) throws Exception {
//                                System.out.println(s);
//                            }
//                        });
//                    }
//                });
//        bind(serverBootstrap,8000);
//    }
}
