package com.TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TankWar extends JFrame {

    FirstView firstView = null;
    AePlayWave aePlayWave = null;
    JPanel oldPanel = null;
    JPanel newPanel = null;
    public JLabel drawBack = null;

    public static void main(String[] args) {
        TankWar tankWar = new TankWar();
    }

    public TankWar() {
        //获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //关闭边框
        setUndecorated(true);
        //窗口大小不可调节
        setResizable(false);
        //设置布局为空
        setLayout(null);
        firstView = new FirstView(this);
        //声音控制器
        musicControl();
        //返回按钮
//        getBack();

        this.add(firstView);

        //搭建JFrame框架
        this.setSize(screenSize.width, screenSize.height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        //设置背景音乐
        aePlayWave = new AePlayWave("src\\music\\bgm.wav");
        aePlayWave.start();
    }
    //声音开关
    public void musicControl () {
        JLabel jLabel = new MyLabel(1350, 800, 50, 50, "/images/open.png", true).getMyLabel();
        JLabel jLabel1 = new MyLabel(1350, 800, 50, 50, "/images/off.png", true).getMyLabel();
        jLabel1.setVisible(false);

        jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AePlayWave.control = false;
                aePlayWave.myStop();
                jLabel.setVisible(false);
                jLabel1.setVisible(true);
                repaint();
                revalidate();
            }
        });

        jLabel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AePlayWave.control = true;
                aePlayWave.myStart();
                jLabel.setVisible(true);
                jLabel1.setVisible(false);
                repaint();
                revalidate();
            }
        });

        this.add(jLabel);
        this.add(jLabel1);
    }
    //返回按钮
    public void getBack() {
        drawBack = new MyLabel(1350, 700, 50, 50, "/images/back.png", true).getMyLabel();
        drawBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                oldPanel.removeAll();
                oldPanel.add(newPanel);
                if (oldPanel instanceof MyPanel) {
//                    MyPanel.flag = false;
                    ((MyPanel) oldPanel).clearPaint(oldPanel.getGraphics());
                }
                drawBack.setVisible(newPanel != null && !(newPanel instanceof FirstView));
            }
        });
        this.add(drawBack);
        drawBack.setVisible(false);
    }

    public void setPanel(JPanel oldPanel, JPanel newPanel) {
        this.oldPanel = oldPanel;
        this.newPanel = newPanel;
    }
}
