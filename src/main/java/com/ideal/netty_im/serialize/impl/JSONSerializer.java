package com.ideal.netty_im.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.ideal.netty_im.serialize.Serializer;
import com.ideal.netty_im.serialize.SerializerAlgorithm;

public class JSONSerializer implements Serializer {
    @Override
    public byte getSerializeAlogrithm() {
        return SerializerAlgorithm.JSON;
    }

    @Override
    public byte[] serialize(Object obj) {
        return JSON.toJSONBytes(obj);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
