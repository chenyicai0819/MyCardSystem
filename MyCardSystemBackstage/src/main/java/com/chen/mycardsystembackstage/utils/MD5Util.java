package com.chen.mycardsystembackstage.utils;

import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author xumj
 * @date 2021-10-28
 */
@Component
public class MD5Util {


    /**
     * 把字节数组转成16进位制数
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer md5str = new StringBuffer();
        //把数组每一字节换成16进制连成md5字符串
        int digital;
        for (int i = 0; i < bytes.length; i++) {
            digital = bytes[i];
            if (digital < 0) {
                digital += 256;
            }
            if (digital < 16) {
                md5str.append("0");
            }
            md5str.append(Integer.toHexString(digital));
        }
        return md5str.toString();
    }

    /**
     * 把字节数组转换成md5
     *
     * @param input
     * @return
     */
    public static String bytesToMD5(byte[] input) {
        String md5str = null;
        try {
            //创建一个提供信息摘要算法的对象，初始化为md5算法对象
            MessageDigest md = MessageDigest.getInstance("MD5");
            //计算后获得字节数组
            byte[] buff = md.digest(input);
            //把数组每一字节换成16进制连成md5字符串
            md5str = bytesToHex(buff);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return md5str;
    }

    /**
     * 将字符串进行md5加密
     *
     * @param str 需要进行加密的字符串
     * @return  加密后的字符串
     */
    private static String strToMD5(String str) {
        byte[] input = str.getBytes();
        return bytesToMD5(input);
    }

    /**
     * 2019-06-22 xumj 统一编码 UTF-8
     */
    private static String strToMD5ForUTF8(String str) {
        try {
            byte[] input = str.getBytes("UTF-8");
            return bytesToMD5(input);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**对密码加密
     * @param password 要加密的密码
     * @return 返回加密后的密码
     */
    public static String encryptPassword(String password) {
        String encryptedPwd=MD5Util.strToMD5(MD5Util.strToMD5(password));//对密码做两次加密
        return encryptedPwd;
    }

    /**
     * 2019-06-22 xumj 统一编码 UTF-8
     */
    public static String encryptPasswordForUTF8(String password) {
        String encryptedPwd=MD5Util.strToMD5ForUTF8(MD5Util.strToMD5ForUTF8(password));//对密码做两次加密
        return encryptedPwd;
    }



    public static void main(String[] args) {
        System.out.println(strToMD5("123456"));
        String encryptedPwd=MD5Util.encryptPassword("123456");//对密码做两次加密
        System.out.println("两次加密后的密文:"+encryptedPwd);
        //如何防止md5加密后的密码被破解？
        //换其他的算法 例如RSA. 或者你可以选择两次MD5或者多次MD5
        System.out.println(strToMD5("ojzCgwj4I0labdd-wFv6s45Y5WQU"));
    }

}
