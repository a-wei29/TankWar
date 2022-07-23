package com.TankWar;

import java.awt.*;
import java.util.Vector;

public class Shot implements Runnable{
    private int x;
    private int y;
    private int dir;
    private int speed = 10;
    private Vector<Steel> steels = new Vector<>();
    boolean isLive = true;

    public Shot(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    @Override
    public void run() {
        while (true) {

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            switch (dir) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    y += speed;
                    break;
                case 2:
                    x -= speed;
                    break;
                case 3:
                    x += speed;
                    break;
            }

            //子弹触碰到边界或撞墙时销毁
            if(x < 30 || x > Toolkit.getDefaultToolkit().getScreenSize().height - 30
                    || y < 30 || y > Toolkit.getDefaultToolkit().getScreenSize().height - 30)
                isLive = false;

            if (MyTools.shotSteels(this, steels)) isLive = false;


            if (!isLive) break;
        }
    }



    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDir() {
        return dir;
    }

    public void setSteels(Vector<Steel> steels) {
        this.steels = steels;
    }
}
