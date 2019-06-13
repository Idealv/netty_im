package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import static com.ideal.netty_im.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;

    private String userId;

    private String username;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}
