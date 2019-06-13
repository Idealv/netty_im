package com.ideal.practice.part18;

import com.ideal.practice.part8.protocol.command.Packet;
import lombok.Data;

import static com.ideal.practice.part8.protocol.command.Command.QUIT_GROUP_REQUEST;

@Data
public class QuitGroupRequestPacket extends Packet {
    private String groupId;

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
