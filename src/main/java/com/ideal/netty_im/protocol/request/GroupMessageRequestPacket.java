package com.ideal.netty_im.protocol.request;

import com.ideal.netty_im.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ideal.netty_im.protocol.command.Command.GROUP_MESSAGE_REQUEST;

@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
    private String toGroupId;

    private String message;

    public GroupMessageRequestPacket(String toGroupId, String message) {
        this.toGroupId = toGroupId;
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
