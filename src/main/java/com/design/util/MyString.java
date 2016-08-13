package com.design.util;

import java.util.Random;

/**
 * @File RString.java
 * @Date: 14-6-4
 * @Author dongjw
 * @Description
 */
public final class MyString {

    /**
     * 缓存前缀，用于Memcached缓存分组
     */
    public static final String CACHE_CAPTCHA = "captcha";
    public static final String ALL_CHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTER_CHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBER_CHAR = "0123456789";
    public static final String NUMBER_CHAR_NO = "123456789";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(ALL_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(LETTER_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯数字字符串(只包含数字)
     *
     * @param length 随机字符串长度
     * @return
     */
    public static String generateNumString(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(NUMBER_CHAR.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯数字字符串(只包含数字)
     *
     * @param length 随机字符串长度
     * @return
     */
    public static String generateNumStringNo(int length) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALL_CHAR.charAt(random.nextInt(NUMBER_CHAR_NO.length())));
        }
        return sb.toString();
    }

    /**
     * 返回一个定长的随机纯大写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含大小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */

    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

    /**
     * 生成一个定长的纯0字符串
     *
     * @param length 字符串长度
     * @return 纯0字符串
     */
    public static String generateZeroString(int length) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            sb.append('0');
        }
        return sb.toString();
    }

    /**
     * 根据数字生成一个定长的字符串，长度不够前面补0
     *
     * @param num       数字
     * @param fixdlenth 字符串长度
     * @return 定长的字符串
     */

    public static String toFixdLengthString(long num, int fixdlenth) {
        StringBuffer sb = new StringBuffer();
        String strNum = MyString.getString(num);
        if (fixdlenth - strNum.length() >= 0) {
            sb.append(generateZeroString(fixdlenth - strNum.length()));
        } else {
            throw new RuntimeException("将数字" + num + "转化为长度为" + fixdlenth + "的字符串发生异常!");
        }
        sb.append(strNum);
        return sb.toString();
    }

    /**
     * Object转换成Long
     *
     * @param obj
     * @return
     */
    public static Long getLong(Object obj) {
        return getLong(obj, 0L);
    }

    /**
     * Object转换成Long
     *
     * @param obj
     * @return
     */
    public static Long getLong(Object obj, Long defaultValue) {
        try {
            return Long.valueOf(obj + "");
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Object转换成Double
     *
     * @param obj
     * @param defaultValue
     * @return
     */
    public static Double getDouble(Object obj, Double defaultValue) {
        try {
            return Double.valueOf(obj + "");
        } catch (Exception ex) {
            return defaultValue;
        }

    }

    /**
     * Object转换成Double
     *
     * @param obj
     * @return
     */
    public static Double getDouble(Object obj) {
        try {
            return Double.valueOf(obj + "");
        } catch (Exception ex) {
            return 0.00;
        }

    }

    /**
     * Object转换成Integer
     *
     * @param obj
     * @return
     */
    public static Integer getInteger(Object obj) {
        return getInteger(obj, 0);
    }

    /**
     * Object转换成Integer
     *
     * @param obj
     * @return
     */
    public static Integer getInteger(Object obj, Integer defaultValue) {
        try {
            return Integer.valueOf(obj + "");
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Object转换成String
     *
     * @param obj
     * @return
     */
    public static String getString(Object obj) {
        return getString(obj, "");
    }

    /**
     * Object转换成String
     *
     * @param obj
     * @return
     */
    public static String getString(Object obj, String defaultValue) {
        try {
            if (obj == null) {
                return defaultValue;
            }
            return String.valueOf(obj);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Object转换成Boolean
     *
     * @param obj
     * @return
     */
    public static Boolean getBoolean(Object obj) {
        try {
            return Boolean.valueOf(obj + "");
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断字符是否为数字字符
     *
     * @param ch
     * @return
     */
    public static Boolean isNumber(char ch) {
        if (ch >= '0' && ch <= '9') {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否为'A-Z' 或者'a'-'z'
     *
     * @param ch
     * @return
     */
    public static Boolean isChar(char ch) {
        if (ch >= 'A' && ch <= 'Z') {
            return true;
        } else if (ch >= 'a' && ch <= 'z') {
            return true;
        }
        return false;
    }

}
