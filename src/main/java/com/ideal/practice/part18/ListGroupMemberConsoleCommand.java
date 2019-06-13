package com.ideal.practice.part18;

import com.ideal.practice.part17.console.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Scanner;

public class ListGroupMemberConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        ListGroupMemberRequestPacket listGroupMemberRequestPacket = new ListGroupMemberRequestPacket();
        System.out.println("输入群号,获取群成员: ");
        String groupId = scanner.next();

        listGroupMemberRequestPacket.setGroupId(groupId);
        channel.writeAndFlush(listGroupMemberRequestPacket);
    }
}
