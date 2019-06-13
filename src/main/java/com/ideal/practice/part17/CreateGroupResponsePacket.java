package com.ideal.practice.part17;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import java.util.List;

import static com.ideal.practice.part8.protocol.command.Command.CREATE_GROUP_RESPONSE;

@Data
public class CreateGroupResponsePacket extends Packet {
    private boolean success;

    private String groupId;

    private List<String> usernameList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_RESPONSE;
    }
}
