package com.ideal.practice.part18;

import com.ideal.practice.part16.Session;
import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import java.util.List;

import static com.ideal.practice.part8.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMemberResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
