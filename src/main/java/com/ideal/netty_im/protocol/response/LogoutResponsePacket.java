package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import static com.ideal.netty_im.protocol.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
