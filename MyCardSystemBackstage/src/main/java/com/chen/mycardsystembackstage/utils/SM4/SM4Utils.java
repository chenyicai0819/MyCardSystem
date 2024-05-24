package com.chen.mycardsystembackstage.utils.SM4;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SM4Utils {

    private static boolean hexString = false;

    public SM4Utils() {
    }

    private static byte[] getkey(String key) {
        try {
            byte[] keyBytes = new byte[16];
            byte[] t = new byte[0];

            t = key.getBytes("UTF-8");

            if (t.length < 16) {
                for (int i = 0; i < t.length; i++) {
                    keyBytes[i] = t[i];
                }
            } else {
                keyBytes = t;
            }
            return keyBytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptData_ECB(String plainText,String secretKey) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = SM4ByteUtil.hexStringToBytes(secretKey);
            } else {
                keyBytes = getkey(secretKey);
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_ecb(ctx, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptData_ECB(String cipherText,String secretKey) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            if (hexString) {
                keyBytes = SM4ByteUtil.hexStringToBytes(secretKey);
            } else {
                keyBytes = getkey(secretKey);
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_ecb(ctx, new BASE64Decoder().decodeBuffer(cipherText));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String encryptData_CBC(String plainText,String secretKey,String iv) {
        try {
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_ENCRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = SM4ByteUtil.hexStringToBytes(secretKey);
                ivBytes = SM4ByteUtil.hexStringToBytes(iv);
            } else {
                keyBytes = getkey(secretKey);
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_enc(ctx, keyBytes);
            byte[] encrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, plainText.getBytes("UTF-8"));
            String cipherText = new BASE64Encoder().encode(encrypted);
            if (cipherText != null && cipherText.trim().length() > 0) {
                Pattern p = Pattern.compile("\\s*|\t|\r|\n");
                Matcher m = p.matcher(cipherText);
                cipherText = m.replaceAll("");
            }
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decryptData_CBC(String cipherText,String secretKey,String iv) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\WeChat Files\\WeChat Files\\wxid_0jt0aw1r3yls32\\FileStorage\\File\\2024-01\\text.txt"));
            StringBuffer sb = new StringBuffer();
            String line;
            while ((line = reader.readLine()) != null){
                sb.append(line);
            }
            String longString = sb.toString();
            SM4Context ctx = new SM4Context();
            ctx.isPadding = true;
            ctx.mode = SM4.SM4_DECRYPT;

            byte[] keyBytes;
            byte[] ivBytes;
            if (hexString) {
                keyBytes = SM4ByteUtil.hexStringToBytes(secretKey);
                ivBytes = SM4ByteUtil.hexStringToBytes(iv);
            } else {
                keyBytes = getkey(secretKey);
                ivBytes = iv.getBytes();
            }

            SM4 sm4 = new SM4();
            sm4.sm4_setkey_dec(ctx, keyBytes);
            byte[] decrypted = sm4.sm4_crypt_cbc(ctx, ivBytes, new BASE64Decoder().decodeBuffer(longString));
            return new String(decrypted, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String strEncode (String value,String secretKey) throws IOException {
        SM4Utils sm4Utils = new SM4Utils();
        //sm4Utils.secretKey = key;
        sm4Utils.hexString = false;
        String s = sm4Utils.encryptData_ECB(value,secretKey);
        s = s.trim().replaceAll("\r|\n","");
        s = s.replace("+","%2B");
        s = s.replace("/","%2F");
        return s;
    }

    public static String strDecode (String value,String secretKey) throws IOException {
        value = value.replace("%2B","+");
        value = value.replace("%2F","/");
        SM4Utils sm4Utils = new SM4Utils();
        //sm4Utils.secretKey = key;
        sm4Utils.hexString = false;
        String s = sm4Utils.decryptData_ECB(value,secretKey);
        return s;
    }

  
}
