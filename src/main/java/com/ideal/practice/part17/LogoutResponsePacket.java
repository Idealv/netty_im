package com.ideal.practice.part17;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import static com.ideal.practice.part8.protocol.command.Command.LOGOUT_RESPONSE;

@Data
public class LogoutResponsePacket extends Packet {
    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
