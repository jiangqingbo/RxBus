package com.huyunit.rxbus2;

import com.huyunit.rxbus2.thread.ThreadEnforcer;

/**
 * Instance of {@link RxBusManager}.
 * Simply use {@link #get()} to get the instance of {@link RxBusManager}
 *
 * author: bobo
 * create time: 2016/12/15 10:13
 * Email: jqbo84@163.com
 */
public class RxBus {

    /**
     * Instance of {@link RxBusManager}
     */
    private static RxBusManager rxBusManager;

    /**
     * Get the instance of {@link RxBusManager}
     *
     * @return
     */
    public static synchronized RxBusManager get() {
        if (rxBusManager == null) {
            rxBusManager = new RxBusManager(ThreadEnforcer.ANY);
        }
        return rxBusManager;
    }
}
