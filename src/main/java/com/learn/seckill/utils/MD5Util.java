package com.learn.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @program: seckill:MD5Util
 * @description: 加密
 * @author: zcf
 * @create: 2019-11-26 12:42
 **/
public class MD5Util {
    private static final String salt = "1a2b3c4d";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String inputPassFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input, String saltDB) {
        String formPass = inputPassFormPass(input);
        String dbPass = formPassToDBPass(formPass,saltDB);
        return dbPass;
    }

    public static void main(String[] args) {
        System.out.println("args = " + args);
        System.out.println("inputPassFormPass(\"123456\") = " + inputPassToDbPass("123456","1a2b3c4d"));
    }

}
