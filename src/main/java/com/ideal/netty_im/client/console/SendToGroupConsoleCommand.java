package com.ideal.netty_im.client.console;

import com.ideal.netty_im.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class SendToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入群号,发送消息至此群: ");
        String toGroupId = scanner.next();
        String message = scanner.next();

        channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));
    }
}
