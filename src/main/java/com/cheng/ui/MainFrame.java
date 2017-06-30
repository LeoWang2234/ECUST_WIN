package com.cheng.ui;

import javax.swing.*;

/**
 * Created by cheng on 2017/6/30.
 */
public abstract class MainFrame extends JFrame{
    public JTextField jTextField;//定义文本框组件
    public JPasswordField jPasswordField;//定义密码框组件
    public JLabel jLabel1, jLabel2, statusLabel, lstatusSignal, rstatusSignal;
    public JPanel jp1, jp2, jp3, status, clock;
    public JButton jb1, jb2;//创建按钮
    public volatile boolean ignorePop = false; //  掉线后忽略弹窗

    public int xLocation;
    public int yLocation;

}
