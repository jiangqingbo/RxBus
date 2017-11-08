package com.huyunit.rxbus2.thread;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * author: bobo
 * create time: 2016/12/15 10:20
 * Email: jqbo84@163.com
 */
public interface ThreadHandler {
    Executor getExecutor();

    Looper getLooper();

    static ThreadHandler DEFAULT = new ThreadHandler() {
        private Executor executor;
        private Looper looper;

        @Override
        public Executor getExecutor() {
            if(executor == null) {
                executor = Executors.newCachedThreadPool();
            }
            return executor;
        }

        @Override
        public Looper getLooper() {
            if(looper == null) {
                looper = Looper.getMainLooper();
            }
            return looper;
        }
    };

}
