package com.Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Tinyhome on 16/9/7.
 */
public class EncryptUtil {
    //简单md5
    public static String md5(String str)   {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        // 添加 计算摘要的信息
        messageDigest.update(str.getBytes());
        //获取密文(byte数组)
        byte[] mdResult = messageDigest.digest();


        StringBuffer stringResult = new StringBuffer("");
        for (int offset = 0; offset < mdResult.length; offset++) {
            int i = mdResult[offset];
            if (i < 0)
                i += 256;
            if (i < 16)
                stringResult.append("0");
            stringResult.append(Integer.toHexString(i));
        }
        return stringResult.toString();
    }
}
