package AimTrainer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Target {

    public Target(Circle circle, Timer timer, Statistics statistics, ArrayList<Timer> timerList) {
        Clip clip;
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("sounds/Pop sound effect_edited.wav"));
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            throw new RuntimeException(ex);
        }

        circle.addMouseListener(new MouseAdapter() {//
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                clip.start();//play sound
                circle.setVisible(false);//the circle dissapears
                timer.stop();//the timer stops
                timerList.remove(timer);
                statistics.setHits(statistics.getHits() + 1);
            }
        });
        timer.start();

    }
}
