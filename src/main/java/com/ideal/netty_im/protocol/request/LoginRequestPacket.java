package com.ideal.netty_im.protocol.request;

import com.ideal.netty_im.protocol.Packet;
import com.ideal.netty_im.protocol.command.Command;
import lombok.Data;

@Data
public class LoginRequestPacket extends Packet {
    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
