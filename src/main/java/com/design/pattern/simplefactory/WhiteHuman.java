package com.design.pattern.simplefactory;
/**
 * 定义人种-白人
 * @author tunghsiaoman
 * @date 2016-11-02
 */
public class WhiteHuman implements Human {

	@Override
	public void smile() {
		System.out.println("白种人会笑！");
	}

	@Override
	public void cry() {
		System.out.println("白种人会哭！");
	}

	@Override
	public void talk() {
		System.out.println("白种人会说话！说English");
	}

}
