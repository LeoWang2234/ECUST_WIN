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
 * �û���¼������
 */
public class MainFrame1 extends MainFrame {
    public MainFrame1() {
        this.setResizable(false);
        jTextField = new JTextField(10);
        jPasswordField = new JPasswordField(10);
        jLabel1 = new JLabel("�û�:");
        jLabel2 = new JLabel("����:");
        statusLabel = new JLabel();
        lstatusSignal = new JLabel();
        rstatusSignal = new JLabel();

        jb1 = new JButton("���ߵ�¼");
        jb2 = new JButton("���ߵ�¼");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        status = new JPanel();


        //���ò���
        this.setLayout(new GridLayout(4, 1));

        int windowWidth = this.getWidth(); //��ô��ڿ�
        int windowHeight = this.getHeight(); //��ô��ڸ�
        Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
//        xLocation = screenWidth / 2 - windowWidth / 2 - 100;
        xLocation = screenWidth - 300;
        yLocation = 0;

//        yLocation = screenHeight / 2 - windowHeight / 2 - 100;
        this.setLocation(xLocation, yLocation);//���ô��ھ�����ʾ


        jp1.add(jLabel1);
        jTextField.setText(ProperityHelper.readValue("username"));
        jp1.add(jTextField);//��һ���������û������ı���

        jp2.add(jLabel2);
        jPasswordField.setText(Utils.deCodeBASE64(ProperityHelper.readValue("password")));
        jp2.add(jPasswordField);//�ڶ�����������������������

        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��

        status.add(rstatusSignal);
        status.add(statusLabel);
        status.add(lstatusSignal);

        //        jp3.setLayout(new FlowLayout());  ����//��ΪJPanelĬ�ϲ��ַ�ʽΪFlowLayout�����Կ���ע����δ���.
        this.add(jp1);
        this.add(jp2);
        this.add(jp3);  //�����������ӵ���½������
        this.add(status);
        //������ʾ
        this.setSize(300, 200);
        //this.pack();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setTitle("��½");
        this.pack();


// ���ߵ�¼�¼�����
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setAlwaysOnTop(false);
                ignorePop = false;

                jb2.setText("���ߵ�¼");
                jb2.setForeground(Color.black);

                // �õ��û�������û��������룬��д�뵽�����ļ�
                String username = jTextField.getText();
                String password = jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

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

//               �õ��û�������û��������룬��д�뵽�����ļ�
                String username = jTextField.getText();
                String password = jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int status = LoginManager.loginWireLess();

//                System.out.println(status);

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
    }
}