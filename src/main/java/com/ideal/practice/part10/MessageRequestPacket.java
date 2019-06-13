package com.ideal.practice.part10;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Getter;
import lombok.Setter;

import static com.ideal.practice.part8.protocol.command.Command.MESSAGE_REQUEST;

@Getter@Setter
public class MessageRequestPacket extends Packet {
    private String message;

    private String toUserId;

    public MessageRequestPacket(String toUserId,String message) {
        this.toUserId = toUserId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }
}
