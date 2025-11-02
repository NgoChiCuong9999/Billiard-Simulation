package baitap;

import java.awt.*;

public class Hole {
    private int x, y, radius;

    public Hole(int x, int y, int radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillOval(x - radius, y - radius, radius * 2, radius * 2);
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getRadius() { return radius; }
}
