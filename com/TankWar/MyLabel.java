package com.TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class MyLabel extends JLabel {
    private int x;
    private int y;
    private int width;
    private int height;
    private String url;
    private boolean flag;

    public MyLabel (int x, int y, int width, int height, String url, boolean flag) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.url = url;
        this.flag = flag;
    }

    public JLabel getMyLabel() {
        //获取图片
        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(url));
        ImageIcon imageIcon = new ImageIcon(image);
        //设置JLabel组件
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(x, y, width, height);

        imageIcon.setImage(imageIcon.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));

        if (flag) {
            jLabel.addMouseListener(new MouseAdapter() {
                //当鼠标移入标签时使标签扩大并设置音效
                @Override
                public void mouseEntered(MouseEvent e) {
                    //添加音效
                    new AePlayWave("src\\music\\button1.wav").start();
                    jLabel.setBounds(x - 5, y - 5, width + 10, height + 10);
                    imageIcon.setImage(imageIcon.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabel.setBounds(x, y, width, height);
                    imageIcon.setImage(imageIcon.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));
                }

                @Override
                public void mousePressed(MouseEvent e) {
                    //添加音效
                    new AePlayWave("src\\music\\button2.wav").start();
                }
            });
        }
        return jLabel;
    }
}
