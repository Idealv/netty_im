package com.ideal.netty_im.protocol.request;

import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import java.util.List;

import static com.ideal.netty_im.protocol.command.Command.CREATE_GROUP_REQUEST;

@Data
public class CreateGroupRequestPacket extends Packet {
    private List<String> userIdList;

    @Override
    public Byte getCommand() {
        return CREATE_GROUP_REQUEST;
    }
}
