package com.cheng.ui;

import javax.swing.*;

/**
 * Created by cheng on 2017/6/30.
 */
public abstract class MainFrame extends JFrame{
    public JTextField jTextField;//�����ı������
    public JPasswordField jPasswordField;//������������
    public JLabel jLabel1, jLabel2, statusLabel, lstatusSignal, rstatusSignal;
    public JPanel jp1, jp2, jp3, status, clock;
    public JButton jb1, jb2;//������ť
    public volatile boolean ignorePop = false; //  ���ߺ���Ե���

    public int xLocation;
    public int yLocation;

}
