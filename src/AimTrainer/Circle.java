package AimTrainer;

import javax.swing.*;
import java.awt.*;

public class Circle extends JPanel {

    private final CircleModel model;

    public Circle(CircleModel model) {
        this.model = model;
        setBounds(model.getX(), model.getY(), model.getMaxDiameter(), model.getMaxDiameter());
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(123, 28, 173));
        int diameter = model.getDiameter();
        //calculating top right corner position so that the oval is in the center of the panel
        int x = getWidth() / 2 - diameter / 2;
        int y = getHeight() / 2 - diameter / 2;
        g.fillOval(x, y, diameter, diameter);
    }

    @Override
    public boolean contains(int x, int y) {
        // Check if the click is within the circular area
        int radius = Math.min(getWidth(), getHeight()) / 2;
        return Math.pow(x - (double) getWidth() / 2, 2) + Math.pow(y - (double) getHeight() / 2, 2) <= Math.pow(radius, 2);
    }
}
