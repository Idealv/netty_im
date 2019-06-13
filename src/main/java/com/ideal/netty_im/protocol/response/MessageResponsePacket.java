package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.protocol.Packet;
import lombok.Getter;
import lombok.Setter;

import static com.ideal.netty_im.protocol.command.Command.MESSAGE_RESPONSE;

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
