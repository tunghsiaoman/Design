package com.design.pattern.abstractfactory;

public class Boss {
	public static void main(String[] args) {
		
		// 第一条生产线，只生产男性
		HumanFactroy womanHumanFactory = new WomanHumanFactory();
		
		// 第二条生产线，只生产女性
		HumanFactroy maleHumanFactory = new MaleHumanFactory();
		
		// 生产线建造完毕，开始投入生产
		Human maleYellowHuman = maleHumanFactory.createYelloHuman();
		Human womanYellowHuman = womanHumanFactory.createYelloHuman();
		
		maleYellowHuman.smile();
		maleYellowHuman.cry();
		maleYellowHuman.talk();
		
		womanYellowHuman.smile();
		womanYellowHuman.cry();
		womanYellowHuman.talk();
		
		// ...
		
		
	}
}
