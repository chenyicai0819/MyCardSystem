package com.chen.mycardsystembackstage.utils.SM4;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Project : MyCardSystem - SM4Run
 * Powered by GeorgeChen On 2023-12-14 19:05:05
 *
 * @author GeorgeChen
 * @version 1.0
 * @since 1.8
 **/
public class SM4Run {

    public static void main(String[] args) {
        String key = "24ec4f8e-f657-4f9e-8b06-8eccfe575205";
        String iv = "UISwD9fW6cFh9SNS";

        String plainText = "{\n" +
                "        \"zjhm\": \"460106201706054415\",\n" +
                "        \"zjlx\": \"01\"\n" +
                "    }";
        // String plainText = "{\n" +
        //         "\"Person\": {\n" +
        //         "\"zjhm\": \"450923201711072288\",\n" +
        //         "\"zjlx\": \"01\"\n" +
        //         "},\n" +
        //         "\"Event\": {},\n" +
        //         "\"BeginDate\": \"20200101\",\n" +
        //         "\"EndDate\": \"20230809\"\n" +
        //         "}\n";

        // String cipherText = SM4Utils.encryptData_CBC(plainText,key,iv);
        // System.out.println(cipherText);
        String tt = "";
        System.out.println(SM4Utils.decryptData_CBC(tt, key, iv));
    }
}
