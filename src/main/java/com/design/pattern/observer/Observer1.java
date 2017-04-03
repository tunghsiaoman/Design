package com.design.pattern.observer;

/**
 * 模拟观察者01
 * @author xiaoman.dong
 * @date 2017-04-03
 */
public class Observer1 implements Observer {
	
	private Subject subject;
	
	public Observer1(Subject subject) {
		// TODO Auto-generated constructor stub
		this.subject = subject;
		subject.registerObserver(this);
	}

	@Override
	public void update(String msg) {
		// TODO Auto-generated method stub
		System.out.println("Observer1 得到3D号码 -->" + msg + ", 我要记录下来");
	}

}
