package com.design.pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 3D服务号的实现
 * @author xiaoman.dong
 * @date 2017-04-03
 */
public class Object43D implements Subject {
	
	private List<Observer> observerList = new ArrayList<Observer>();
	
	/**
	 * 3D彩票的号码
	 */
	private String msg;
	
	/**
	 * 更新主题消息
	 * @param msg
	 */
	public void setMsg(String msg) {
		this.msg = msg;
		notifyObserver();
	}

	@Override
	public void registerObserver(Observer observer) {
		// TODO Auto-generated method stub
		observerList.add(observer);
	}

	@Override
	public void deleteObserver(Observer observer) {
		// TODO Auto-generated method stub
		int index = observerList.indexOf(observer);
		if(index >= 0){
			observerList.remove(observer);
		}
	}

	@Override
	public void notifyObserver() {
		// TODO Auto-generated method stub
		for (Observer observer : observerList) {
			observer.update(msg);
		}
	}

}
