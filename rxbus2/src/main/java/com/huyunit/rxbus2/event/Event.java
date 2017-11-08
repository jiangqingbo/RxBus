package com.huyunit.rxbus2.event;

import java.lang.reflect.InvocationTargetException;

/**
 * author: bobo
 * create time: 2016/12/15 10:53
 * Email: jqbo84@163.com
 */
public abstract class Event {
    /**
     * Throw a {@link RuntimeException} with given message and cause lifted from an {@link
     * InvocationTargetException}. If the specified {@link InvocationTargetException} does not have a
     * cause, neither will the {@link RuntimeException}.
     */
    public void throwRuntimeException(String msg, InvocationTargetException e) {
        throwRuntimeException(msg, e.getCause());
    }

    /**
     * Throw a {@link RuntimeException} with given message and cause lifted from an {@link
     * InvocationTargetException}. If the specified {@link InvocationTargetException} does not have a
     * cause, neither will the {@link RuntimeException}.
     */
    public void throwRuntimeException(String msg, Throwable e) {
        Throwable cause = e.getCause();
        if (cause != null) {
            throw new RuntimeException(msg + ": " + cause.getMessage(), cause);
        } else {
            throw new RuntimeException(msg + ": " + e.getMessage(), e);
        }
    }
}
