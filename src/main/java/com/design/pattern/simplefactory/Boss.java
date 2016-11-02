package com.design.pattern.simplefactory;

/**
 * 定义女娲-大BOSS
 * 
 * @author tunghsiaoman
 * @date 2016-11-02
 */
public class Boss {
	public static void main(String[] args) {
		// 女娲第一次造人,试验性质,少造点,火候不足,缺陷产品
		System.out.println("------------造出的第一批人是这样的:白人 -----------------");

		Human whiteHuman = HumanFactory.createHuman(WhiteHuman.class);
		whiteHuman.smile();
		whiteHuman.cry();
		whiteHuman.talk();

		// 女娲第二次造人,火候加足点,然后又出了个次品,黑人
		System.out.println("\n\n------------造出的第二批人是这样的:黑人 -----------------");
		Human blackHuman = HumanFactory.createHuman(BlackHuman.class);
		blackHuman.cry();
		blackHuman.smile();
		blackHuman.talk();

		// 第三批人了,这次火候掌握的正好,黄色人种(不写黄人,免得引起歧义), 备注:RB人不属于此列
		System.out.println("\n\n------------造出的第三批人是这样的:黄色 人种-----------------");
		Human yellowHuman = HumanFactory.createHuman(YellowHuman.class);
		yellowHuman.cry();
		yellowHuman.smile();
		yellowHuman.talk();
		
		
		System.out.println("但是这样造人好累啊，非得一个个塞进去，一个个的造吗？？？接HumanFactory.createHuman()");
		
		System.out.println("终于！女娲BOSS烦躁了，一天到晚的造人，都没时间逛淘宝了！爱是哪种人就是哪种人吧！烧吧！");
		
		for (int i = 0; i < 10; i++) {
			System.out.println("\n\n------------随机产生人种了-----------------" + i);
			Human human = HumanFactory.createHuman();
			human.cry();
			human.smile();
			human.talk();
		}
		

	}
}
