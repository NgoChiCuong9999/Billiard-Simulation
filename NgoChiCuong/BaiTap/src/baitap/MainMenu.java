package baitap;

import javax.swing.*;
import java.awt.*;

public class MainMenu extends JFrame {

    private JComboBox<String> mapSelector;
    private JSpinner ballSpinner;

    public MainMenu() {
        setTitle("🏆 Billiard Simulation - Main Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JLabel title = new JLabel("🎱 BILLIARD SIMULATION 🎱", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));
        add(title, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 2, 10, 10));

        centerPanel.add(new JLabel("Chọn bản đồ (Map):"));
        mapSelector = new JComboBox<>(new String[]{"Map 1 - Giữa bàn", "Map 2 - 4 góc", "Map 3 - 2 bên"});
        centerPanel.add(mapSelector);

        centerPanel.add(new JLabel("Số lượng bóng:"));
        ballSpinner = new JSpinner(new SpinnerNumberModel(5, 1, 50, 1));
        centerPanel.add(ballSpinner);

        JButton startButton = new JButton("▶ Start Simulation");
        JButton exitButton = new JButton("❌ Thoát");

        centerPanel.add(startButton);
        centerPanel.add(exitButton);
        add(centerPanel, BorderLayout.CENTER);

        // Sự kiện nút Start
        startButton.addActionListener(e -> {
            int mapNum = mapSelector.getSelectedIndex() + 1;
            int numBalls = (int) ballSpinner.getValue();
            new SimulationFrame(numBalls, mapNum);
            dispose();
        });

        // Sự kiện nút Exit
        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainMenu::new);
    }
}
