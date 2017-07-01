package com.cheng.login;

import com.cheng.ui.MainFrame;
import sun.jvm.hotspot.ui.table.LongCellRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

/**
 * Created by login on 2017/6/8.
 *
 * 定时检测是否掉线
 */
class OnlineTestTask extends TimerTask {

    MainFrame mainFrame;

    public OnlineTestTask(final MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        mainFrame.weatherLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                mainFrame.remove(mainFrame.weatherLabel);
                mainFrame.add(mainFrame.jp3);
                mainFrame.jp3.updateUI();
            }
        });
    }

    public void run() {

        Boolean status = OnlineStatus.sendGet();
        if (status) {
            mainFrame.ignorePop = false;
            mainFrame.setAlwaysOnTop(false);

            new Thread(new Runnable() {
                public void run() {
                    mainFrame.remove(mainFrame.jp3);
                    mainFrame.add(mainFrame.weatherLabel);
                    mainFrame.weatherLabel.updateUI();
                    mainFrame.statusLabel.setText("当前在线");
                    mainFrame.statusLabel.setFont(new Font("",Font.BOLD,15));
                    mainFrame.statusLabel.setForeground(Color.BLUE);
                    mainFrame.jb2.setText("无线登录");
                    mainFrame.jb2.setForeground(Color.black);
                    mainFrame.jb1.setText("有线登录");
                    mainFrame.jb1.setForeground(Color.black);
                }
            }).start();

        }else {
            // 一秒后再重新请求一次，防止误判
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            status = OnlineStatus.sendGet();
            // 若是判断掉线啦，则再重新请求一次，防止误判，但是有可能会增加响应时间
            // 如果在线，再继续循环
            if (status) {
                return;

            }

            new Thread(new Runnable() {
                public void run() {
                    mainFrame.remove(mainFrame.weatherLabel);
                    mainFrame.add(mainFrame.jp3);
                    mainFrame.jp3.updateUI();
                    if (!mainFrame.ignorePop) {
                        mainFrame.statusLabel.setText("掉线啦 点我忽略");
                        mainFrame.statusLabel.setForeground(Color.RED);
                        mainFrame.setState(JFrame.NORMAL);

                        // 当前 登录页面没有获得焦点，则将弹窗位置设置在屏幕中间
                        if (mainFrame.getFocusOwner() == null) {
                            mainFrame.setLocation(mainFrame.xLocation, mainFrame.yLocation);//设置窗口居中显示
                        }
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
