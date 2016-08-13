package com.design.util;


import java.text.SimpleDateFormat;
import java.util.Date;


public class UserUtil {
    /**
     * 银行账号 格式化  如：(尾号:8888）
     * 许松
     */
    public static String formatCardNo(String cardNo) {
        //String str="(尾号:";
        String str = "";
        for (int i = 1; i < cardNo.length() - 7; i++) {
            str += "*";
            if (i % 4 == 0)
                str += " ";
        }
        return cardNo.substring(0, 4) + str + cardNo.substring(cardNo.length() - 4, cardNo.length());
        //return str+cardNo.substring(cardNo.length()-4, cardNo.length())+")";
    }



    /**
     * 长整型ip转换为string
     *
     * @param long型ip
     * @return
     */
    public static String getLongIpToString(long ipLong) {

        long mask[] = {0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000};
        long num = 0;
        StringBuffer ipInfo = new StringBuffer();
        for (int i = 0; i < 4; i++) {
            num = (ipLong & mask[i]) >> (i * 8);
            if (i > 0) {
                ipInfo.insert(0, ".");
            }
            ipInfo.insert(0, Long.toString(num, 10));
        }
        return ipInfo.toString();
    }

    /**
     * 生成合同编号 的字符串 规则是：当前日期yyyyMMdd_担保人编号_投资id
     */
    public static String getAndCreateContractCode(String bussiness) {
        SimpleDateFormat simple = new SimpleDateFormat("yyyyMMdd");
        return simple.format(new Date()) + bussiness;
    }

}
