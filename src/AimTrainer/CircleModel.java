package AimTrainer;

public class CircleModel {
    private int x;//absolute position index
    private int y;//absolute position index
    private int diameter;
    private int maxDiameter;
    private int minDiameter = 0;

    public CircleModel(int x, int y, int diameter, int maxDiameter) {
        this.diameter = diameter;
        this.x = x;
        this.y = y;
        this.maxDiameter = maxDiameter;
    }

    public CircleModel minDiameter(int minDiameter){
        this.minDiameter = minDiameter;
        return this;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDiameter() {
        return diameter;
    }

    public void setDiameter(int diameter) {
        this.diameter = diameter;
    }

    public int getMaxDiameter() {
        return maxDiameter;
    }

    public void setMaxDiameter(int maxDiameter) {
        this.maxDiameter = maxDiameter;
    }

    public int getMinDiameter() {
        return minDiameter;
    }

    public void setMinDiameter(int minDiameter) {
        this.minDiameter = minDiameter;
    }

    public int getCenterX(){
        return x + diameter/2;
    }

    public int getCenterY(){
        return y + diameter/2;
    }
}
