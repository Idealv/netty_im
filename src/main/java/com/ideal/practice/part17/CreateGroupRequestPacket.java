package com.ideal.practice.part17;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import java.util.List;

import static com.ideal.practice.part8.protocol.command.Command.CREATE_GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
