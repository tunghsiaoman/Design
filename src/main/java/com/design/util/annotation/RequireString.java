package com.design.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 非空注解，在属性上加上这个注解以后，表示该属性不能为空。
 * 保留到运行时，作用到属性上。
 * @author chunzhi.jiang
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RequireString {

}
