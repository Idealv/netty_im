package com.ideal.practice.part18;

import com.ideal.practice.part17.console.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

public class JoinGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("输入群号,加入群聊: ");

        String groupId = scanner.next();
        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
