package com.design.dao.asyn;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

/**
 * 
 * @author tunghsiaoman
 *
 */
public class AsyncDaoCallableExecutor implements Callable<Object> {
	
	private Object mapper;

	private Method method;

	private Object[] args;
	
	public AsyncDaoCallableExecutor(Object mapper, Method method, Object[] args) {
		this.mapper = mapper;
		this.method = method;
		this.args = args;
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		return method.invoke(mapper, args);
	}
	
}
