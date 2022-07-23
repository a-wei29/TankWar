package com.TankWar;

import javax.sound.sampled.*;
import java.io.File;
import java.util.Objects;

public class AePlayWave extends Thread{
    public static boolean control = true;
    private String filename;
    private Clip clip = null;

    public AePlayWave(String filename) {
        this.filename = filename;
    }

    public void myStart() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void myStop() {
        clip.stop();
    }

    public void run() {
        File file = new File(filename);

        try {
                clip = AudioSystem.getClip();
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
                clip.open(audioInputStream);
                if (Objects.equals(filename, "src\\music\\bgm.wav")) clip.loop(Clip.LOOP_CONTINUOUSLY);
                else clip.start();

                if (!control) clip.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
