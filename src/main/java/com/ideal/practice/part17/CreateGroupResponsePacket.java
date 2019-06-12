package com.ideal.practice.part17;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.ideal.practice.part8.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Getter@Setter
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> usernameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
