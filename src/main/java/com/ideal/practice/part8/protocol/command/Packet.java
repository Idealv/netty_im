package com.ideal.practice.part8.protocol.command;

import lombok.Data;

@Data
public abstract class Packet {
    private Byte version=1;

    public abstract Byte getCommand();

    //public abstract void doOperation();
}
