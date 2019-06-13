package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.session.Session;
import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.ideal.netty_im.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

@Data
public class ListGroupMemberResponsePacket extends Packet {
    private String groupId;

    private List<Session> sessionList;

    @Override
    public Byte getCommand() {
        return LIST_GROUP_MEMBERS_RESPONSE;
    }
}
