package com.cheng.cheng;

/**
 * Created by cheng on 2017/6/6.
 */
    import java.awt.BorderLayout;

        import javax.swing.JButton;
        import javax.swing.JFrame;
        import javax.swing.JPanel;
        import javax.swing.JScrollPane;
        import javax.swing.JTextArea;

public final class LoginOption extends JFrame {

    public static void main(String[] args) {
        LoginOption tp = new LoginOption();
        tp.setVisible(true);
    }

    public LoginOption() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        for (int i = 0; i < 2; i++) {
            panel.add(new JButton("Button 00" + i));
        }

//        JTextArea textArea = new JTextArea(5, 15);
//        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane();
        getContentPane().add(panel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        pack();
    }

}