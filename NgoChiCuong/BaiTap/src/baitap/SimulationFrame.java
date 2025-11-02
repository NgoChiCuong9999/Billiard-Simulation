package baitap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SimulationFrame extends JFrame {

    private SimulationPanel simPanel;

    public SimulationFrame(int numBalls, int mapNum) {
        setTitle("Billiard Simulation - Map " + mapNum);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        simPanel = new SimulationPanel(numBalls, mapNum);
        add(simPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        JButton backButton = new JButton("⬅ Quay lại Menu chính");
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        backButton.addActionListener((ActionEvent e) -> {
            simPanel.stopSimulation();
            dispose();
            new MainMenu();
        });

        setVisible(true);
    }
}
