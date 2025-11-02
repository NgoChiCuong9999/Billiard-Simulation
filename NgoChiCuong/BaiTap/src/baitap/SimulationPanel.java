package baitap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationPanel extends JPanel implements ActionListener {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int FPS = 60;
    private static final int HOLE_RADIUS = 30;

    private final Timer timer;
    private final List<Ball> balls;
    private final List<Hole> holes;

    private boolean firstBallFell = false;

    public SimulationPanel(int numBalls, int mapNumber) {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(new Color(10, 80, 10));

        holes = new ArrayList<>();
        setupMap(mapNumber);

        balls = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i < numBalls; i++) {
            int radius = 15;
            int x = rand.nextInt(WIDTH - 2 * radius) + radius;
            int y = rand.nextInt(HEIGHT - 2 * radius) + radius;
            double dx = (rand.nextDouble() - 0.5) * 6;
            double dy = (rand.nextDouble() - 0.5) * 6;
            Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
            balls.add(new Ball(i + 1, x, y, dx, dy, radius, color));
        }

        timer = new Timer(1000 / FPS, this);
        timer.start();
    }

    private void setupMap(int map) {
        holes.clear();
        switch (map) {
            case 1 -> holes.add(new Hole(WIDTH / 2, HEIGHT / 2, HOLE_RADIUS)); // 1 lỗ giữa
            case 2 -> {
                holes.add(new Hole(50, 50, HOLE_RADIUS));
                holes.add(new Hole(WIDTH - 50, 50, HOLE_RADIUS));
                holes.add(new Hole(50, HEIGHT - 50, HOLE_RADIUS));
                holes.add(new Hole(WIDTH - 50, HEIGHT - 50, HOLE_RADIUS));
            }
            case 3 -> {
                holes.add(new Hole(50, HEIGHT / 2, HOLE_RADIUS));
                holes.add(new Hole(WIDTH - 50, HEIGHT / 2, HOLE_RADIUS));
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        updateBalls();
        repaint();
    }

    private void updateBalls() {
        for (Ball ball : balls) ball.move(WIDTH, HEIGHT);
        for (int i = 0; i < balls.size(); i++) {
            for (int j = i + 1; j < balls.size(); j++) {
                balls.get(i).checkCollision(balls.get(j));
            }
        }
        for (Ball ball : balls) ball.slowDown();

        List<Ball> toRemove = new ArrayList<>();
        for (Ball ball : balls) {
            for (Hole hole : holes) {
                double dist = Math.hypot(ball.getX() - hole.getX(), ball.getY() - hole.getY());
                if (dist < hole.getRadius()) {
                    toRemove.add(ball);
                    if (!firstBallFell) {
                        firstBallFell = true;
                        JOptionPane.showMessageDialog(this,
                                "🎯 Ball " + ball.getId() + " fell into the hole first!",
                                "Kết quả", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }
            }
        }
        balls.removeAll(toRemove);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Hole hole : holes) hole.draw(g2);
        for (Ball ball : balls) ball.draw(g2);

        g2.setColor(Color.WHITE);
        g2.drawRect(0, 0, WIDTH - 1, HEIGHT - 1);
    }

    public void stopSimulation() {
        timer.stop();
    }
}
