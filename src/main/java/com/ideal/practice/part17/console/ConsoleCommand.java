package com.ideal.practice.part17.console;

import io.netty.channel.Channel;

import java.util.Scanner;

public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
