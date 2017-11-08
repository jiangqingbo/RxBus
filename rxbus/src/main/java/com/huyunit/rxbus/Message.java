package com.huyunit.rxbus;

/**
 * author: bobo
 * create time: 2017/11/6 下午4:54
 * email: jqbo84@163.com
 */
public class Message {

    private int code;

    private Object object;

    public Message() {

    }

    public Message(int code, Object o) {
        this.code = code;
        this.object = o;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
