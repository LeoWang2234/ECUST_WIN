package com.cheng.login;
import com.cheng.helper.*;
import com.cheng.ui.MainFrame;
import com.cheng.ui.MainFrame1;
import com.cheng.ui.MainFrame2;

import javax.swing.*;
import java.awt.*;   //�����Ҫ�İ�
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class StartLogin{

    static MainFrame2 mainFrame = new MainFrame2();

    // ��ʱ����Ƿ���ߵĶ�ʱ��
    static java.util.concurrent.ScheduledExecutorService globalTimer = com.cheng.helper.Timer.getGlobalTimer();

//    static java.util.Timer timer = new java.util.Timer(true);


    public static void main(String[] args) {

        // ���Ե����ߵ���
        mainFrame.statusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.statusLabel.setText("������!");
                mainFrame.setState(Frame.ICONIFIED);
                mainFrame.ignorePop = true;
                mainFrame.setAlwaysOnTop(false);
                mainFrame.statusLabel.setForeground(Color.RED);

            }
        });

        MyTimerTask onlineTestTask = new MyTimerTask(mainFrame);

        // delayΪlong,periodΪlong�����������delay�����Ժ�ÿ��period
        // ����ִ��һ�Ρ�
        globalTimer.scheduleAtFixedRate(onlineTestTask, 0, 5000, TimeUnit.MILLISECONDS);

//        boolean isTerminated = false;
//        while (!isTerminated) {
//            try {
//                isTerminated = globalTimer.isTerminated();
//                System.out.println(isTerminated);
//                mainFrame.lstatusSignal.setText("<--");
//                mainFrame.rstatusSignal.setText("-->");
//                Thread.sleep(1000);
//                mainFrame.lstatusSignal.setText("");
//                mainFrame.rstatusSignal.setText("");
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//            }
//        }
//
//         �����ߵ�����˵������ѭ���ˣ�Ҳ���Ƕ�ʱ����������
//        mainFrame.lstatusSignal.setText("-->");
//        mainFrame.rstatusSignal.setText("<--");
//        mainFrame.statusLabel.setText("�Զ�������");
    }
}

