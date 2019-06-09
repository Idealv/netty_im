package com.ideal.base;

import io.netty.bootstrap.ServerBootstrap;

public class GenericNettyServer {
    protected static void bind(final ServerBootstrap bootstrap, final int port){
        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                System.out.println("端口:["+port+"]绑定成功");
            }else {
                System.out.println("端口:["+port+"]绑定成功");
                bind(bootstrap,port+1);
            }
        });
    }
}
