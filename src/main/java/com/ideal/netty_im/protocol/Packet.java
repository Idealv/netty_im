package com.ideal.netty_im.protocol;

import lombok.Data;

@Data
public abstract class Packet {
    private Byte version=1;

    public abstract Byte getCommand();

    //public abstract void doOperation();
}
