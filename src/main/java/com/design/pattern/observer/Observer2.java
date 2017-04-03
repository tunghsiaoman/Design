package com.design.pattern.observer;

/**
 * 模拟观察者01
 * @author xiaoman.dong
 * @date 2017-04-03
 */
public class Observer2 implements Observer {
	
	private Subject subject;
	
	public Observer2(Subject subject) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
		subject.registerObserver(this);
	}

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		System.out.println("Observer2 得到3D号码 -->" + msg + ", 我要告诉小伙伴");
	}

}
