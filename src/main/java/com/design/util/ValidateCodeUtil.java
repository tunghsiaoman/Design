package com.design.util;

import java.util.Random;

/**
 * 生成活动验证码工具类
 *
 * @author sihang
 */
public class ValidateCodeUtil {

    /**
     * 生成验证码
     *
     * @return
     */
    public static String provideValidateCode() {
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();

        for (int i = 10; i > 1; i--) {
            int index = rand.nextInt(i);
            int tmp = array[index];
            array[index] = array[i - 1];
            array[i - 1] = tmp;
        }

        StringBuffer result = new StringBuffer("");
        for (int i = 0; i < 6; i++) {
            result.append(array[i]);
        }

        return result.toString();
    }

    /**
     * 生成固定位数的验证码
     *
     * @param count 生成验证码的位数
     * @return String
     */
    public static String provideValidateCodeWithCount(final int count) {
        StringBuffer result = new StringBuffer("");
        int[] array = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random rand = new Random();

        for (int i = 0; i < count; i++) {
            result.append(array[Math.abs(rand.nextInt() % 10)]);
        }

        return result.toString();
    }

}
