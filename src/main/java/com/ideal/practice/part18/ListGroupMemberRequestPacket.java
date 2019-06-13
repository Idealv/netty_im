package com.ideal.practice.part18;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import static com.ideal.practice.part8.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

@Data
public class ListGroupMemberRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_REQUEST;
    }
}
