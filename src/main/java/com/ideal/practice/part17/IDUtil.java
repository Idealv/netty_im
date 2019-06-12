package com.ideal.practice.part17;

import java.util.UUID;

public class IDUtil {
    public static String randomId(){
        return UUID.randomUUID().toString().split("-")[0];
    }
}
