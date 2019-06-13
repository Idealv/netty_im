package com.ideal.practice.part17;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import static com.ideal.practice.part8.protocol.command.Command.LOGOUT_REQUEST;

@Data
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
