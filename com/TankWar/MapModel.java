package com.TankWar;

import java.util.Vector;

public interface MapModel  {
    //定义一个Vector存放墙壁信息
    Vector<Steel> getSteels();
    //定义一个MyTank存放自身坦克信息
    MyTank getMyTank();
    //定义一个Vector存放敌人坦克信息
    Vector<EnemyTank> getEnemyTanks();
    //定义一个boolean值存放是否通过的信息
    boolean getFlag();
    void setFlag(boolean flag);
}
