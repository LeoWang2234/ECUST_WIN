package com.cheng.ui;

import com.cheng.common.Common;
import com.cheng.helper.ProperityHelper;
import com.cheng.helper.Timer;
import com.cheng.helper.Utils;
import com.cheng.login.LoginManager;
import com.cheng.login.LoginStatus;
import com.cheng.login.Weather;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by cheng on 2017/6/8.
 * <p>
 * �û���¼������
 */
public class MainFrame2 extends MainFrame {


    static JLabel dateLabel = new JLabel("", JLabel.CENTER);
    static JLabel timeLabel = new JLabel("", JLabel.CENTER);


    SimpleDateFormat df = new SimpleDateFormat("MM��dd��(EE)");//�������ڸ�ʽ
    public MainFrame2() {

        jTextField = new JTextField(10);
        jPasswordField = new JPasswordField(10);
        statusLabel = new JLabel("", JLabel.CENTER);
        weatherLabel = new JLabel("", JLabel.CENTER);

        dateLabel.setFont(new Font("SAN_SERIF", Font.CENTER_BASELINE, 18));
        timeLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        weatherLabel.setFont(new Font("", Font.BOLD, 15));

        jb1 = new JButton("���ߵ�¼");
        jb2 = new JButton("���ߵ�¼");

        Font font1 = new Font("", Font.BOLD, 12);
        jb1.setFont(font1);
        jb2.setFont(font1);
        jp3 = new JPanel();
        status = new JPanel();

        dateLabel.setPreferredSize(new Dimension(150, 80));
        timeLabel.setPreferredSize(new Dimension(150, 50));
        statusLabel.setPreferredSize(new Dimension(150, 0));

        //���ò���
        this.setLayout(new GridLayout(4, 1, 0, 0));

        Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        xLocation = screenWidth - 300;
        yLocation = 0;

        this.setLocation(xLocation, yLocation);//���ô��ھ�����ʾ


        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��

        status.add(statusLabel);

        this.add(dateLabel);
        this.add(timeLabel);
        this.add(statusLabel);
        this.add(jp3);  //�����������ӵ���½������
        //������ʾ
        this.setPreferredSize(new Dimension(200, 200));
//        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��½");
        this.setResizable(false);
        this.pack();


// ���ߵ�¼�¼�����
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(false);
                ignorePop = false;

                jb2.setText("���ߵ�¼");
                jb2.setForeground(Color.black);

                int status = LoginManager.loginWithWire();

                if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("��¼ʧ��");
                            statusLabel.setForeground(Color.red);
                            jb1.setText("�������");
                            jb1.setForeground(Color.red);
                        }
                    }).start();

                } else if (status == LoginStatus.SUCCESS.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("��¼�ɹ�");
                            statusLabel.setForeground(Color.BLUE);
                            jb1.setText("���ߵ�¼");
                            jb1.setForeground(Color.black);
                        }
                    }).start();
                }
            }
        });

        //         ���ߵ�¼�¼�����
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(false);
                ignorePop = false;

                jb1.setText("���ߵ�¼");
                jb1.setForeground(Color.black);

                int status = LoginManager.loginWireLess();

                if (status == LoginStatus.SUCCESS.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("��¼�ɹ�");
                            statusLabel.setForeground(Color.BLUE);
                            jb2.setText("���ߵ�¼");
                            jb2.setForeground(Color.black);
                        }
                    }).start();
                } else if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("��¼ʧ��");
                            statusLabel.setForeground(Color.red);
                            jb2.setText("�������");
                            jb2.setForeground(Color.red);
                        }
                    }).start();
                } else {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("�����쳣");
                            statusLabel.setForeground(Color.BLUE);
                        }
                    }).start();
                }
            }

        });


        // ����ʱ����ʾ�Ķ�ʱ����
        class updateTime implements Runnable {
            private int hour, min, sec;

            public void run() {

                new Thread(new Runnable() {
                    public void run() {
                        //                GregorianCalendar date = new GregorianCalendar();
                        Date date = new Date();
                        dateLabel.setText(df.format(date));
                        hour = date.getHours();
                        min = date.getMinutes();
                        sec = date.getSeconds();
                        // ��������
                        timeLabel.setText(String.format("%1$,02d", hour) + ":"
                                + String.format("%1$,02d", min) + ":"
                                + String.format("%1$,02d", sec));

                        System.out.println("time update");
                    }
                }).start();
            }
        }


        // ���������Ķ�ʱ����
        class updateWeather implements Runnable {
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        // ֻ������ʱ�����������ݣ�
                        if (Common.online) {
                            Common.weathers = Weather.getweather();
                        }
                        if (Common.weathers.size() < 2){
                            weatherLabel.setText("��������ʵʱ����");
                        }
                    }
                }).start();

            }
        }

        // ��������ѯ�Ľ����һ�����棬����һֱ��ѯ
        class updateWeatherUI implements Runnable {
            int count = 0;
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        if (Common.online) {
                            if (Common.weathers.size()==0){
                                Common.weathers = Weather.getweather();
                            }else if (Common.weathers.size()!=0){
                                System.out.println(Common.weathers.toString());
                                weatherLabel.setText(Common.weathers.get(count % 2));
                                count = count % 2 + 1;
                                weatherLabel.updateUI();
                                System.out.println("weather UI update");
                            }
                        }
                    }
                }).start();
            }
        }

        Common.globalTimer.scheduleWithFixedDelay(new updateTime(), 0, 1000, TimeUnit.MILLISECONDS);
        Common.globalTimer.scheduleWithFixedDelay(new updateWeather(), 0, 300, TimeUnit.SECONDS);
        Common.globalTimer.scheduleWithFixedDelay(new updateWeatherUI(), 0, 10, TimeUnit.SECONDS);
    }
}