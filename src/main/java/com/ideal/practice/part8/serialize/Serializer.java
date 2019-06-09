package com.ideal.practice.part8.serialize;

import com.ideal.practice.part8.serialize.impl.JSONSerializer;

public interface Serializer {
    byte JSON_SERIALZER=1;

    Serializer DEFAULT = new JSONSerializer();

    byte getSerializeAlogrithm();

    //将java对象序列化
    byte[] serialize(Object obj);

    //将二进制数据反序列化为java对象
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
