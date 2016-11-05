package com.design.annotation;

public @interface MyAnnotation {

	public int type();
	
	public String msg() default "";
	
}
