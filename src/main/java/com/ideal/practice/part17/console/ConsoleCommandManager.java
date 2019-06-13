package com.ideal.practice.part17.console;

import com.ideal.practice.part16.SessionUtil;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsoleCommandManager implements ConsoleCommand {
    private Map<String,ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("createGroup", new CreateGroupConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
    }

    @Override
    public void exec(Scanner scanner, Channel channel) {

        String command = scanner.next();

        if (!SessionUtil.hasLogin(channel)) {
            return;
        }

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);
        if (consoleCommand!=null){
            consoleCommand.exec(scanner, channel);
        }else {
            System.out.println("指令["+command+"]错误,请重新输入");
        }

    }
}
