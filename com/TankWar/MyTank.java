package com.TankWar;

import java.util.Vector;

public class MyTank extends Tank{
    private int shotTimes = 3;
    private int myLife = 1;

    public int getMyLife() {
        return myLife;
    }

    public void setMyLife(int myLife) {
        this.myLife = myLife;
    }

    public int getShotNum() {
        return shotTimes - shots.size();
    }

    public int getShotTimes() {
        return shotTimes;
    }

    public void setShotTimes(int shotTimes) {
        this.shotTimes = shotTimes;
    }

    Shot shot = null;

    Vector<Shot> shots = new Vector<>();

    public MyTank(int x, int y, int dir, int speed, int type) {
        super(x, y, dir, speed, type);
    }

    public void shotEnemy(int dir, Vector<Steel> steels){
        //子弹发射数量
        if (shots.size() == shotTimes) return;

        new AePlayWave("src\\music\\fire.wav").start();
        switch (dir){
            case 0:
                shot = new Shot(getX() + 20, getY(), 0);
                break;
            case 1:
                shot = new Shot(getX() + 20, getY() + 60, 1);
                break;
            case 2:
                shot = new Shot(getX(), getY() + 20, 2);
                break;
            case 3:
                shot = new Shot(getX() + 60, getY() + 20, 3);
                break;
        }
        shot.setSteels(steels);
        shots.add(shot);

        //启动线程
        new Thread(shot).start();
    }
}
