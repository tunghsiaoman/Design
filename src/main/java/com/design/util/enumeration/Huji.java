package com.design.util.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * 户籍
 * @author lcp
 *
 */
public class Huji {

	private static final Map<Integer, String> sheng_map = new HashMap<Integer, String>();
	
	static {
		sheng_map.put(11, "北京");
		sheng_map.put(12, "天津");
		sheng_map.put(13, "河北");
		sheng_map.put(14, "山西");
		sheng_map.put(15, "内蒙古");
		sheng_map.put(21, "辽宁");
		sheng_map.put(22, "吉林");
		sheng_map.put(23, "黑龙江");
		sheng_map.put(31, "上海");
		sheng_map.put(32, "江苏");
		sheng_map.put(33, "浙江");
		sheng_map.put(34, "安徽");
		sheng_map.put(35, "福建");
		sheng_map.put(36, "江西");
		sheng_map.put(37, "山东");
		sheng_map.put(41, "河南");
		sheng_map.put(42, "湖北");
		sheng_map.put(43, "湖南");
		sheng_map.put(44, "广东");
		sheng_map.put(45, "广西");
		sheng_map.put(46, "海南");
		sheng_map.put(50, "重庆");
		sheng_map.put(51, "四川");
		sheng_map.put(52, "贵州");
		sheng_map.put(53, "云南");
		sheng_map.put(54, "西藏");
		sheng_map.put(61, "陕西");
		sheng_map.put(62, "甘肃");
		sheng_map.put(63, "青海");
		sheng_map.put(64, "宁夏");
		sheng_map.put(65, "新疆");
		sheng_map.put(71, "台湾");
		sheng_map.put(81, "香港");
		sheng_map.put(82, "澳门");
		sheng_map.put(91, "国外");
	}

	/**
	 * 通过id得到学历的文字标题
	 * 
	 * @param id
	 * @return
	 */
	public static String getShengTitleById(Integer id) {
		return sheng_map.get(id);
	}

}
