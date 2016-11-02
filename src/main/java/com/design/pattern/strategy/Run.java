package com.design.pattern.strategy;

public class Run {
	public static void main(String[] args) {
		Context context;
		
		System.out.println("--开启第一个锦囊--");
		
		context = new Context(new Plan1());	// 拿出锦囊
		context.operate();	// 拆开
	
		System.out.println("--开启第二个锦囊--");
		context = new Context(new Plan2());
		context.operate();
		
		System.out.println("--拆开第三个锦囊--");
		context = new Context(new Plan3());
		context.operate();
		
	}
}
