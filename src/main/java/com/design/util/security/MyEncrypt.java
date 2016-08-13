package com.design.util.security;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @File SecurityUtil.java
 * @Date 2011-6-28
 * @Author dongjw
 * @Description
 */
public final class MyEncrypt {

    /**
     * 密码加密
     *
     * @param password
     * @param salt
     * @return
     */
    public static String obscure(String password, String salt) {
        StringBuffer sbuf = new StringBuffer();
        sbuf.append("^#").append(password).append(salt).append("#~");
        return MyEncrypt.sha(sbuf.toString());
    }

    /**
     * 生成Token
     *
     * @param userName
     * @param time
     * @param rand
     * @return
     */
    public static String token(String userName, Long time, String rand) {
        return MyEncrypt.sha(new StringBuffer().append("^#").append(userName)
                .append(time).append(rand).append("#~").toString());
    }

    /**
     * md5加密
     *
     * @param inputStr
     * @return
     */
    public static String md5(String inputStr) {
        return encrypt(inputStr, "md5");
    }

    /**
     * sha加密
     *
     * @param inputStr
     * @return
     */
    public static String sha(String inputStr) {
        return encrypt(inputStr, "sha-1");
    }

    /**
     * md5或者sha-1加密
     *
     * @param inputStr      要加密的内容
     * @param algorithmName 加密算法名称：md5或者sha-1，不区分大小写
     * @return
     */
    private static String encrypt(String inputStr, String algorithmName) {
        if (inputStr == null || "".equals(inputStr.trim())) {
            throw new IllegalArgumentException("请输入要加密的内容");
        }
        if (algorithmName == null || "".equals(algorithmName.trim())) {
            algorithmName = "md5";
        }
        String encryptText = null;
        try {
            MessageDigest m = MessageDigest.getInstance(algorithmName);
            m.update(inputStr.getBytes("UTF8"));
            byte s[] = m.digest();
            // m.digest(inputStr.getBytes("UTF8"));
            return hex(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return encryptText;
    }

    /**
     * 返回十六进制字符串
     *
     * @param arr
     * @return
     */
    private static String hex(byte[] arr) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,
                    3));
        }
        return sb.toString();
    }

}
