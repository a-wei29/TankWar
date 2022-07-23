package com.TankWar;

import java.util.Vector;

public class MyTools {
    public static boolean isHit(Tank tank, int x, int y) {
        switch (tank.getDir()) {
            case 0:
                if(tank.getX() < x + 20 && tank.getX() > x - 40
                        && tank.getY() >= y && tank.getY() <= y + 20)
                    return true;
                break;
            case 1:
                if(tank.getX() < x + 20 && tank.getX() > x - 40
                        && tank.getY() + 60 >= y && tank.getY() + 60 <= y + 20)
                    return true;
                break;
            case 2:
                if(tank.getX() <= x + 20 && tank.getX() >= x
                        && tank.getY() < y + 20 && tank.getY() > y - 40)
                    return true;
                break;
            case 3:
                if(tank.getX() + 60 <= x + 20 && tank.getX() + 60 >= x
                        && tank.getY() < y + 20 && tank.getY() > y - 40)
                    return true;
                break;
        }
        return false;
    }

    //判断坦克是否撞墙
    public static boolean hitSteels(Tank tank, Vector<Steel> steels) {
        for (int i = 0; i < steels.size(); i ++) {
            Steel steel = steels.get(i);
            if (MyTools.isHit(tank, steel.getX(), steel.getY())) return true;
        }
        return false;
    }

    //判断子弹是否撞墙
    public static boolean shotSteels(Shot shot, Vector<Steel> steels) {
        for (int i = 0; i < steels.size(); i ++) {
            Steel steel = steels.get(i);
            if (shot.getX() <= steel.getX() + 20 && shot.getX() >= steel.getX()
                    && shot.getY() <= steel.getY() + 20 && shot.getY() >= steel.getY())
                return true;
        }
        return false;
    }
}
