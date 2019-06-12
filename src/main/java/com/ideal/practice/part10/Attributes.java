package com.ideal.practice.part10;

import com.ideal.practice.part16.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
