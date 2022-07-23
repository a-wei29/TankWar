package com.TankWar;

import java.util.Vector;

public class CreatProp implements Runnable{
    Vector<Prop> props = new Vector<Prop>();
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (props.size() < 3) {
                int x = (int) ((Math.random() * 40 + 2) * 20);
                int y = (int) ((Math.random() * 40 + 2) * 20);
                int type = (int) (Math.random() * 3);
                props.add(new Prop(x, y, type));
            }
        }
    }

    public Vector<Prop> getProps() {
        return props;
    }
}
