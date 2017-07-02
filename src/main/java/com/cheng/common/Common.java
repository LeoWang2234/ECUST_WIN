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
 * ������ų�����Ҫ��ȫ����
 */
public class Common {
    public static boolean online = false;
    public static List<String> weathers = new ArrayList<String>();
    public static ScheduledExecutorService globalTimer = Timer.getGlobalTimer();
    public static volatile boolean isWordOnFrame = false;

    // ���������������ӿڣ�Ҫ����Ҫ�����ӿڣ�ֱ��������ľ�������
    public static List<String> getWeathers() {
        return HeFengWeather.getweather();
    }


    public static void updateWeathers() {
        weathers = getWeathers();
    }
}
