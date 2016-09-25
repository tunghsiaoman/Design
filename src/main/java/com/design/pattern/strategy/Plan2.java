package com.design.pattern.strategy;

/**
 * 锦囊妙计二：求吴国太开个绿灯
 * @author YK--DZ-3591606
 *
 */
public class Plan2 implements IStrategy {

	@Override
	public void operate() {
		System.out.println("求吴国太放个绿灯，放行！");
	}

}
