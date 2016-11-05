package com.design.pattern.abstractfactory;
/**
* @author dongxiaoman
* I'm glad to share my knowledge with you all. * 
* 为什么要修改成抽象类呢?要定义性􏰀呀
*/
public abstract class AbstractWhiteHuman implements Human {
	@Override
	public void smile() {
		// TODO Auto-generated method stub
		System.out.println("白人-笑");
	}
	
	@Override
	public void cry() {
		// TODO Auto-generated method stub
		System.out.println("白人-哭");
	}
	
	@Override
	public void talk() {
		// TODO Auto-generated method stub
		System.out.println("白人-说");
	}
}
