package com.design.dao.asyn;

public interface AsyncDaoCallback<T> {
	
	void onSuccess(T result);
	
	void onException(Exception e);

}
