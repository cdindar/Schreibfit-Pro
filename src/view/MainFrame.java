package view;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private JPanel contentPanel = new JPanel(new CardLayout());
    private JLabel scoreLabel = new JLabel("SCORE: 0");
    public static final Color BEIGE = new Color(245, 245, 220);

    public MainFrame() {
        super("SchreibFit Pro 2026");
        setSize(1000, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(BEIGE);

        JPanel header = new JPanel(new BorderLayout());
        header.setOpaque(false);
        header.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        scoreLabel.setFont(new Font("Monospaced", Font.BOLD, 26));
        scoreLabel.setForeground(Color.DARK_GRAY);
        header.add(scoreLabel, BorderLayout.EAST);

        contentPanel.setOpaque(false);
        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        setContentPane(mainPanel);
    }

    public void setView(JPanel p) {
        contentPanel.removeAll();
        contentPanel.add(p);
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public void updateScore(int s) {
        scoreLabel.setText("SCORE: " + s);
    }
}