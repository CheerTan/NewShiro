package com.learn.shirologin.util;


import org.apache.commons.codec.digest.DigestUtils;

public class UseMD5 {

    public static String createKey(String password) {
        String actPassword = DigestUtils.md5Hex(Contants.SALT+password);
        return actPassword;

    }

}
