package com.cheng.login;
import com.cheng.helper.ProperityHelper;
import com.cheng.helper.Utils;
import com.cheng.ui.MainFrame;

import javax.swing.*;
import java.awt.*;   //�����Ҫ�İ�
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.concurrent.TimeUnit;

public class StartLogin{

    static MainFrame mainFrame = new MainFrame();

    // ��ʱ����Ƿ���ߵĶ�ʱ��
    static java.util.concurrent.ScheduledExecutorService globalTimer = java.util.concurrent.Executors.newSingleThreadScheduledExecutor();

//    static java.util.Timer timer = new java.util.Timer(true);


    public static void main(String[] args) {

        // ������ߵ�¼����
        loginWithWireListener();

        // ������ߵ�¼����
        loginWireLessListener();

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

        boolean isTerminated = false;
        while (!isTerminated) {
            try {
                isTerminated = globalTimer.isTerminated();
                System.out.println(isTerminated);
                mainFrame.lstatusSignal.setText("<--");
                mainFrame.rstatusSignal.setText("-->");
                Thread.sleep(1000);
                mainFrame.lstatusSignal.setText("");
                mainFrame.rstatusSignal.setText("");
                Thread.sleep(1000);

            } catch (InterruptedException e) {
            }
        }

        // �����ߵ�����˵������ѭ���ˣ�Ҳ���Ƕ�ʱ����������
        mainFrame.lstatusSignal.setText("-->");
        mainFrame.rstatusSignal.setText("<--");
        mainFrame.statusLabel.setText("�Զ�������");
    }

    private static void loginWithWireListener() {
        mainFrame.jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setAlwaysOnTop(false);
                mainFrame.ignorePop = false;

                mainFrame.jb2.setText("���ߵ�¼");
                mainFrame.jb2.setForeground(Color.black);

                // �õ��û�������û��������룬��д�뵽�����ļ�
                String username = mainFrame.jTextField.getText();
                String password = mainFrame.jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int  status = LoginManager.loginWithWire();

                if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("��¼ʧ��");
                            mainFrame.statusLabel.setForeground(Color.red);
                            mainFrame.jb1.setText("�������");
                            mainFrame.jb1.setForeground(Color.red);
                        }
                    }).start();

                }else if (status==LoginStatus.SUCCESS.getIndex()){
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("��¼�ɹ�");
                            mainFrame.statusLabel.setForeground(Color.BLUE);
                            mainFrame.jb1.setText("���ߵ�¼");
                            mainFrame.jb1.setForeground(Color.black);
                        }
                    }).start();
                }
            }
        });
    }
    private static void loginWireLessListener() {
        //         ���ߵ�¼
        mainFrame.jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setAlwaysOnTop(false);
                mainFrame.ignorePop = false;

                mainFrame.jb1.setText("���ߵ�¼");
                mainFrame.jb1.setForeground(Color.black);

//               �õ��û�������û��������룬��д�뵽�����ļ�
                String username = mainFrame.jTextField.getText();
                String password = mainFrame.jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int status = LoginManager.loginWireLess();

//                System.out.println(status);

                if (status == LoginStatus.SUCCESS.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("��¼�ɹ�");
                            mainFrame.statusLabel.setForeground(Color.BLUE);
                            mainFrame.jb2.setText("���ߵ�¼");
                            mainFrame.jb2.setForeground(Color.black);
                        }
                    }).start();
                }else if(status == LoginStatus.FAILED.getIndex()){
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("��¼ʧ��");
                            mainFrame.statusLabel.setForeground(Color.red);
                            mainFrame.jb2.setText("�������");
                            mainFrame.jb2.setForeground(Color.red);
                        }
                    }).start();
                }
                else{
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("�����쳣");
                            mainFrame.statusLabel.setForeground(Color.BLUE);
                        }
                    }).start();
                }
            }
        });
    }
}

