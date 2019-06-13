package com.ideal.practice.part8.protocol.command;

import com.ideal.practice.part10.MessageRequestPacket;
import com.ideal.practice.part10.MessageResponsePacket;
import com.ideal.practice.part17.CreateGroupRequestPacket;
import com.ideal.practice.part17.CreateGroupResponsePacket;
import com.ideal.practice.part17.LogoutRequestPacket;
import com.ideal.practice.part17.LogoutResponsePacket;
import com.ideal.practice.part18.*;
import com.ideal.practice.part8.serialize.Serializer;
import com.ideal.practice.part8.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;

import java.util.HashMap;
import java.util.Map;

import static com.ideal.practice.part8.protocol.command.Command.*;

public class PacketCodeC {
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    public static final int MAGIC_NUMBER = 0x12345678;
    private static final Map<Byte, Class<? extends Packet>> packetTypeMap;
    private static final Map<Byte, Serializer> serializerMap;

    static {
        packetTypeMap = new HashMap<>();
        packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
        packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
        packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
        packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
        packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
        packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
        packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
        packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
        packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
        packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
        packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
        packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMemberRequestPacket.class);
        packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMemberResponsePacket.class);

        serializerMap = new HashMap<>();
        Serializer serializer = new JSONSerializer();
        serializerMap.put(serializer.getSerializeAlogrithm(), serializer);
    }

    private PacketCodeC(){}

    public void encode(ByteBuf buffer, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        //1.magicNumber
        buffer.writeInt(MAGIC_NUMBER);
        //2.version
        buffer.writeByte(packet.getVersion());
        //3.serializerAlgorithm
        buffer.writeByte(Serializer.DEFAULT.getSerializeAlogrithm());
        //4.command
        buffer.writeByte(packet.getCommand());
        //5.the length of data
        buffer.writeInt(bytes.length);
        //6.data
        buffer.writeBytes(bytes);
    }
    //buf->byte->JSON serialize
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
