package com.ideal.netty_im.protocol.request;

import com.ideal.netty_im.protocol.Packet;
import lombok.Getter;
import lombok.Setter;

import static com.ideal.netty_im.protocol.command.Command.MESSAGE_REQUEST;

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
