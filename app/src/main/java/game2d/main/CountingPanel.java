package game2d.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import game2d.entity.Player;

public class CountingPanel extends JPanel{
    private JLabel label;
    private GamePanel gp;

    public CountingPanel(GamePanel gamePanel, Player player) {
        this.gp = gamePanel;
        this.setLayout(new BorderLayout());
        this.setOpaque(false); 
        this.setPreferredSize(new Dimension(140,50));

        label = new JLabel();
        add(label);

        Timer timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                label.setText("Slimes found: " + player.getEatenSlimes() + " / " +gp.getSlimesCount());
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(new Color(255, 255, 255, 100)); 
        g.fillRect(0, 0, getWidth(), getHeight());

        super.paintComponent(g);
    }
}
