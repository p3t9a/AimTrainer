package AimTrainer;

import javax.swing.*;

public class CustomButton extends JButton {
    public CustomButton() {
    }

    public CustomButton(Icon icon) {
        super(icon);
    }

    public CustomButton(String text) {
        super(text);
    }

    public CustomButton(Action a) {
        super(a);
    }

    public CustomButton(String text, Icon icon) {
        super(text, icon);
    }
}
