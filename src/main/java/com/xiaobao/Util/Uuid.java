package com.xiaobao.Util;

import java.util.UUID;

/**
 * UUID
 * @author 小暴
 */
public class Uuid {
    UUID uuid = UUID.randomUUID();
    public String uuid(){
        String randomString = usingUUID();
        return randomString.substring(0, 8);
    }
    static String usingUUID() {
        UUID randomUUID = UUID.randomUUID();
        return randomUUID.toString().replaceAll("-", "");
    }
}
