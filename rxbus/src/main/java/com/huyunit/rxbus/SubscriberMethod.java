package com.huyunit.rxbus;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * author: bobo
 * create time: 2017/11/6 下午4:56
 * email: jqbo84@163.com
 */
public class SubscriberMethod {

    public Method method;
    public ThreadMode threadMode;
    public Class<?> eventType;
    public Object subscriber;
    public int code;

    public SubscriberMethod(Object subscriber, Method method, Class<?> eventType, int code,ThreadMode threadMode) {
        this.method = method;
        this.threadMode = threadMode;
        this.eventType = eventType;
        this.subscriber = subscriber;
        this.code = code;
    }


    /**
     * 调用方法
     * @param o
     */
    public void invoke(Object o){
        try {
            method.invoke(subscriber, o);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
