package com.ideal.practice.part17.console;

import com.ideal.practice.part17.LogoutRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

public class LogoutConsoleCommand implements ConsoleCommand{
    @Override
    public void exec(Scanner scanner, Channel channel) {
        LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
        channel.writeAndFlush(logoutRequestPacket);
    }
}
