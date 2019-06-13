package com.ideal.practice.part18;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import static com.ideal.practice.part8.protocol.command.Command.JOIN_GROUP_RESPONSE;

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
