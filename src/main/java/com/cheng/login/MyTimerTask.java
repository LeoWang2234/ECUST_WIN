package com.cheng.login;

import com.cheng.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;

/**
 * Created by login on 2017/6/8.
 *
 * ��ʱ����Ƿ����
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
                    mainFrame.statusLabel.setText("��ǰ����");
                    mainFrame.statusLabel.setForeground(Color.BLUE);
                    mainFrame.jb2.setText("���ߵ�¼");
                    mainFrame.jb2.setForeground(Color.black);
                    mainFrame.jb1.setText("���ߵ�¼");
                    mainFrame.jb1.setForeground(Color.black);
                }
            }).start();

        }else {
            status = OnlineStatus.sendGet("http://www.baidu.com");
            // �����жϵ�������������������һ�Σ���ֹ���У������п��ܻ�������Ӧʱ��
            // ������ߣ��ټ���ѭ��
            if (status) {
                return;
            }
            new Thread(new Runnable() {
                public void run() {
                    if (!mainFrame.ignorePop) {
                        System.out.println(mainFrame.ignorePop);
                        mainFrame.statusLabel.setText("������ ���Һ���");
                        mainFrame.statusLabel.setForeground(Color.RED);
                        mainFrame.setState(JFrame.NORMAL);
                        mainFrame.setLocation(mainFrame.xLocation, mainFrame.yLocation);//���ô��ھ�����ʾ
                        mainFrame.setAlwaysOnTop(true);
                    }else {
                        mainFrame.statusLabel.setText("������");
                        mainFrame.statusLabel.setForeground(Color.RED);
                    }
                    mainFrame.jb2.setText("���ߵ�¼");
                    mainFrame.jb2.setForeground(Color.black);
                    mainFrame.jb1.setText("���ߵ�¼");
                    mainFrame.jb1.setForeground(Color.black);
                }
            }).start();

        }
    }
}
