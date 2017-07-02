package com.cheng.login;

import com.cheng.common.Common;
import com.cheng.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.TimerTask;

/**
 * Created by login on 2017/6/8.
 *
 * ��ʱ����Ƿ����
 */
class OnlineTestTask implements Runnable {

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
        System.out.println("online status update");
        if (status) {
            Common.online = status;
            mainFrame.ignorePop = false;
            mainFrame.setAlwaysOnTop(false);

            new Thread(new Runnable() {
                public void run() {
                    mainFrame.remove(mainFrame.jp3);
                    mainFrame.add(mainFrame.weatherLabel);
                    mainFrame.remove(mainFrame.statusLabel);
                    if (!Common.isWordOnFrame) {
                        mainFrame.add(mainFrame.word, 2);
                        mainFrame.word.updateUI();
                        Common.isWordOnFrame = true;
                    }
                    mainFrame.weatherLabel.updateUI();
                }
            }).start();

        }else {
            // һ�������������һ�Σ���ֹ����
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            status = OnlineStatus.sendGet();
            // �����жϵ�������������������һ�Σ���ֹ���У������п��ܻ�������Ӧʱ��
            // ������ߣ��ټ���ѭ��
            if (status) {
                return;

            }
            // ����ȫ������״̬
            Common.online = status;
            new Thread(new Runnable() {
                public void run() {
                    mainFrame.remove(mainFrame.weatherLabel);
                    mainFrame.add(mainFrame.jp3);
                    if (Common.isWordOnFrame) {
                        mainFrame.remove(mainFrame.word);
                        Common.isWordOnFrame = false;
                    }
                    mainFrame.add(mainFrame.statusLabel, 2);
                    mainFrame.jp3.updateUI();
                    mainFrame.statusLabel.updateUI();
                    if (!mainFrame.ignorePop) {
                        mainFrame.statusLabel.setText("������ ���Һ���");
                        mainFrame.statusLabel.setForeground(Color.RED);
                        mainFrame.setState(JFrame.NORMAL);

                        // ��ǰ ��¼ҳ��û�л�ý��㣬�򽫵���λ����������Ļ�м�
                        if (mainFrame.getFocusOwner() == null) {
                            mainFrame.setLocation(mainFrame.xLocation, mainFrame.yLocation);//���ô��ھ�����ʾ
                        }
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
