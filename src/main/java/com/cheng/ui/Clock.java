package com.cheng.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;


class clockPanel extends Panel
{
    Panel p1, p2, p3;
    JLabel label1;
    int year, month, day, week;

    public clockPanel()
    {
        setBackground(Color.yellow);
        setPreferredSize(new Dimension(250, 320));
        setLayout(new BorderLayout(10, 10));
        /**************************************************************************/
        p1 = new Panel();
        label1 = new JLabel();

        SimpleDateFormat df = new SimpleDateFormat("yyyy年MM月dd日(EE)");//设置日期格式
        label1.setText(df.format(new Date()));
        p1.add(label1);
        /**************************************************************************/
//        p2 = new panel3();
        /**************************************************************************/
        p3 = new panel3();

        /**************************************************************************/
        this.add(p1, BorderLayout.NORTH);
//        this.add(p2, BorderLayout.CENTER);
        this.add(p3, BorderLayout.SOUTH);
    }

}

public class Clock
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("时钟");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 点击x结束程序
        Container contentPane = frame.getContentPane();
        // 得到窗口内容面板
        contentPane.add(new clockPanel());
        frame.pack();
        frame.setVisible(true); // 设置窗口可见

    }

}


class panel3 extends Panel
{
    Timer timer;
    private int hour, min, sec;

    JLabel label2;

    public panel3()
    {
        label2 = new JLabel();
        label2.setFont(new Font("SAN_SERIF", Font.BOLD, 20));
        add(label2);

        // setPreferredSize(new Dimension(150,200));
        timer = new Timer(1000, new myActionListener());
        timer.start();

        GregorianCalendar date = new GregorianCalendar();
        hour = date.getTime().getHours();
        min = date.getTime().getMinutes();
        sec = date.getTime().getSeconds();
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        /****************************************************************************/
        // 画数字钟
        label2.setText(String.format("%1$,02d", hour) + ":"
                + String.format("%1$,02d", min) + ":"
                + String.format("%1$,02d", sec));
    }

    public class myActionListener implements ActionListener
    {
        @SuppressWarnings("deprecation")
        public void actionPerformed(ActionEvent e)
        {
            if (e.getSource() == timer)
            {
                GregorianCalendar date = new GregorianCalendar();
                hour = date.getTime().getHours();
                min = date.getTime().getMinutes();
                sec = date.getTime().getSeconds();
                // System.out.println(sec);
                // degree+=6;
                // System.out.println(degree);
                repaint();
            }
        }
    }
}




