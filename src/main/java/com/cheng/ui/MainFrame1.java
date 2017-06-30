package com.cheng.ui;

import com.cheng.helper.ProperityHelper;
import com.cheng.helper.Utils;
import com.cheng.login.LoginManager;
import com.cheng.login.LoginStatus;
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by cheng on 2017/6/8.
 * <p>
 * 用户登录主界面
 */
public class MainFrame1 extends MainFrame {
    public MainFrame1() {
        this.setResizable(false);
        jTextField = new JTextField(10);
        jPasswordField = new JPasswordField(10);
        jLabel1 = new JLabel("用户:");
        jLabel2 = new JLabel("密码:");
        statusLabel = new JLabel();
        lstatusSignal = new JLabel();
        rstatusSignal = new JLabel();

        jb1 = new JButton("有线登录");
        jb2 = new JButton("无线登录");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        status = new JPanel();


        //设置布局
        this.setLayout(new GridLayout(4, 1));

        int windowWidth = this.getWidth(); //获得窗口宽
        int windowHeight = this.getHeight(); //获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); //定义工具包
        Dimension screenSize = kit.getScreenSize(); //获取屏幕的尺寸
        int screenWidth = screenSize.width; //获取屏幕的宽
        int screenHeight = screenSize.height; //获取屏幕的高
//        xLocation = screenWidth / 2 - windowWidth / 2 - 100;
        xLocation = screenWidth - 300;
        yLocation = 0;

//        yLocation = screenHeight / 2 - windowHeight / 2 - 100;
        this.setLocation(xLocation, yLocation);//设置窗口居中显示


        jp1.add(jLabel1);
        jTextField.setText(ProperityHelper.readValue("username"));
        jp1.add(jTextField);//第一块面板添加用户名和文本框

        jp2.add(jLabel2);
        jPasswordField.setText(Utils.deCodeBASE64(ProperityHelper.readValue("password")));
        jp2.add(jPasswordField);//第二块面板添加密码和密码输入框

        jp3.add(jb1);
        jp3.add(jb2); //第三块面板添加确认和取消

        status.add(rstatusSignal);
        status.add(statusLabel);
        status.add(lstatusSignal);

        //        jp3.setLayout(new FlowLayout());  　　//因为JPanel默认布局方式为FlowLayout，所以可以注销这段代码.
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //将三块面板添加到登陆框上面
        this.add(status);
        //设置显示
        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("登陆");
        this.pack();


// 有线登录事件监听
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(false);
                ignorePop = false;

                jb2.setText("无线登录");
                jb2.setForeground(Color.black);

                // 拿到用户输入的用户名和密码，并写入到配置文件
                String username = jTextField.getText();
                String password = jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

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

//               拿到用户输入的用户名和密码，并写入到配置文件
                String username = jTextField.getText();
                String password = jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int status = LoginManager.loginWireLess();

//                System.out.println(status);

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
    }
}