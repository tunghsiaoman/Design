package com.design.pattern.observer;

public class Test {
	public static void main(String[] args) {
		// 模拟一个3D服务号
		Object43D suject43d = new Object43D();
		Observer observer1 = new Observer1(suject43d);  
        Observer observer2 = new Observer2(suject43d);
        
        suject43d.setMsg("所有订阅了我的都会收到消息" );  
	}
}
