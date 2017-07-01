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
 * ��ʱ����Ƿ����
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
                    mainFrame.statusLabel.setText("��ǰ����");
                    mainFrame.statusLabel.setFont(new Font("",Font.BOLD,15));
                    mainFrame.statusLabel.setForeground(Color.BLUE);
                    mainFrame.jb2.setText("���ߵ�¼");
                    mainFrame.jb2.setForeground(Color.black);
                    mainFrame.jb1.setText("���ߵ�¼");
                    mainFrame.jb1.setForeground(Color.black);
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

            new Thread(new Runnable() {
                public void run() {
                    mainFrame.remove(mainFrame.weatherLabel);
                    mainFrame.add(mainFrame.jp3);
                    mainFrame.jp3.updateUI();
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
