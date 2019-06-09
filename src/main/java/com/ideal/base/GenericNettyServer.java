package com.ideal.base;

import io.netty.bootstrap.ServerBootstrap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GenericNettyServer {
    protected static void bind(final ServerBootstrap bootstrap, final int port){
        bootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()){
                log.info("端口:["+port+"]绑定成功");
            }else {
                log.info("端口:["+port+"]绑定成功");
                bind(bootstrap,port+1);
            }
        });
    }
}
