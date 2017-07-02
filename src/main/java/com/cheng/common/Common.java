package com.cheng.common;

import com.cheng.helper.Timer;
import com.cheng.login.HeFengWeather;
import com.cheng.login.Weather;

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
    public static volatile boolean isWordOnFrame = false;

    // 现在有两个天气接口，要是需要更换接口，直接在这里改就行了了
    public static List<String> getWeathers() {
        return HeFengWeather.getweather();
    }


    public static void updateWeathers() {
        weathers = getWeathers();
    }
}
