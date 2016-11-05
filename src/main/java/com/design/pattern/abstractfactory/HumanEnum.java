package com.design.pattern.abstractfactory;

/**
 * @author dongxiaoman I'm glad to share my knowledge with you all. *
 *         世界上有哪些类型的人,列出来 JDK 1.5开始引入enum类型也是目的的,吸引C程序员转过来
 */

public enum HumanEnum {

	// 把世界上所有的人种都定义出来
	YellowMaleHuman("com.design.pattern.abstractfactory.YellowMaleHuman"),
	YellowWomanHuman("com.design.pattern.abstractfactory.YellowFemaleHuman"),
	WhiteMaleHuman("白种男人"),
	WhiteWomanHuman("白种男人"),
	BlackMaleHuman("黑种男人"),
	BlackWomanHuman("黑种男人");

	private String value = "";

	public String getValue() {
		return this.value;
	}

	// 定义构造函数,目的是Data(value)类型的相匹配
	private HumanEnum(String value) {
		this.value = value;
	}
}
