package com.TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;

public class LevelView extends JPanel {
    int id = 0;
    Vector<JLabel> map = new Vector<>();
    TankWar jFrame = null;
    public LevelView (TankWar jFrame) {
        this.jFrame = jFrame;
        jFrame.setPanel(this, new FirstView(jFrame));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        this.setLayout(null);
        JLabel jLabel = new JLabel();
        JLabel jLabel1 = new MyLabel(50, 50, 360, 114, "/images/ncu.png", false).getMyLabel();
        JLabel jLabel2 = new MyLabel(620, 650, 245, 55, "/images/level.png", false).getMyLabel();
        JLabel jLabel3 = new MyLabel(300, 350, 120, 80, "/images/left.png", true).getMyLabel();
        JLabel jLabel4 = new MyLabel(1070, 350, 120, 80, "/images/right.png", true).getMyLabel();
        JLabel jLabel5 = new MyLabel(520, 140, 450, 450, "/images/map1.jpg", true).getMyLabel();
        JLabel jLabel6 = new MyLabel(520, 140, 450, 450, "/images/map2.jpg", true).getMyLabel();


        map.add(jLabel5);
        map.add(jLabel6);

        jLabel.setBackground(Color.LIGHT_GRAY);
        jLabel.setOpaque(true);
        jLabel.setBounds(0, 0, screenSize.width, screenSize.height);

        jLabel3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //切换地图
                map.get(id).setVisible(false);
                id -= 1;
                if (id == -1) id = map.size() - 1;
                map.get(id).setVisible(true);
                LevelView.super.revalidate();
            }
        });

        jLabel4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //切换地图
                map.get(id).setVisible(false);
                id += 1;
                if (id == map.size()) id = 0;
                map.get(id).setVisible(true);
                LevelView.super.revalidate();
            }
        });

        jLabel5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //清除该JPanel上的组件
                LevelView.super.removeAll();
                LevelView.super.repaint();
                LevelView.super.revalidate();

                Recorder.getNodesEnemyTankNum("2");
                MyPanel myPanel = null;
                if (Recorder.map == 1 && Recorder.flag == 1) {
                    int choose = JOptionPane.showOptionDialog(jFrame, "该地图已有存档请选择", "",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            new String[]{"新游戏", "继续游戏"}, null);

                    if (choose == 0) myPanel = new MyPanel("1", new Map_1(), jFrame);
                    else if (choose == 1) myPanel = new MyPanel("2", new Map_1(), jFrame);
                }
                else myPanel = new MyPanel("1", new Map_1(), jFrame);
                //将地图插入
                LevelView.super.add(myPanel);
                //获取键盘操作
                jFrame.addKeyListener(myPanel);
                jFrame.repaint();
                jFrame.revalidate();
                //启动线程
                new Thread(myPanel).start();
                LevelView.super.repaint();
                LevelView.super.revalidate();
            }
        });

        jLabel6.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LevelView.super.removeAll();
                LevelView.super.repaint();
                LevelView.super.revalidate();
                Recorder.getNodesEnemyTankNum("1");
                MyPanel myPanel = null;
                if (Recorder.map == 2 && Recorder.flag == 1) {
                    int choose = JOptionPane.showOptionDialog(jFrame, "该地图已有存档请选择", "",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                            new String[]{"新游戏", "继续游戏"}, null);

                    if (choose == 0) myPanel = new MyPanel("1", new Map_2(), jFrame);
                    else if (choose == 1) myPanel = new MyPanel("2", new Map_2(), jFrame);
                }
                else myPanel = new MyPanel("1", new Map_2(), jFrame);
                LevelView.super.add(myPanel);
                jFrame.addKeyListener(myPanel);
                new Thread(myPanel).start();
                LevelView.super.repaint();
                LevelView.super.revalidate();
            }
        });

        JLabel drawBack = new MyLabel(1350, 700, 50, 50, "/images/back.png", true).getMyLabel();
        drawBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LevelView.super.removeAll();
                LevelView.super.add(new FirstView(jFrame));
//                drawBack.setVisible(false);
            }
        });
        this.add(drawBack);

        //添加组件
        this.add(jLabel1);
        this.add(jLabel2);
        this.add(jLabel3);
        this.add(jLabel4);
        this.add(jLabel5);
        this.add(jLabel6);
        this.add(jLabel);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        jFrame.repaint();
        jFrame.revalidate();
    }
}
