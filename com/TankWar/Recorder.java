package com.TankWar;

import java.awt.*;
import java.io.*;
import java.util.Objects;
import java.util.Vector;

public class Recorder {
    //定义变量，记录我方击毁敌人坦克数
    private static int allEnemyTankNum = 0;
    //定义我方生命值
    private static int myLife = 1;
    //定义我方子弹数
    private static int myBullet = 1;
    //定义剩余坦克数
    private static int restTanks;
    //定义IO对象
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = (Panel.class.getResource("/myRecord.txt")+"").substring(5);
    private static Vector<EnemyTank> enemyTanks = null;
    private static MyTank myTank;
    public static int map;
    public static int flag;

    //定义一个Node的Vector，用于保存敌人的信息
    private static Vector<Node> nodes = new Vector<>();

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static void setMyTank(MyTank myTank) {
        Recorder.myTank = myTank;
    }

    //增加一个方法，用于读取recordFile
    public static void getNodesEnemyTankNum(String key) {
        try {
            br = new BufferedReader(new FileReader(recordFile));
            flag = Integer.parseInt(br.readLine());
            map = Integer.parseInt(br.readLine());
            allEnemyTankNum = Integer.parseInt(br.readLine());
            if(Objects.equals(key, "1")) allEnemyTankNum = 0;
            myLife = Integer.parseInt(br.readLine());
            if(Objects.equals(key, "1")) myLife = 1;
            myBullet = Integer.parseInt(br.readLine());
            if(Objects.equals(key, "1")) myBullet = 3;

            restTanks = Integer.parseInt(br.readLine());
            //循环读取文件，生成nodes集合
            String line = "";
            if (nodes.size() == 0) {
                while ((line = br.readLine()) != null) {
                    String[] xyd = line.split(" ");
                    Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                    nodes.add(node);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static Vector<Node> getNodes() {
        return nodes;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    public static int getMyLife() {
        return myLife;
    }

    public static int getMyBullet() {
        return myBullet;
    }

    public static void setMyBullet(int myBullet) {
        Recorder.myBullet = myBullet;
    }

    //退出游戏时保存记录
    public static void keepRecord() {
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(flag + "\r\n");
            bw.write(map + "\r\n");
            bw.write(allEnemyTankNum + "\r\n");
            bw.write(myTank.getMyLife() + "\r\n");
            bw.write(myTank.getShotTimes() + "\r\n");
            //遍历当前活着的坦克
            bw.write(enemyTanks.size() + "\r\n");
            for(int i = 0; i < enemyTanks.size(); i ++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive) {
                    bw.write(enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDir() + "\r\n");
                }
            }
            bw.write(myTank.getX() + " " + myTank.getY() + " " + myTank.getDir() + "\r\n");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void addNum() {
        Recorder.allEnemyTankNum ++;
    }
}
