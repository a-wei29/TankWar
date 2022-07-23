package com.TankWar;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable{
    //使用vector存放子弹
    Vector<Shot> shots = new Vector<>();
    //存放坦克
    Vector<EnemyTank> enemyTanks = new Vector<>();

    public EnemyTank(int x, int y, int dir, int speed, int type) {
        super(x, y, dir, speed, type);
    }

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    //判断坦克之间是否碰撞
    public boolean isTouchEnemy() {
        switch (this.getDir()) {
            case 0:
                for(int i = 0; i < enemyTanks.size(); i ++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this) {
                        //敌人坦克向上下
                        if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) return true;

                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) return true;
                        }
                        //敌人坦克向左右
                        if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                            if(this.getX()>= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) return true;

                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) return true;
                        }
                    }
                }
                break;
            case 1:
                for(int i = 0; i < enemyTanks.size(); i ++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this) {
                        //敌人坦克向上下
                        if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60
                            ) return true;

                            if(this.getX() + 40>= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() + 60>= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 60
                            ) return true;
                        }
                        //敌人坦克向左右
                        if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                            if(this.getX()>= enemyTank.getX()
                                    && this.getX()<= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40
                            ) return true;

                            if(this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 60
                                    && this.getY() + 60 >= enemyTank.getY()
                                    && this.getY() + 60 <= enemyTank.getY() + 40
                            ) return true;
                        }
                    }
                }
                break;
            case 2:
                for(int i = 0; i < enemyTanks.size(); i ++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this) {
                        //敌人坦克向上下
                        if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) return true;

                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() + 40>= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60
                            ) return true;
                        }
                        //敌人坦克向左右
                        if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                            if(this.getX()>= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) return true;

                            if(this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40
                            ) return true;
                        }
                    }
                }
                break;
            case 3:
                for(int i = 0; i < enemyTanks.size(); i ++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if(enemyTank != this) {
                        //敌人坦克向上下
                        if(enemyTank.getDir() == 0 || enemyTank.getDir() == 1) {
                            if(this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60
                            ) return true;

                            if(this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 60
                            ) return true;
                        }
                        //敌人坦克向左右
                        if(enemyTank.getDir() == 2 || enemyTank.getDir() == 3) {
                            if(this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 40
                            ) return true;

                            if(this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 60
                                    && this.getY() + 40 >= enemyTank.getY()
                                    && this.getY() + 40 <= enemyTank.getY() + 40
                            ) return true;
                        }
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //判断没子弹时
            if (isLive && shots.size() <= 2) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Shot s = null;
                switch (getDir()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        s.setSteels(this.getSteels());
                        break;
                    case 1:
                        s = new Shot(getX() + 20, getY() + 60, 1);
                        s.setSteels(this.getSteels());
                        break;
                    case 2:
                        s = new Shot(getX(), getY() + 20, 2);
                        s.setSteels(this.getSteels());
                        break;
                    case 3:
                        s = new Shot(getX() + 60, getY() + 20, 3);
                        s.setSteels(this.getSteels());
                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }

            //根据坦克方向继续移动
            switch (getDir()) {
                case 0:
                    for (int i = 0; i < 25; i ++) {
                        if(isTouchEnemy()) break;
                        moveUp();
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 25; i ++) {
                        if(isTouchEnemy()) break;
                        moveDown();
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 25; i ++) {
                        if(isTouchEnemy()) break;
                        moveLeft();
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 25; i ++) {
                        if(isTouchEnemy()) break;
                        moveRight();
                        //休眠
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
            }
            //随机改变坦克方向
            setDir((int)(Math.random() * 4));
            if(!isLive) break;
        }
    }
}
