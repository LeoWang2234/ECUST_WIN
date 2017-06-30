package com.cheng.helper;

import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by cheng on 2017/6/30.
 */
public class Timer {
    private static java.util.concurrent.ScheduledExecutorService globalTimer = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();

    private Timer() {

    }

    public static ScheduledExecutorService getGlobalTimer() {
        return globalTimer;
    }
}
