package AimTrainer;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ScoreBoard extends JPanel {

    public ScoreBoard(Statistics statistics) {

        setBackground(new Color(60, 63, 65));
        setLayout(new FlowLayout());

        JLabel labelHits = new JLabel("Hits: " + statistics.getHits());
        JLabel labelMisses = new JLabel("Misses: " + statistics.getMisses());

        add(labelHits);
        add(labelMisses);

        labelMisses.setForeground(Color.RED);
        labelHits.setForeground(Color.GREEN);

        statistics.addListenerHits(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                labelHits.setText("Hits: " + evt.getNewValue());
            }
        });
        statistics.addListenerMisses(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                labelMisses.setText("Misses: " + evt.getNewValue());
            }
        });
    }
}
