package com.cheng.ui;

import com.cheng.helper.ProperityHelper;
import com.cheng.helper.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Created by cheng on 2017/6/8.
 * <p>
 * �û���¼������
 */
public class MainFrame extends JFrame {
    public JTextField jTextField;//�����ı������
    public JPasswordField jPasswordField;//������������
    public JLabel jLabel1, jLabel2, statusLabel,statusSignal;
    public JPanel jp1, jp2, jp3, status;
    public JButton jb1, jb2;//������ť
    public volatile boolean ignorePop = false; //  ���ߺ���Ե���

    public int xLocation;
    public int yLocation;

    public MainFrame() {
        this.setResizable(false);
        jTextField = new JTextField(10);
        jPasswordField = new JPasswordField(10);
        jLabel1 = new JLabel("�û�:");
        jLabel2 = new JLabel("����:");
        statusLabel = new JLabel();
        statusSignal = new JLabel();

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
        status.add(statusSignal);

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

    }
}
