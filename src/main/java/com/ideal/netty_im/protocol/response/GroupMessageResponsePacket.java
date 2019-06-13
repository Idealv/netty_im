package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.session.Session;
import com.ideal.netty_im.protocol.Packet;
import lombok.Data;

import static com.ideal.netty_im.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

@Data
public class GroupMessageResponsePacket extends Packet {
    private String fromGroupId;

    private Session fromUser;

    private String message;

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
