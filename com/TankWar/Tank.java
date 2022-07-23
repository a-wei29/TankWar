package com.TankWar;

import java.awt.*;
import java.util.Vector;

public class Tank {
    public final static int ME = 0;
    public final static int ENEMY = 1;

    private int x;
    private int y;
    private int dir;
    private int speed = 5;
    private int type;
    private Vector<Steel> steels = new Vector<>();
    boolean isLive = true;

    //获取屏幕尺寸
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public int getType() {
        return type;
    }

    public void moveUp() {
        if (y > 20 && !MyTools.hitSteels(this, steels)) y -= speed;
    }

    public void moveDown() {
        if (y < screenSize.height - 80 && !MyTools.hitSteels(this, steels)) y += speed;
    }

    public void moveLeft() {
        if (x > 20 && !MyTools.hitSteels(this, steels)) x -= speed;
    }

    public void moveRight() {
        if (x < screenSize.height - 80 && !MyTools.hitSteels(this, steels)) x += speed;
    }

    public int getDir() {
        return dir;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Tank(int x, int y, int dir, int speed, int type) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setSteels(Vector<Steel> steels) {
        this.steels = steels;
    }

    public Vector<Steel> getSteels() {
        return steels;
    }
}
