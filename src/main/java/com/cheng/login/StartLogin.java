package com.cheng.login;
import com.cheng.helper.ProperityHelper;
import com.cheng.helper.Utils;
import com.cheng.ui.MainFrame;

import java.awt.*;   //导入必要的包
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StartLogin{

    static MainFrame mainFrame = new MainFrame();

    // 定时检测是否掉线的定时器
    static java.util.Timer timer = new java.util.Timer(true);


    public static void main(String[] args) {

        // 添加有线登录监听
        loginWithWireListener();

        // 添加无线登录监听
        loginWireLessListener();

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

        MyTimerTask onlineTestTask = new MyTimerTask(mainFrame);

        // delay为long,period为long：从现在起过delay毫秒以后，每隔period
        // 毫秒执行一次。
        timer.schedule(onlineTestTask, 0, 3000);





    }

    private static void loginWithWireListener() {
        mainFrame.jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setAlwaysOnTop(false);
                mainFrame.ignorePop = false;

                mainFrame.jb2.setText("无线登录");
                mainFrame.jb2.setForeground(Color.black);

                // 拿到用户输入的用户名和密码，并写入到配置文件
                String username = mainFrame.jTextField.getText();
                String password = mainFrame.jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int  status = LoginManager.loginWithWire();

                if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("登录失败");
                            mainFrame.statusLabel.setForeground(Color.red);
                            mainFrame.jb1.setText("点击重试");
                            mainFrame.jb1.setForeground(Color.red);
                        }
                    }).start();

                }else if (status==LoginStatus.SUCCESS.getIndex()){
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("登录成功");
                            mainFrame.statusLabel.setForeground(Color.BLUE);
                            mainFrame.jb1.setText("有线登录");
                            mainFrame.jb1.setForeground(Color.black);
                        }
                    }).start();
                }
            }
        });
    }
    private static void loginWireLessListener() {
        //         无线登录
        mainFrame.jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mainFrame.setAlwaysOnTop(false);
                mainFrame.ignorePop = false;

                mainFrame.jb1.setText("有线登录");
                mainFrame.jb1.setForeground(Color.black);

//               拿到用户输入的用户名和密码，并写入到配置文件
                String username = mainFrame.jTextField.getText();
                String password = mainFrame.jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int status = LoginManager.loginWireLess();

//                System.out.println(status);

                if (status == LoginStatus.SUCCESS.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("登录成功");
                            mainFrame.statusLabel.setForeground(Color.BLUE);
                            mainFrame.jb2.setText("无线登录");
                            mainFrame.jb2.setForeground(Color.black);
                        }
                    }).start();
                }else {
                    new Thread(new Runnable() {
                        public void run() {
                            mainFrame.statusLabel.setText("参数异常");
                            mainFrame.statusLabel.setForeground(Color.BLUE);
                        }
                    }).start();
                }
            }
        });
    }
}

