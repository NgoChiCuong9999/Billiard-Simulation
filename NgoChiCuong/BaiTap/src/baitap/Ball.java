package baitap;

import java.awt.*;

public class Ball {
    private int id;
    private double x, y, dx, dy;
    private int radius;
    private Color color;

    public Ball(int id, double x, double y, double dx, double dy, int radius, Color color) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
    }

    public void move(int width, int height) {
        x += dx;
        y += dy;
        if (x < radius || x > width - radius) dx = -dx;
        if (y < radius || y > height - radius) dy = -dy;
    }

    public void slowDown() {
        dx *= 0.995;
        dy *= 0.995;
    }

    public void checkCollision(Ball other) {
        double dxDist = other.x - this.x;
        double dyDist = other.y - this.y;
        double dist = Math.sqrt(dxDist * dxDist + dyDist * dyDist);
        if (dist < this.radius + other.radius) {
            double tempDx = this.dx;
            double tempDy = this.dy;
            this.dx = other.dx;
            this.dy = other.dy;
            other.dx = tempDx;
            other.dy = tempDy;
        }
    }

    public void draw(Graphics2D g2) {
        g2.setColor(color);
        g2.fillOval((int) (x - radius), (int) (y - radius), radius * 2, radius * 2);
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public int getId() { return id; }
}
