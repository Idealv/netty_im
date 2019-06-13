package com.ideal.netty_im.session;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Session {
    private String userId;

    private String username;

    public Session(String userId, String username) {
        this.userId = userId;
        this.username = username;
    }

    @Override
    public String toString() {
        return this.userId + ":" + this.username;
    }
}
