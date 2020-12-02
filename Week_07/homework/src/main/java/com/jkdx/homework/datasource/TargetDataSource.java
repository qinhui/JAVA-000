package com.jkdx.homework.datasource;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author qinhui
 * @version 1.0 2020/12/3
 */

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented

public @interface TargetDataSource {

    String name();

    boolean readOnly() default false;
}
