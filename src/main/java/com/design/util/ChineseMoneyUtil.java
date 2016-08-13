package com.design.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
  
/**  
 * 数字金额转大写  
 *   
 * 上限：16个9 .99  
 *   
 * @author MeltingSnower@hotmail.com  
 *   
 */  
public class ChineseMoneyUtil {   
  
    private static BigDecimal MAX_VALUE = new BigDecimal("9999999999999999.99");   
    private static BigDecimal MIN_VALUE = new BigDecimal("0.01");   
    private static String FORMAT_PATTERN = "0000000000000000.00";   
  
    private static String[] CHINESE_NUMBER = { "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖" };   
    private static String[][] CHINESE_UNIT = { { "", "拾", "佰", "仟" }, { "兆", "亿", "万", "" }, { "角", "分" } };   
  
    private static String[] BASE_UNIT = { "圆", "整" };   
  
    public static String convert(BigDecimal convertValue) {   
        if (convertValue == null || convertValue.compareTo(BigDecimal.ZERO) == 0)   
            return CHINESE_NUMBER[0] + BASE_UNIT[0] + BASE_UNIT[1];   
        if (convertValue.compareTo(MAX_VALUE) > 0 || convertValue.compareTo(MIN_VALUE) < 0)   
            return convertValue.toString();   
        String[] parts = new DecimalFormat(FORMAT_PATTERN).format(convertValue).split("\\.");   
        String result = "";   
        boolean start = false, zero = false, zeroed = false;   
        for (int i = 0; i < 4; i++) {   
            int groupValue = Integer.parseInt(parts[0].substring(i * 4, (i + 1) * 4));   
            if (start)   
                if ((zero || groupValue < 1000) && !zeroed) {   
                    result += CHINESE_NUMBER[0];   
                    zeroed = true;   
                }   
            if (groupValue > 0) {   
                result += groupConvert(groupValue) + CHINESE_UNIT[1][i];   
                start = true;   
                zeroed = false;   
            }   
            if (groupValue > 0 && groupValue % 10 == 0)   
                zero = true;   
            else  
                zero = false;   
        }   
        if (convertValue.compareTo(BigDecimal.ONE) > -1)   
            result += BASE_UNIT[0];   
        if (!parts[1].equals("00")) {   
            if (result.length() > 0 && (parts[0].charAt(parts[0].length() - 1) == '0' || parts[1].charAt(0) == '0'))   
                result += CHINESE_NUMBER[0];   
            for (int i = 0; i < parts[1].length(); i++) {   
                int single = parts[1].charAt(i) - '0';   
                if (single > 0)   
                    result += CHINESE_NUMBER[single] + CHINESE_UNIT[2][i];   
            }   
        } else  
            result += BASE_UNIT[1];   
        
        result= result.replace("零圆","圆");
        result= result.replace("零万","万");
        result= result.replace("零亿","亿");
        result= result.replace("零兆","兆");
    	result= result.replace("零京","京");
    	result= result.replace("零仟","零");
    	result= result.replace("零佰","零");
    	result= result.replace("零零","零");
    	result= result.replace("零拾","零");

    	result= result.replace("京兆","京零");
    	result= result.replace("京亿","京零");
    	result= result.replace("京万","京零");
    	
    	result= result.replace("兆亿","兆零");
    	result= result.replace("兆万","兆零");
    	
    	result= result.replace("亿万","亿零");
    	
    	result= result.replace("万仟","万零");
    	
    	result= result.replace("仟佰","仟零");
        return result;   
  
    }   
  
    private static String groupConvert(int groupValue) {   
        if (groupValue > 9 && groupValue < 20)   
            return CHINESE_UNIT[0][1] + (groupValue % 10 > 0 ? CHINESE_NUMBER[groupValue % 10] : "");   
        String stringValue = String.valueOf(groupValue), result = "";   
        boolean valid = false, zero = false, zerod = false;   
        for (int i = 0; i < stringValue.length(); i++) {   
            int intValue = stringValue.charAt(stringValue.length() - i - 1) - '0';   
            if (intValue > 0) {   
                valid = true;   
                zero = false;   
                zerod = false;   
            } else if (valid)   
                zero = true;   
            if (valid) {   
                if (!zerod)   
                    result = CHINESE_NUMBER[intValue] + (zero ? "" : CHINESE_UNIT[0][i]) + result;   
                if (zero)   
                    zerod = true;   
            }   
        }   
        return result;   
    }   
}  
