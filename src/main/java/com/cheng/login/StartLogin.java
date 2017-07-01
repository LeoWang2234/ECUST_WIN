package com.cheng.login;
import com.cheng.ui.MainFrame2;

import java.awt.*;   //导入必要的包
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class StartLogin{

    static MainFrame2 mainFrame = new MainFrame2();

    // 定时检测是否掉线的定时器
    static java.util.concurrent.ScheduledExecutorService globalTimer = com.cheng.helper.Timer.getGlobalTimer();

//    static java.util.Timer timer = new java.util.Timer(true);


    public static void main(String[] args) {

        // 忽略掉掉线弹窗
        mainFrame.statusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.statusLabel.setText("掉线啦!");
                mainFrame.setState(Frame.ICONIFIED);
                mainFrame.ignorePop = true;
                mainFrame.setAlwaysOnTop(false);
                mainFrame.statusLabel.setForeground(Color.RED);

            }
        });

        OnlineTestTask onlineTestTask = new OnlineTestTask(mainFrame);

        // delay为long,period为long：从现在起过delay毫秒以后，每隔period
        // 毫秒执行一次。
        globalTimer.scheduleAtFixedRate(onlineTestTask, 0, 5000, TimeUnit.MILLISECONDS);

    }
}

