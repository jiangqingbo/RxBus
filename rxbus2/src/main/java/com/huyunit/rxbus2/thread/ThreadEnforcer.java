package com.huyunit.rxbus2.thread;

import android.os.Looper;

import com.huyunit.rxbus2.RxBusManager;

/**
 * author: bobo
 * create time: 2016/12/15 10:23
 * Email: jqbo84@163.com
 */
public interface ThreadEnforcer {
    /**
     * Enforce a valid thread for the given {@code bus}. Implementations may throw any runtime exception.
     *
     * @param bus Event bus instance on which an action is being performed.
     */
    void enforce(RxBusManager bus);


    /**
     * A {@link ThreadEnforcer} that does no verification.
     */
    ThreadEnforcer ANY = new ThreadEnforcer() {
        @Override
        public void enforce(RxBusManager bus) {
            // Allow any thread.
        }
    };

    /**
     * A {@link ThreadEnforcer} that confines {@link RxBusManager} methods to the main thread.
     */
    ThreadEnforcer MAIN = new ThreadEnforcer() {
        @Override
        public void enforce(RxBusManager bus) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                throw new IllegalStateException("Event bus " + bus + " accessed from non-main thread " + Looper.myLooper());
            }
        }
    };
}
