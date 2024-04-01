package AimTrainer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerTimer implements ActionListener {
    private final CircleModel model;
    private final Circle circle;
    int change = 1;

    public ActionListenerTimer(CircleModel model, Circle circle) {
        this.model = model;
        this.circle = circle;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //expand, then shrink the circle
        int diameter = model.getDiameter();
        int newDiameter = diameter + change;

        if (newDiameter > model.getMaxDiameter()) {
            //if the max diameter is met, then start shrinking the size
            change *= -1;//
        } else if (newDiameter < model.getMinDiameter()) {
            ((Timer) e.getSource()).stop();
        } else {
            model.setDiameter(newDiameter);
            circle.revalidate();
            circle.repaint();
        }
    }
}
