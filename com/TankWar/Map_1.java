package com.TankWar;

import java.util.Vector;

public class Map_1 implements MapModel{
    private MyTank myTank = null;
    private Vector<Steel> steels = new Vector<>();
    private Vector<EnemyTank> enemyTanks = new Vector<>();
    private boolean flag = true;

    public Map_1() {
        for(int i = 140; i <= 320; i += 20) steels.add(new Steel(i, 580));
        for(int i = 600; i <= 760; i += 20) steels.add(new Steel(320, i));
        for(int i = 600; i <= 760; i += 20) steels.add(new Steel(580, i));
        for(int i = 580; i <= 760; i += 20) steels.add(new Steel(i, 580));
        for(int i = 140; i <= 320; i += 20) steels.add(new Steel(i, 300));
        for(int i = 120; i <= 280; i += 20) steels.add(new Steel(320, i));
        for(int i = 120; i <= 280; i += 20) steels.add(new Steel(580, i));
        for(int i = 580; i <= 760; i += 20) steels.add(new Steel(i, 300));

        myTank = new MyTank(200, 640, 2, 5, Tank.ME);

        enemyTanks.add(new EnemyTank(200, 220, 2, 5, Tank.ENEMY));
        enemyTanks.add(new EnemyTank(620, 220, 3, 5, Tank.ENEMY));
        enemyTanks.add(new EnemyTank(620, 640, 3, 5, Tank.ENEMY));
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
    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    public boolean getFlag() {
        return false;
    }
}
