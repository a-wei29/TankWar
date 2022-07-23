package com.TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FirstView extends JPanel {
    public FirstView(TankWar jFrame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.setLayout(null);
        JLabel jLabel = new MyLabel(580, 430, 245, 55, "/images/start.png", true).getMyLabel();
        JLabel jLabel1 = new MyLabel(580, 250, 240, 76, "/images/ncu.png", false).getMyLabel();
        JLabel jLabel2 = new MyLabel(580, 730, 245, 55, "/images/quit.png", true).getMyLabel();
        JLabel jLabel3 = new MyLabel(0, 0, screenSize.width, screenSize.height, "/images/background.jpg", false).getMyLabel();
        JLabel jLabel4 = new MyLabel(580, 530, 245, 55, "/images/about.png", true).getMyLabel();
        JLabel jLabel5 = new MyLabel(580, 630, 245, 55, "/images/help.png", true).getMyLabel();
        //开始游戏
        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                        FirstView.super.removeAll();
                        FirstView.super.repaint();
                        FirstView.super.revalidate();
                        FirstView.super.add(new LevelView(jFrame));
                        FirstView.super.repaint();
                        FirstView.super.revalidate();
            }
        });
        //退出
        jLabel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                    int select = JOptionPane.showConfirmDialog(FirstView.this,
                            "是否确认退出",
                            "确认",
                            JOptionPane.YES_NO_OPTION);

                    if(select == 0) {
                        System.exit(0);
                    }
            }
        });
        //关于
        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AboutView(jFrame, "A B O U T");
            }
        });
        //帮助
        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new HelpView(jFrame, "H E L P");
            }
        });

        this.add(jLabel);
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel4);
        this.add(jLabel5);
        this.add(jLabel3);
    }
}
