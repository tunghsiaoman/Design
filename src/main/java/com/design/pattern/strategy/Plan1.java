package com.design.pattern.strategy;

/*
 * 锦囊妙计一：找乔国老帮忙，使孙权不能杀刘备
 */
public class Plan1 implements IStrategy {

	@Override
	public void operate() {
		System.out.println("找乔国老帮忙,让吴国太给孙权施加压力");
	}

}
