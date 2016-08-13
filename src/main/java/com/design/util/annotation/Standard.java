package com.design.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * JavaBean对应的规格名称。由于规格中全部采用大写字母，中间采用下划线分隔，
 * 不符合JavaBean的命名规范，所以可以在JavaBean的属性上加这个Anontation
 * 来标识属性对应的规格名称。
 * @author chunzhi.jiang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Standard {
	
	/*
	 * 规格名称
	 */
	public String name();

}
