/**
 * 策略模式
 */
package com.design.pattern.strategy;


public class Context {
	
	private IStrategy strategy;

	public Context(IStrategy strategy) {
		super();
		this.strategy = strategy;
	}
	
	// 开始使用计谋
	public void operate(){
		this.strategy.operate();
	}
	
}
