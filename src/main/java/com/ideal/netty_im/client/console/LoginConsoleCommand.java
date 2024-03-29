package com.ideal.netty_im.client.console;

import com.ideal.netty_im.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.Scanner;

@Slf4j
public class LoginConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        log.info("输入用户名登录");
        String username = scanner.nextLine();

        loginRequestPacket.setUsername(username);
        loginRequestPacket.setPassword("pwd");
        channel.writeAndFlush(loginRequestPacket);
        waitForLogin();
    }

    private static void waitForLogin(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
    }
}
