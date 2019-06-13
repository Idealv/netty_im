package com.ideal.netty_im.protocol.response;

import com.ideal.netty_im.protocol.Packet;

import static com.ideal.netty_im.protocol.command.Command.HEARTBEAT_RESPONSE;

public class HeartBeatResponsePacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_RESPONSE;
    }
}
