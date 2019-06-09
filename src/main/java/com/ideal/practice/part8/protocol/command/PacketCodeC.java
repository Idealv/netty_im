package com.ideal.practice.part8.protocol.command;

import com.ideal.practice.part8.serialize.Serializer;
import com.ideal.practice.part8.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;
import static com.ideal.practice.part8.protocol.command.Command.LOGIN_REQUEST;

public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializeAlogrithm(), serializer);
    }

    private PacketCodeC(){}

    public ByteBuf encode(ByteBufAllocator bufAllocator, Packet packet) {
        //返回适配 io 读写相关的内存
        ByteBuf buffer = bufAllocator.ioBuffer();

        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        //1.magicNumber
        buffer.writeInt(MAGIC_NUMBER);
        //2.version
        buffer.writeByte(packet.getVersion());
        //3.serializerAlgorithm
        buffer.writeByte(Serializer.JSON_SERIALZER);
        //4.command
        buffer.writeByte(packet.getCommand());
        //5.the length of data
        buffer.writeInt(bytes.length);
        //6.data
        buffer.writeBytes(bytes);
        return buffer;
    }

    public Packet decode(ByteBuf buf) {
        //skip magicNumber
        buf.skipBytes(4);
        //skip version
        buf.skipBytes(1);

        byte serializeAlgorithm = buf.readByte();
        byte command = buf.readByte();
        int length = buf.readInt();

        byte[] bytes = new byte[length];
        //read data to bytes
        buf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeAlgorithm);

        if (requestType != null && serializer != null) {
            return serializer.deserialize(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeAlgorithm) {
        return serializerMap.get(serializeAlgorithm);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return packetTypeMap.get(command);
    }



}
