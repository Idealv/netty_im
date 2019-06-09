package com.ideal.practice.part8.protocol.command;

import lombok.Data;

import static com.ideal.practice.part8.protocol.command.Command.LOGIN_RESPONSE;

@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }
}