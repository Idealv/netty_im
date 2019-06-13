package com.ideal.netty_im.attributes;

import com.ideal.netty_im.session.Session;
import io.netty.util.AttributeKey;

public interface Attributes {

    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
