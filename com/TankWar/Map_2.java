package com.TankWar;

import java.util.Vector;

public class Map_2 implements MapModel{
    private MyTank myTank = null;
    private Vector<Steel> steels = new Vector<>();
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private boolean flag = false;

    public Map_2() {
        for(int i = 205; i <= 700; i += 225) {
            steels.add(new Steel(i, 205));
            steels.add(new Steel(i, 225));
            steels.add(new Steel(i, 430));
            steels.add(new Steel(i, 450));
            steels.add(new Steel(i, 655));
            steels.add(new Steel(i, 675));
        }

        for(int i = 225; i <= 700; i += 225) {
            steels.add(new Steel(i, 205));
            steels.add(new Steel(i, 225));
            steels.add(new Steel(i, 430));
            steels.add(new Steel(i, 450));
            steels.add(new Steel(i, 655));
            steels.add(new Steel(i, 675));
        }

        myTank = new MyTank(430, 700, 0, 5, Tank.ME);

        enemyTanks.add(new EnemyTank(100, 280, 3, 5, Tank.ENEMY));
        enemyTanks.add(new EnemyTank(740, 280, 2, 5, Tank.ENEMY));
        enemyTanks.add(new EnemyTank(320, 100, 1, 5, Tank.ENEMY));
        enemyTanks.add(new EnemyTank(540, 100, 1, 5, Tank.ENEMY));
    }

    @Override
    public Vector<Steel> getSteels() {
        return steels;
    }

    @Override
    public MyTank getMyTank() {
        return myTank;
    }

    @Override
    public Vector<EnemyTank> getEnemyTanks() {
        return enemyTanks;
    }

    @Override
    public boolean getFlag() {
        return false;
    }

    @Override
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
