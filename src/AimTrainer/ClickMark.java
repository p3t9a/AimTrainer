package AimTrainer;

import javax.swing.*;
import java.awt.*;

public class ClickMark extends JPanel {

    private final CircleModel model;

    public ClickMark(CircleModel model) {
        this.model = model;
        setBounds(model.getX(), model.getY(), model.getDiameter(), model.getDiameter());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.red);
        int diameter = model.getDiameter();
        int x = 0;
        int y = 0;
        g.fillOval(x, y, diameter, diameter);
    }

}

