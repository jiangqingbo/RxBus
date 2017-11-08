package com.huyunit.rxbus;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author: bobo
 * create time: 2017/11/6 下午4:55
 * email: jqbo84@163.com
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Subscribe {

    int code() default -1;

    ThreadMode threadMode() default ThreadMode.CURRENT_THREAD;

}
