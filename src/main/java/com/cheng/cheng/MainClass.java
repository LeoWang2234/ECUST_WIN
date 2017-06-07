package com.cheng.cheng;
import javax.swing.*;

import java.awt.*;   //�����Ҫ�İ�
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainClass extends JFrame{
    JTextField jTextField ;//�����ı������
    JPasswordField jPasswordField;//������������
    JLabel jLabel1,jLabel2,statusLabel;
    JPanel jp1,jp2,jp3,status;
    JButton jb1,jb2;//������ť

    volatile boolean ignorePop = false; //  ���ߺ���Ե���

    int xLocation;
    int yLocation;
    public MainClass() {
        this.setResizable(false);
        jTextField = new JTextField(10);
        jPasswordField = new JPasswordField(10);
        jLabel1 = new JLabel("�û�:");
        jLabel2 = new JLabel("����:");
        statusLabel = new JLabel();

        jb1 = new JButton("���ߵ�¼");
        jb2 = new JButton("���ߵ�¼");
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        status = new JPanel();

        // ��Ӧ���ȿ�һ���̣߳����Ϸ��ʰٶ�������Ϊ�Ƿ���ߵı�־
        Online online = new Online(true);
        online.start();

        //���ò���
        this.setLayout(new GridLayout(4, 1));

        int windowWidth = this.getWidth(); //��ô��ڿ�
        int windowHeight = this.getHeight(); //��ô��ڸ�
        Toolkit kit = Toolkit.getDefaultToolkit(); //���幤�߰�
        Dimension screenSize = kit.getScreenSize(); //��ȡ��Ļ�ĳߴ�
        int screenWidth = screenSize.width; //��ȡ��Ļ�Ŀ�
        int screenHeight = screenSize.height; //��ȡ��Ļ�ĸ�
        xLocation = screenWidth / 2 - windowWidth / 2 - 100;
        yLocation = screenHeight / 2 - windowHeight / 2 - 100;
        this.setLocation(xLocation, yLocation);//���ô��ھ�����ʾ


        jp1.add(jLabel1);
        jTextField.setText(ProperityHelper.readValue("username"));
        jp1.add(jTextField);//��һ���������û������ı���

        jp2.add(jLabel2);
        jPasswordField.setText(Utils.deCodeBASE64(ProperityHelper.readValue("password")));
        jp2.add(jPasswordField);//�ڶ�����������������������

        jp3.add(jb1);
        jp3.add(jb2); //������������ȷ�Ϻ�ȡ��

        status.add(statusLabel);

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

        statusLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                statusLabel.setText("������!");
                setState(Frame.ICONIFIED);
                ignorePop = true;
                MainClass.this.setAlwaysOnTop(false);
                statusLabel.setForeground(Color.RED);
            }
        });

        // ���ߵ�¼
        jb1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainClass.this.setAlwaysOnTop(false);
                ignorePop = false;

                jb2.setText("���ߵ�¼");
                jb2.setForeground(Color.black);

                // �õ��û�������û��������룬��д�뵽�����ļ�
                String username = jTextField.getText();
                String password = jPasswordField.getText();

                ProperityHelper.writeProperties("username", username);
                ProperityHelper.writeProperties("password", Utils.base64(password));

                int  status = LoginManager.loginWithWire();

//                System.out.println(status);

                if (status == LoginStatus.FAILED.getIndex()) {
                    new Thread(new Runnable() {
                        public void run() {
                            new Online(false).start();
                            statusLabel.setText("��¼ʧ��");
                            statusLabel.setForeground(Color.red);
                            jb1.setText("�������");
                            jb1.setForeground(Color.red);
                        }
                    }).start();

                }else if (status==LoginStatus.SUCCESS.getIndex()){
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


//         ���ߵ�¼
        jb2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MainClass.this.setAlwaysOnTop(false);
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
                }else {
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

    class Online extends Thread {

        private boolean loop = true;
        public Online(Boolean loop) {
            this.loop = loop;
        }

        public void run() {
            while (loop) {
                Boolean status = OnlineTest.sendGet("http://www.baidu.com");
                if (status) {
                    ignorePop = false;
                    MainClass.this.setAlwaysOnTop(false);
                    new Thread(new Runnable() {
                        public void run() {
                            statusLabel.setText("��ǰ����");
                            statusLabel.setForeground(Color.BLUE);
                            jb2.setText("���ߵ�¼");
                            jb2.setForeground(Color.black);
                            jb1.setText("���ߵ�¼");
                            jb1.setForeground(Color.black);
                        }
                    }).start();

                }else {
                    status = OnlineTest.sendGet("http://www.baidu.com");
                    // �����жϵ�������������������һ�Σ���ֹ���У������п��ܻ�������Ӧʱ��
                    // ������ߣ��ټ���ѭ��
                    if (status) {
                        continue;
                    }
                    new Thread(new Runnable() {
                        public void run() {
                            if (!ignorePop) {
                                System.out.println(ignorePop);
                                statusLabel.setText("������ ���Һ���");
                                statusLabel.setForeground(Color.RED);
                                setState(JFrame.NORMAL);
                                MainClass.this.setLocation(xLocation, yLocation);//���ô��ھ�����ʾ
                                MainClass.this.setAlwaysOnTop(true);
                            }else {
                                statusLabel.setText("������");
                                statusLabel.setForeground(Color.RED);
                            }
                            jb2.setText("���ߵ�¼");
                            jb2.setForeground(Color.black);
                            jb1.setText("���ߵ�¼");
                            jb1.setForeground(Color.black);
                        }
                    }).start();

                }
                if (loop) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                    }
                }

            }
        }
    }




    public static void main(String[] args){
        new MainClass();
    }
}