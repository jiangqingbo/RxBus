package com.huyunit.rxbus2.event;

import com.huyunit.rxbus2.RxBus;

/**
 * author: bobo
 * create time: 2016/12/15 10:52
 * Email: jqbo84@163.com
 */
public class DeadEvent {
    public final Object source;
    public final Object event;

    /**
     * Creates a new DeadEvent.
     *
     * @param source object broadcasting the DeadEvent (generally the {@link RxBus}).
     * @param event  the event that could not be delivered.
     */
    public DeadEvent(Object source, Object event) {
        this.source = source;
        this.event = event;
    }
}
