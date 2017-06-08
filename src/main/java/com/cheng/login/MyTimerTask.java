package com.cheng.login;

import com.cheng.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 * Created by login on 2017/6/8.
 *
 * 定时检测是否掉线
 */
class MyTimerTask extends TimerTask {

    MainFrame mainFrame;
    public MyTimerTask(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }
    public void run() {
        Boolean status = OnlineStatus.sendGet("http://www.baidu.com");
        if (status) {
            mainFrame.ignorePop = false;
            mainFrame.setAlwaysOnTop(false);
            new Thread(new Runnable() {
                public void run() {
                    mainFrame.statusLabel.setText("当前在线");
                    mainFrame.statusLabel.setForeground(Color.BLUE);
                    mainFrame.jb2.setText("无线登录");
                    mainFrame.jb2.setForeground(Color.black);
                    mainFrame.jb1.setText("有线登录");
                    mainFrame.jb1.setForeground(Color.black);
                }
            }).start();

        }else {
            status = OnlineStatus.sendGet("http://www.baidu.com");
            // 若是判断掉线啦，则再重新请求一次，防止误判，但是有可能会增加响应时间
            // 如果在线，再继续循环
            if (status) {
                return;
            }
            new Thread(new Runnable() {
                public void run() {
                    if (!mainFrame.ignorePop) {
                        System.out.println(mainFrame.ignorePop);
                        mainFrame.statusLabel.setText("掉线啦 点我忽略");
                        mainFrame.statusLabel.setForeground(Color.RED);
                        mainFrame.setState(JFrame.NORMAL);
                        mainFrame.setLocation(mainFrame.xLocation, mainFrame.yLocation);//设置窗口居中显示
                        mainFrame.setAlwaysOnTop(true);
                    }else {
                        mainFrame.statusLabel.setText("掉线啦");
                        mainFrame.statusLabel.setForeground(Color.RED);
                    }
                    mainFrame.jb2.setText("无线登录");
                    mainFrame.jb2.setForeground(Color.black);
                    mainFrame.jb1.setText("有线登录");
                    mainFrame.jb1.setForeground(Color.black);
                }
            }).start();

        }
    }
}
