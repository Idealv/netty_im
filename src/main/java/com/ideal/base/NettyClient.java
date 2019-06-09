package com.ideal.base;

public class NettyClient {
//    private static final int MAX_RETRY=5;
//
//    private static void connect(Bootstrap bootstrap,String host,int port,int retry){
//        bootstrap.connect(host,port).addListener(future -> {
//            if (future.isSuccess()){
//                System.out.println("连接成功!");
//            }else if (retry==0){
//                System.err.println("连接次数已用完,放弃连接");
//            }else {
//                int order=(MAX_RETRY-retry)+1;
//                int delay=1<<order;
//                System.err.println(new Date()+"第["+order+"]连接失败,尝试重连!");
//                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
//                        .SECONDS);
//            }
//        });
//    }
//
//
//    public static void main(String[] args) throws InterruptedException {
//        Bootstrap bootstrap = new Bootstrap();
//        NioEventLoopGroup group = new NioEventLoopGroup();
//
//        bootstrap.group(group)
//                .channel(NioSocketChannel.class)
//                .handler(new ChannelInitializer<Channel>() {
//                    @Override
//                    protected void initChannel(Channel ch) {
//                        ch.pipeline().addLast(new StringEncoder());
//                    }
//                });
//        connect(bootstrap, "juejin.im", 80, MAX_RETRY);
////        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
////
////        while (true) {
////            channel.writeAndFlush(new Date() + ": hello world!");
////            Thread.sleep(2000);
////        }
//    }
}
