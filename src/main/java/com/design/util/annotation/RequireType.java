package com.design.util.annotation;

import com.design.util.enumeration.FieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 成员变量类型
 * @author chunzhi.jiang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequireType {
	
	public FieldType value() default FieldType.STRING;

}
