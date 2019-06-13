package com.ideal.netty_im.protocol.request;

import com.ideal.netty_im.protocol.Packet;

import static com.ideal.netty_im.protocol.command.Command.HEARTBEAT_REQUEST;

public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEARTBEAT_REQUEST;
    }
}
