package com.TankWar;

import java.util.Vector;

public class Prop {
    private int x;
    private int y;
    //0为生命，1为子弹
    int type;
    boolean isLife = true;

    public Prop(int x, int y, int type) {
        this.x = x;
        this.y = y;
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
}
