package com.ideal.netty_im.protocol.request;

import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import static com.ideal.netty_im.protocol.command.Command.JOIN_GROUP_REQUEST;

@Data
public class JoinGroupRequestPacket extends Packet {
    private String groupId;


    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
