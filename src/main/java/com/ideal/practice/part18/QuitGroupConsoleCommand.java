package com.ideal.practice.part18;

import com.ideal.practice.part17.console.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("即将退出群聊,请输入要退出的群的群号: ");

        String groupId = scanner.next();

        QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();
        quitGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(quitGroupRequestPacket);
    }
}
