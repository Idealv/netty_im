package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import static com.ideal.netty_im.protocol.command.Command.JOIN_GROUP_RESPONSE;

@Data
public class JoinGroupResponsePacket extends Packet {
    private String groupId;

    private boolean success;

    private String reason;

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_RESPONSE;
    }
}
