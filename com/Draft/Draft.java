package com.Draft;

import com.TankWar.LevelView;
import com.TankWar.MyPanel;
import com.sun.jndi.toolkit.url.UrlUtil;
import jdk.nashorn.internal.scripts.JD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeMap;

public class Draft {
    public static void main(String[] args) {
        MF mf = new MF();
    }
}

class MF extends JFrame {
    public MF () {
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        int choose = JOptionPane.showOptionDialog(this, "请选择", "",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, new String[]{"新游戏","继续游戏"}, null);
        System.out.println(choose);
    }
}

class MyPanel_1 extends JPanel{
    public MyPanel_1() {
        add(new JLabel("fafsef"));
    }
}

class MyPanel_2 extends JPanel implements Runnable{

    public boolean flag = true;
    public MyPanel_2() {
        JButton jButton = new JButton("返回");
        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                flag = false;
                MyPanel_2.super.removeAll();
                MyPanel_2.super.add(new MyPanel_1());
                MyPanel_2.super.paint(MyPanel_2.this.getGraphics());
                MyPanel_2.super.revalidate();
            }
        });
        this.add(jButton);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
        g.fillRect(0, 100, 700, 700);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(flag);
            if (flag) repaint();
            else break;
        }
    }
}

class MyLabel0 extends JLabel {
    private int x;
    private int y;
    private int width;
    private int height;
    private String url;
    private boolean flag;

    public MyLabel0 (int x, int y, int width, int height, String url, boolean flag) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.url = url;
        this.flag = flag;
    }

    public JLabel getMyLabel() {
        Image image = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource(url));
        ImageIcon imageIcon = new ImageIcon(image);
        JLabel jLabel = new JLabel(imageIcon);
        jLabel.setBounds(x, y, width, height);

        imageIcon.setImage(imageIcon.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));

        if (flag) {
            jLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    jLabel.setBounds(x - 5, y - 5, width + 10, height + 10);
                    imageIcon.setImage(imageIcon.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    jLabel.setBounds(x, y, width, height);
                    imageIcon.setImage(imageIcon.getImage().getScaledInstance(jLabel.getWidth(),jLabel.getHeight(),Image.SCALE_DEFAULT));
                }
            });
        }

        return jLabel;
    }
}
