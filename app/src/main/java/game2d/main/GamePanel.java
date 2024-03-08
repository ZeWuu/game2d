package game2d.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import game2d.entity.FlameSlime;
import game2d.entity.Player;
import game2d.tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    private int slimesCount = 200;
    private Random random = new Random();

    private KeyHandler key = new KeyHandler();
    private TileManager tile = new TileManager(this);
    public Player player = new Player(key, tile, this);
    public Thread gameThread;
    private List<FlameSlime> slimes = new ArrayList<FlameSlime>();
    private CountingPanel countingPanel = new CountingPanel(this, player);

    public GamePanel() {
        this.setPreferredSize(new Dimension(Constants.visibleWidth, Constants.visibleHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(key);
        this.setFocusable(true);
        this.addingSlimes();
        this.add(countingPanel);

    }

    private void addingSlimes() {
        for (int i = 0; i < slimesCount; i++) {
            int tileX = random.nextInt(Constants.maxWorldCol);
            int tileY = random.nextInt(Constants.maxWorldRow);
            if (!tile.isTileCollidable(tile.getTileID(tileX, tileY)))
                slimes.add(new FlameSlime(this, tileX, tileY));
            else
                i--;
        }
    }

    public int getSlimesCount() {
        return slimesCount;
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
        for (FlameSlime flameSlime : slimes) {
            flameSlime.update();
        }
    }

    public List<FlameSlime> getSlimes() {
        return slimes;
    }

    public void checkCollision() {
        player.checkBorderCollision();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        tile.draw(g2);
        player.draw(g2);

        if (slimes != null) {
            Iterator<FlameSlime> it = slimes.iterator();
            while (it.hasNext()) {
                FlameSlime flameSlime = (FlameSlime) it.next();
                flameSlime.draw(g2);
            }
        }

    }

}
