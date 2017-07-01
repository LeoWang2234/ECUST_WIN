package com.cheng.common;

import com.cheng.helper.Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;

/**
 * Created by cheng on 2017/7/1.
 *
 * 用来存放程序需要的全局量
 */
public class Common {
    public static boolean online = false;
    public static List<String> weathers = new ArrayList<String>();
    public static ScheduledExecutorService globalTimer = Timer.getGlobalTimer();
}
