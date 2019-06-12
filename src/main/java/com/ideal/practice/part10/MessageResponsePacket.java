package com.ideal.practice.part10;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import static com.ideal.practice.part8.protocol.command.Command.MESSAGE_RESPONSE;

@Getter@Setter
public class MessageResponsePacket extends Packet {
    private String message;

    private String fromUserId;

    private String fromUsername;

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }
}
