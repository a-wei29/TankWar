package com.TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;
import java.util.Vector;

/*绘图区*/
public class MyPanel extends JPanel implements KeyListener, Runnable {
    boolean flag;
    //地图
    MapModel map = null;
    //获取屏幕尺寸
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    //定义自己的坦克
    MyTank myTank = null;
    //定义敌方坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //定义道具Vector
    CreatProp creatProp = null;
    Vector<Prop> props = new Vector<>();
    //定义一个Node对象的Vector
    Vector<Node> nodes = new Vector<>();
    //定义Vector用于存放炸弹
    Vector<Bomb> bombs = new Vector<>();
    //获取JFrame容器
    TankWar jFrame = null;
    //定义图片存放爆炸效果
    Image image0 = null;
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;
    Image image4 = null;
    Image image5 = null;
    Image image6 = null;
    Image image7 = null;
    Image image8 = null;
    Image image9 = null;
    Image image10 = null;
    //敌军数量
    private int enemyNum = 3;

    public MyPanel(String key, MapModel mapModel, TankWar jFrame) {
        flag = true;
        this.jFrame = jFrame;
        jFrame.setPanel(this, new LevelView(jFrame));
        this.setLayout(null);
        setSize(screenSize.width, screenSize.height);

        map = mapModel;
        nodes = Recorder.getNodes();
        bombs.add(new Bomb(-60, -60));
        creatProp = new CreatProp();
        new Thread(creatProp).start();

        JLabel drawBack = new MyLabel(1350, 700, 50, 50, "/images/back.png", true).getMyLabel();
        drawBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag = false;
                //保存记录
                Recorder.setEnemyTanks(enemyTanks);
                Recorder.setMyTank(myTank);
                if (myTank.isLive && enemyTanks.size() != 0) {
                    Recorder.flag = 1;
                    if (map instanceof Map_1) Recorder.map = 1;
                    else if (map instanceof Map_2) Recorder.map = 2;
                }
                else Recorder.flag = 0;

                Recorder.keepRecord();

                MyPanel.super.removeAll();
                MyPanel.super.paint(MyPanel.this.getGraphics());
                MyPanel.super.add(new LevelView(jFrame));
                MyPanel.super.revalidate();
            }
        });
        this.add(drawBack);
        JLabel restart = new MyLabel(1350, 600, 50, 50, "/images/restart.png", true).getMyLabel();
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                flag = false;
                MyPanel.super.removeAll();
                MyPanel.super.paint(MyPanel.this.getGraphics());
                MyPanel map = null;
                if (mapModel instanceof Map_1) map = new MyPanel("1", new Map_1(), jFrame);
                else if (mapModel instanceof Map_2) map = new MyPanel("1", new Map_2(), jFrame);
                jFrame.addKeyListener(map);
                new Thread(map).start();
                MyPanel.super.add(map);
                MyPanel.super.revalidate();
            }
        });
        this.add(restart);

        switch (key) {
            case "1":
                //初始化自己坦克
                myTank = map.getMyTank();
                myTank.setSteels(map.getSteels());
                //初始化敌人坦克
                for(int i = 0; i < map.getEnemyTanks().size(); i ++) {
                    EnemyTank enemyTank = map.getEnemyTanks().get(i);
                    enemyTank.setSteels(map.getSteels());
                    new Thread(enemyTank).start();
                    Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 20, enemyTank.getDir());
                    enemyTank.shots.add(shot);

                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);
                }
                break;
            case "2":
                for(int i = 0; i < nodes.size(); i ++) {
                    Node node = nodes.get(i);
                    if (i == nodes.size() - 1){
                        myTank = new MyTank(node.getX(), node.getY(), node.getDir(), 5, 0);
                        myTank.setShotTimes(Recorder.getMyBullet());
                        myTank.setMyLife(Recorder.getMyLife());
                        myTank.isLive = true;
                        myTank.setSteels(map.getSteels());
                    } else {
                        EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY(), node.getDir(), 5, 1);
                        enemyTank.setSteels(map.getSteels());
                        new Thread(enemyTank).start();
                        Shot shot = new Shot(enemyTank.getX() + 20, enemyTank.getY() + 60, enemyTank.getDir());
                        enemyTank.shots.add(shot);

                        new Thread(shot).start();
                        enemyTanks.add(enemyTank);
                    }
                }
                break;
        }
        for(int i = 0; i < enemyTanks.size(); i ++){
            enemyTanks.get(i).setEnemyTanks(enemyTanks);
        }

        //初始化爆炸效果
        image0 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image0.png"));
        image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image1.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image2.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image3.png"));
        image4 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image4.png"));
        image5 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image5.png"));
        image6 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image6.png"));
        image7 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/image7.png"));
        image8 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/steels.gif"));
        image9 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/questionMark.png"));
        image10 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/gameOver2.png"));
    }

    public void showInfo(Graphics g) {
        //画出总成绩
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 30));
        g.drawString("您累计击毁敌人：", screenSize.height + 120, 100);
        drawTank(screenSize.height + 100, 190, g, 0, 1);
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", Font.BOLD, 50));
        g.drawString( "  *   " + Recorder.getAllEnemyTankNum(), screenSize.height + 170, 235);
        g.drawImage(image6, screenSize.height + 95, 325, 45, 45, this);
        g.drawString( "  *   " + myTank.getMyLife(), screenSize.height + 170, 365);
        g.drawImage(image7, screenSize.height + 95, 455, 45, 45, this);
        g.drawString( "  *   " + myTank.getShotNum(), screenSize.height + 170, 495);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (!flag) return;

        jFrame.repaint();
        jFrame.revalidate();
        props = creatProp.getProps();
        Recorder.setMyBullet(myTank.getShotTimes() - myTank.shots.size());
        g.fillRect(0, 0, screenSize.height, screenSize.height);
        //绘制网格
        drawCell(g);
        //绘制边框
        drawFrame(g);
        //绘制信息
        showInfo(g);
        //绘制道具
        drawProp(g);
        //绘制小地图
        drawSmallMap(g);
        //绘制己方坦克
        if(myTank != null && myTank.isLive) drawTank(myTank.getX(), myTank.getY(), g, myTank.getDir(), 0);
        //绘制己方子弹
        for (int i = 0; i < myTank.shots.size(); i ++) {
            Shot shot = myTank.shots.get(i);

            if(shot != null && shot.isLive){
                int x = shot.getX();
                int y = shot.getY();
                switch (shot.getDir()){
                    case 0:
                        g.drawLine(x, y, x, y - 15);
                        break;
                    case 1:
                        g.drawLine(x, y, x, y + 15);
                        break;
                    case 2:
                        g.drawLine(x, y, x - 15, y);
                        break;
                    case 3:
                        g.drawLine(x, y, x + 15, y);
                        break;
                }
            } else {
                myTank.shots.remove(shot);
            }
        }
        //绘制敌方坦克
        for (int i = 0; i < enemyTanks.size(); i ++) {
            EnemyTank tep = enemyTanks.get(i);
            if(tep.isLive){
                drawTank(tep.getX(), tep.getY(), g, tep.getDir(), tep.getType());
            }
            //绘制敌方子弹
            for(int j = 0; j < tep.shots.size(); j ++) {
                Shot it = tep.shots.get(j);
                if(it.isLive) {
                    int x = it.getX();
                    int y = it.getY();
                    switch (it.getDir()){
                        case 0:
                            g.drawLine(x, y, x, y - 15);
                            break;
                        case 1:
                            g.drawLine(x, y, x, y + 15);
                            break;
                        case 2:
                            g.drawLine(x, y, x - 15, y);
                            break;
                        case 3:
                            g.drawLine(x, y, x + 15, y);
                            break;
                    }
                } else {
                    tep.shots.remove(it);
                }
            }
        }
        //如果bombs集合中有对象，就画出
        for(int i = 0; i < bombs.size(); i ++){
            Bomb it = bombs.get(i);
            if (it.life > 10) {
                g.drawImage(image0, it.x, it.y, 80, 80, this);
            } else if (it.life > 8){
                g.drawImage(image1, it.x, it.y, 80, 80, this);
            } else if (it.life > 6) {
                g.drawImage(image2, it.x, it.y, 80, 80, this);
            } else if (it.life > 4) {
                g.drawImage(image3, it.x, it.y, 80, 80, this);
            } else if (it.life > 2) {
                g.drawImage(image4, it.x, it.y, 80, 80, this);
            } else {
                g.drawImage(image5, it.x, it.y, 80, 80, this);
            }
            //减少炸弹生命值
            it.lifeDown();
            //如果炸弹生命值为0将其删除
            if (it.life == 0) {
                bombs.remove(it);
            }
        }
        //绘制墙壁
        drawSteels(g);
        //绘制游戏结束
        if (!myTank.isLive) g.drawImage(image10, 200, 200, 480, 320, this);

    }
    //绘制墙壁
    public void drawSteels(Graphics g) {
        for(int i = 0; i < map.getSteels().size(); i ++) {
            Steel steel = map.getSteels().get(i);
            g.drawImage(image8, steel.getX(), steel.getY(), 20, 20, this);
        }
    }

    //绘制道具
    public void drawProp(Graphics g) {
        for (int i = 0; i < props.size(); i ++) {
            Prop prop = props.get(i);
            if(MyTools.isHit(myTank, prop.getX(), prop.getY())) {
                if(prop.type == 0) myTank.setMyLife(myTank.getMyLife() + 1);
                if(prop.type == 1) myTank.setShotTimes(myTank.getShotTimes() + 1);
                if(prop.type == 2) {
                    //创建Bomb对象，加入bombs中
                    new AePlayWave("src\\music\\bomb.wav").start();
                    bombs.add(new Bomb(myTank.getX(), myTank.getY()));
                    if(Recorder.getMyLife() > 0)
                        myTank.setMyLife(myTank.getMyLife() - 1);
                    else
                        myTank.isLive = false;
                }
                new AePlayWave("src\\music\\get.wav").start();
                prop.isLife = false;
            }
            if (!prop.isLife) props.remove(prop);
        }

        for(int i = 0; i < props.size(); i ++) {
            Prop prop = props.get(i);
            if (prop.isLife) {
                g.drawImage(image9, prop.getX(), prop.getY(), 20, 20, this);
            } else {
                props.remove(prop);
            }
        }
    }

    //绘制坦克
    public void drawTank(int x, int y, Graphics g, int dir, int type){
        switch (type){
            case 0://己方
                g.setColor(Color.cyan);
                break;
            case 1://敌方
                g.setColor(Color.yellow);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }

        if(dir == 0 || dir == 1){
            g.fill3DRect(x, y, 10, 60, false);
            g.fill3DRect(x + 10, y + 10, 20, 40, false);
            g.fill3DRect(x + 30, y, 10, 60, false);
            g.fillOval(x + 10, y + 20, 20, 20);
            if(dir == 0){
                g.drawLine(x + 20, y + 30, x + 20, y);
            }
            if(dir == 1){
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
            }
        }

        if(dir == 2 || dir == 3){
            g.fill3DRect(x, y, 60, 10, false);
            g.fill3DRect(x + 10, y + 10, 40, 20, false);
            g.fill3DRect(x, y + 30, 60, 10, false);
            g.fillOval(x + 20, y + 10, 20, 20);
            if(dir == 2){
                g.drawLine(x + 30, y + 20, x, y + 20);
            }
            if(dir == 3){
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
            }
        }

    }

    //绘制边框
    public void drawFrame(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        int weigh = screenSize.height + 6;
        int height = screenSize.height;

        g.fill3DRect(0, 0, weigh, 20, true);
        g.fill3DRect(0, height - 20, weigh, height, true);
        g.fill3DRect(0, 0, 20, height, true);
        g.fill3DRect(weigh - 20, 0,20, height, true);

        g.setColor(Color.GRAY);
//        g.fillRect(weigh, 0, screenSize.width, screenSize.height);
        g.setColor(Color.DARK_GRAY);
    }
    //绘制小地图
    public void drawSmallMap(Graphics g) {
        int dx = 920;
        int dy = 690;
        g.fill3DRect(dx, dy, 225, 225, false);
        //自己
        g.setColor(Color.cyan);
        g.fillOval(myTank.getX() / 4 + dx, myTank.getY() / 4 + dy, 5, 5);
        //敌人
        g.setColor(Color.yellow);
        for(int i = 0; i < enemyTanks.size(); i ++) {
            EnemyTank tep = enemyTanks.get(i);
            if(tep.isLive)
                g.fillOval(tep.getX() / 4 + dx, tep.getY() / 4 + dy, 5, 5);
        }
        //道具
        g.setColor(Color.red);
        for(int i = 0; i < props.size(); i ++) {
            Prop prop = props.get(i);
            if (prop.isLife)
                g.fillRect(prop.getX() / 4 + dx, prop.getY() / 4 + dy, 5, 5);
        }
        //墙壁
        g.setColor(Color.lightGray);
        for(int i = 0; i < map.getSteels().size(); i ++) {
            Steel steel = map.getSteels().get(i);
            g.fillRect(steel.getX() / 4 + dx, steel.getY() / 4 + dy, 5, 5);
        }
    }
    //绘制网格
    public void drawCell(Graphics g){
        g.setColor(Color.gray);
        for(int i = 0; i < screenSize.height; i += 20){
            g.drawLine(0, i, screenSize.height, i);
        }
        for(int i = 0; i < screenSize.height; i += 20){
            g.drawLine(i, 0, i, screenSize.height);
        }
    }
    //判断坦克之间的碰撞
    public void tankCrash() {
        //遍历所有敌方坦克
        for(int i = 0; i < enemyTanks.size(); i ++) {
            boolean flag = false;
            EnemyTank enemyTank = enemyTanks.get(i);
            switch (myTank.getDir()) {
                case 0:
                    //敌人坦克向上下
                    if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                        if(myTank.getX() >= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 40
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 60
                        ) flag = true;

                        if(myTank.getX() + 40 >= enemyTank.getX()
                                && myTank.getX() + 40 <= enemyTank.getX() + 40
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 60
                        ) flag = true;
                    }
                    //敌人坦克向左右
                    if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                        if(myTank.getX()>= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 60
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 40
                        ) flag = true;

                        if(myTank.getX() + 40 >= enemyTank.getX()
                                && myTank.getX() + 40 <= enemyTank.getX() + 60
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 40
                        ) flag = true;
                    }
                    break;
                case 1:
                    //敌人坦克向上下
                    if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                        if(myTank.getX() >= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 40
                                && myTank.getY() + 60 >= enemyTank.getY()
                                && myTank.getY() + 60 <= enemyTank.getY() + 60
                        ) flag = true;

                        if(myTank.getX() + 40>= enemyTank.getX()
                                && myTank.getX() + 40 <= enemyTank.getX() + 40
                                && myTank.getY() + 60>= enemyTank.getY()
                                && myTank.getY() + 60 <= enemyTank.getY() + 60
                        ) flag = true;
                    }
                    //敌人坦克向左右
                    if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                        if(myTank.getX()>= enemyTank.getX()
                                && myTank.getX()<= enemyTank.getX() + 60
                                && myTank.getY() + 60 >= enemyTank.getY()
                                && myTank.getY() + 60 <= enemyTank.getY() + 40
                        ) flag = true;

                        if(myTank.getX() + 40 >= enemyTank.getX()
                                && myTank.getX() + 40 <= enemyTank.getX() + 60
                                && myTank.getY() + 60 >= enemyTank.getY()
                                && myTank.getY() + 60 <= enemyTank.getY() + 40
                        ) flag = true;
                    }
                    break;
                case 2:
                    //敌人坦克向上下
                    if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                        if(myTank.getX() >= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 40
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 60
                        ) flag = true;

                        if(myTank.getX() >= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 40
                                && myTank.getY() + 40>= enemyTank.getY()
                                && myTank.getY() + 40 <= enemyTank.getY() + 60
                        ) flag = true;
                    }
                    //敌人坦克向左右
                    if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                        if(myTank.getX()>= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 60
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 40
                        ) flag = true;

                        if(myTank.getX() >= enemyTank.getX()
                                && myTank.getX() <= enemyTank.getX() + 60
                                && myTank.getY() + 40 >= enemyTank.getY()
                                && myTank.getY() + 40 <= enemyTank.getY() + 40
                        ) flag = true;
                    }
                    break;
                case 3:
                    //敌人坦克向上下
                    if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                        if(myTank.getX() + 60 >= enemyTank.getX()
                                && myTank.getX() + 60 <= enemyTank.getX() + 40
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 60
                        ) flag = true;

                        if(myTank.getX() + 60 >= enemyTank.getX()
                                && myTank.getX() + 60 <= enemyTank.getX() + 40
                                && myTank.getY() + 40 >= enemyTank.getY()
                                && myTank.getY() + 40 <= enemyTank.getY() + 60
                        ) flag = true;
                    }
                    //敌人坦克向左右
                    if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                        if(myTank.getX() + 60 >= enemyTank.getX()
                                && myTank.getX() + 60 <= enemyTank.getX() + 60
                                && myTank.getY() >= enemyTank.getY()
                                && myTank.getY() <= enemyTank.getY() + 40
                        ) flag = true;

                        if(myTank.getX() + 60 >= enemyTank.getX()
                                && myTank.getX() + 60 <= enemyTank.getX() + 60
                                && myTank.getY() + 40 >= enemyTank.getY()
                                && myTank.getY() + 40 <= enemyTank.getY() + 40
                        ) flag = true;
                    }
                    break;
            }
            if (flag) {
                myTank.setMyLife(myTank.getMyLife() - 1);
                if (myTank.getMyLife() == 0) {
                    myTank.isLive = false;
                    new AePlayWave("src\\music\\gameOver2.wav").start();
                }
                enemyTank.isLive = false;
                enemyTanks.remove(enemyTank);
                bombs.add(new Bomb(enemyTank.getX(), enemyTank.getY()));
                bombs.add(new Bomb(myTank.getX(), myTank.getY()));
                new AePlayWave("src\\music\\bomb.wav").start();
            }
        }
    }


    //判断己方坦克是否被击中
    public void hitMyTank() {
        //遍历所有敌人坦克
        for(int i = 0; i < enemyTanks.size(); i ++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历所有子弹
            for(int j = 0; j < enemyTank.shots.size(); j ++) {
                Shot shot = enemyTank.shots.get(j);
                //判断是否击中
                if(myTank.isLive && shot.isLive) {
                    hitTank(shot, myTank);
                }
            }
        }
    }

    //判断子弹是否击中
    public void hitTank(Shot s, Tank tank) {
        switch (tank.getDir()) {
            case 0:
            case 1:
                if(s.getX() > tank.getX() && s.getX() < tank.getX() + 40
                    && s.getY() > tank.getY() && s.getY() < tank.getY() + 60){
                    s.isLive = false;


                    if(tank instanceof EnemyTank){
                        tank.isLive = false;
                        enemyTanks.remove(tank);
                        Recorder.addNum();
                    }
                    if (tank instanceof MyTank){
                        if(myTank.getMyLife() > 0)
                            myTank.setMyLife(myTank.getMyLife() - 1);
                        if (myTank.getMyLife() == 0) {
                            new AePlayWave("src\\music\\gameOver2.wav").start();
                            myTank.isLive = false;
                        }
                    }
                    new AePlayWave("src\\music\\bomb.wav").start();
                    //创建Bomb对象，加入bombs中
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                }
            case 2:
            case 3:
                if(s.getX() > tank.getX() && s.getX() < tank.getX() + 60
                    && s.getY() > tank.getY() && s.getY() < tank.getY() + 40) {
                    s.isLive = false;

                    new AePlayWave("src\\music\\bomb.wav").start();
                    if(tank instanceof EnemyTank){
                        tank.isLive = false;
                        enemyTanks.remove(tank);
                        Recorder.addNum();
                    }
                    if (tank instanceof MyTank){
                        System.out.print(myTank.isLive);
                        if(myTank.getMyLife() > 0)
                            myTank.setMyLife(myTank.getMyLife() - 1);
                        if (myTank.getMyLife() == 0) myTank.isLive = false;
                        System.out.println(myTank.isLive);
                    }
                    //创建Bomb对象，加入bombs中
                    bombs.add(new Bomb(tank.getX(), tank.getY()));
                }
        }
    }

    public void clearPaint(Graphics g) {
        super.paint(g);
    }

    //字符输出时
    @Override
    public void keyTyped(KeyEvent e) {

    }

    //某键被按下时
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { //退出
            int select = JOptionPane.showConfirmDialog(jFrame,
                    "是否确认退出",
                    "确认",
                    JOptionPane.YES_NO_OPTION);

            if(select == 0) {
                Recorder.setEnemyTanks(enemyTanks);
                Recorder.setMyTank(myTank);
                if (myTank.isLive && enemyTanks.size() != 0) {
                    Recorder.flag = 1;
                    if (map instanceof Map_1) Recorder.map = 1;
                    else if (map instanceof Map_2) Recorder.map = 2;
                }
                else Recorder.flag = 0;

                Recorder.keepRecord();
                System.exit(0);
            }
        }
        if(myTank.isLive) {
            if (e.getKeyCode() == KeyEvent.VK_W) {            //向上移动
                myTank.setDir(0);
                myTank.moveUp();
            } else if (e.getKeyCode() == KeyEvent.VK_S) {      //向下移动
                myTank.setDir(1);
                myTank.moveDown();
            } else if (e.getKeyCode() == KeyEvent.VK_A) {      //向左移动
                myTank.setDir(2);
                myTank.moveLeft();
            } else if (e.getKeyCode() == KeyEvent.VK_D) {      //向右移动
                myTank.setDir(3);
                myTank.moveRight();
            } else if (e.getKeyCode() == KeyEvent.VK_J) {      //发射子弹
                myTank.shotEnemy(myTank.getDir(), map.getSteels());
            }
        }
    }

    //某键弹起时
    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true){
            if (flag) this.repaint();
            else {
                MyPanel.super.paint(MyPanel.this.getGraphics());
                break;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //是否击中敌人坦克
            if (myTank.shot != null && myTank.shot.isLive && flag) {
                for(int i = 0; i < enemyTanks.size(); i ++){
                    EnemyTank it = enemyTanks.get(i);
                    for(Shot shot : myTank.shots)
                        hitTank(shot, it);
                }
            }
            //己方是否被击中
            hitMyTank();
            //坦克是否发生碰撞
            tankCrash();
            if (!myTank.isLive) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                flag = false;
                MyPanel.super.removeAll();
                MyPanel.super.paint(MyPanel.this.getGraphics());
                MyPanel.super.add(new LevelView(jFrame));
                MyPanel.super.revalidate();
            }
        }
    }
}
