package com.design.pattern.strategy;

/**
 * 孙夫人断后，挡住追兵
 * @author YK--DZ-3591606
 *
 */
public class Plan3 implements IStrategy {

	@Override
	public void operate() {
		System.out.println("孙夫人断后，挡住追兵！");
	}

}
