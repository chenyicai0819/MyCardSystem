package com.chen.mycardsystembackstage.utils;

import cn.hutool.crypto.SmUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Project : MyCardSystem - Sm3Utils
 * Powered by GeorgeChen On 2023-12-14 15:40:09
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
@Component
public class Sm3Utils {

    public static String create(){
        String appCode = "ycJqGD";
        String appSecretKey = "770df23d-ecee-4087-aa04-f5ecb190707b";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(UUID.randomUUID());

        String signKey = appCode + appSecretKey + requestId + timestamp;
        String digestHex = SmUtil.sm3(signKey);
        System.out.println(timestamp);
        System.out.println(digestHex);
        return digestHex;
    }

    public static void main(String[] args){
        String appCode = "ycJqGD";
        String appSecretKey = "770df23d-ecee-4087-aa04-f5ecb190707b";
        String timestamp = String.valueOf(System.currentTimeMillis());
        String requestId = String.valueOf(UUID.randomUUID());

        String signKey = appCode + appSecretKey + requestId + timestamp;
        String digestHex = SmUtil.sm3(signKey);
        System.out.println(timestamp);
        System.out.println(requestId);
        System.out.println(digestHex);
    }
}
