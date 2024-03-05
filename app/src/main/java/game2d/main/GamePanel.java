package game2d.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import game2d.entity.Player;
import game2d.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    private KeyHandler key = new KeyHandler();
    private TileManager tile = new TileManager(this);
    public Player player = new Player(key, tile);
    private Thread gameThread;

    public GamePanel() {
        this.setPreferredSize(new Dimension(Constants.visibleWidth, Constants.visibleHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawingBreak = 1000000000 / Constants.FPS;
        double drawingMoment = System.nanoTime() + drawingBreak;

        while (gameThread != null) {

            update();
            repaint();
            checkCollision();

            try {
                double remainingTime = (drawingMoment - System.nanoTime()) / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);
                drawingMoment += drawingBreak;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {
        player.update();
    }

    public void checkCollision() {
        player.checkBorderCollision();

    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tile.draw(g2);
        player.draw(g2);

        g2.dispose();
    }

}
