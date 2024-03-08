package game2d.main;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame window = new JFrame();
            GamePanel gamePanel = new GamePanel();

            window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.setResizable(false);
            window.setTitle("2DGame");
            window.setSize(new Dimension(Constants.visibleWidth, Constants.visibleHeight));

            window.add(gamePanel);

            window.setLocationRelativeTo(null);
            window.setVisible(true);

            gamePanel.startGameThread();
        });
    }
}