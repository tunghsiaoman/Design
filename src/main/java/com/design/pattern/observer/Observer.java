package com.design.pattern.observer;

/**
 * 所有观察者都应该实现这个接口
 * @author xiaoman.dong
 * @date 2017-04-03
 */
public interface Observer {
	
	public void update(String msg);
}
