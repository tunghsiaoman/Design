package com.design.util;


import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.net.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


public class DesClass {
    private static String Algorithm = "DESede";

    private static final String Default_Key = "A3F2DEI569WBCJSJEOTY45DYQWF68H1Y";

    public static String encryptString(String value)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {
        return encryptString(Default_Key, value);
    }

    public static String encryptString(String key, String value)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null || "".equals(value)) {
            return "";
        }

        // ������
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try {
            bytesKey = Base64.decodeBase64(key.getBytes("UTF-8"));
            deskey = new SecretKeySpec(bytesKey, Algorithm);

            bytes = value.getBytes("UTF-8");// ����ܵ��ִ�
            desCipher.init(Cipher.ENCRYPT_MODE, deskey);// ��ʼ��������������Կdeskey,�������ģʽ
            bytesCipher = desCipher.doFinal(bytes);
            return (new String(Base64.encodeBase64(bytesCipher), "UTF-8")).trim();
        } finally {
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
    }

    public static String decryptString(String value)
            throws InvalidKeyException, NoSuchAlgorithmException,
            NoSuchPaddingException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {
        return decryptString(Default_Key, value);
    }

    public static String decryptString(String key, String value)
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, UnsupportedEncodingException {

        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null || "".equals(value)) {
            return "";
        }

        // ������
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try {
            bytesKey = Base64.decodeBase64(key.getBytes("UTF-8"));
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            bytes = Base64.decodeBase64(value.getBytes("UTF-8"));// ���ܺ���ִ�
            desCipher.init(Cipher.DECRYPT_MODE, deskey);// ��ʼ��������������Կdeskey,�������ģʽ
            bytesCipher = desCipher.doFinal(bytes);
            return (new String(bytesCipher, "UTF-8"));
        } finally {

        }
    }
}