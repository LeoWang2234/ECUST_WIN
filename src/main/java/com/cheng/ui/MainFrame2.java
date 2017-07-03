package com.cheng.ui;

import com.cheng.audio.Audio;
import com.cheng.audio.PlayMusic;
import com.cheng.common.Common;
import com.cheng.login.LoginManager;
import com.cheng.login.LoginStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by cheng on 2017/6/8.
 * <p>
 * 用户登录主界面
 */
public class MainFrame2 extends MainFrame {


    static JLabel dateLabel = new JLabel("", JLabel.CENTER);
    static JLabel timeLabel = new JLabel("", JLabel.CENTER);
    static String initStr = new String(" Just do it !");
    static JTextField wordText = new JTextField(initStr,6);

    SimpleDateFormat df = new SimpleDateFormat("MM月dd日(EE)");//设置日期格式
    public MainFrame2() {

//        jTextField = new JTextField(10);
//        jTextField.setFont(new Font("宋体",Font.BOLD,40));
//        jTextField.setHorizontalAlignment(JTextField.CENTER);

        wordText.setFont(wordText.getFont().deriveFont(25f));
        word = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        word.add(wordText);

        wordText.setForeground(Color.LIGHT_GRAY);

        wordText.addFocusListener(new FocusListener() {
                                      public void focusGained(FocusEvent e) {
                                          JTextField src = (JTextField) e.getSource();
                                          src.setForeground(Color.blue);
                                          if (src.getText().equals(initStr)) {
                                              src.setText(null);
                                          }
                                      }

                                      public void focusLost(FocusEvent e) {
                                          JTextField src = (JTextField) e.getSource();
                                              src.setText(initStr);
                                              src.setForeground(Color.LIGHT_GRAY);
                                      }
                                  });

//        jPasswordField = new JPasswordField(10);
        statusLabel = new JLabel("", JLabel.CENTER);
        weatherLabel = new JLabel("", JLabel.CENTER);


        dateLabel.setFont(new Font("SAN_SERIF", Font.CENTER_BASELINE, 18));
        timeLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 25));
        weatherLabel.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        weatherLabel.setText("正在启动实时天气");

        jb1 = new JButton("有线登录");
        jb2 = new JButton("无线登录");

        jb2.setPreferredSize(new java.awt.Dimension(65, 33));
        jb1.setPreferredSize(new java.awt.Dimension(65, 33));


        Font font1 = new Font("", Font.BOLD, 12);
        jb1.setFont(font1);
        jb2.setFont(font1);
        jp3 = new JPanel();
        status = new JPanel();

        dateLabel.setPreferredSize(new Dimension(150, 80));
        timeLabel.setPreferredSize(new Dimension(150, 50));
        statusLabel.setPreferredSize(new Dimension(150, 0));

        //设置布局
        this.setLayout(new GridLayout(4, 1, 0, 0));

        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        xLocation = screenWidth - 300;
        yLocation = 0;

        this.setLocation(xLocation, yLocation);//设置窗口居中显示


        jp3.add(jb1);
        jp3.add(jb2); //第三块面板添加确认和取消

        status.add(statusLabel);

        this.add(dateLabel);
        this.add(timeLabel);
        this.add(statusLabel);
        this.add(jp3);  //将三块面板添加到登陆框上面
        //设置显示
        this.setPreferredSize(new Dimension(200, 200));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登陆");
        this.setResizable(false);
        this.pack();



        // 单词查询事件监听
        wordText.addKeyListener(new KeyListener() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String value = wordText.getText();
                     System.out.println("Enter " + value);
                    String filePath = Audio.getAudio(value);
                    PlayMusic.Play(filePath);
                }
            }
            public void keyReleased(KeyEvent e) {
            }
            public void keyTyped(KeyEvent e) {
            }
        });

// 有线登录事件监听
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(false);
                ignorePop = false;

                jb2.setText("无线登录");
                jb2.setForeground(Color.black);

                int status = LoginManager.loginWithWire();

                if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("登录失败");
                            statusLabel.setForeground(Color.red);
                            jb1.setText("点击重试");
                            jb1.setForeground(Color.red);
                        }
                    }).start();

                } else if (status == LoginStatus.SUCCESS.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("登录成功");
                            statusLabel.setForeground(Color.BLUE);
                            jb1.setText("有线登录");
                            jb1.setForeground(Color.black);
                        }
                    }).start();
                }
            }
        });

        //         无线登录事件监听
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(false);
                ignorePop = false;

                jb1.setText("有线登录");
                jb1.setForeground(Color.black);

                int status = LoginManager.loginWireLess();

                if (status == LoginStatus.SUCCESS.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("登录成功");
                            statusLabel.setForeground(Color.BLUE);
                            jb2.setText("无线登录");
                            jb2.setForeground(Color.black);
                        }
                    }).start();
                } else if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("登录失败");
                            statusLabel.setForeground(Color.red);
                            jb2.setText("点击重试");
                            jb2.setForeground(Color.red);
                        }
                    }).start();
                } else {
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("参数异常");
                            statusLabel.setForeground(Color.BLUE);
                        }
                    }).start();
                }
            }

        });


        // 更新时间显示的定时任务
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
                        // 画数字钟
                        timeLabel.setText(String.format("%1$,02d", hour) + ":"
                                + String.format("%1$,02d", min) + ":"
                                + String.format("%1$,02d", sec));

                        System.out.println("time update");
                    }
                }).start();
            }
        }

        // 将天气查询的结果做一个缓存，避免一直查询
        class updateWeatherUI implements Runnable {
            int count = 0;
            int time = 0;
            public void run() {
                new Thread(new Runnable() {
                    public void run() {
                        if (Common.online) {
                            // 每隔 30 * 10 秒后更新一下天气，也就是五分钟
                            time++;
                            if (time == 30) {
                                Common.updateWeathers();
                                time = 0;
                                System.out.println("--------request for weather-------");
                            }
                            if (Common.weathers.size()==0){
                                Common.updateWeathers();
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
        Common.globalTimer.scheduleWithFixedDelay(new updateWeatherUI(), 0, 10, TimeUnit.SECONDS);
    }
}