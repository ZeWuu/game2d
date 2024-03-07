package game2d.entity;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import game2d.main.Constants;
import game2d.main.GamePanel;

public class FlameSlime extends Entity {

    private GamePanel gp;
    private int x, y;

    public FlameSlime(GamePanel gp, int x, int y) {
        this.x = x;
        this.y = y;
        this.gp = gp;
        setDefaultValues();
        getSlimeImage();
    }

    private void setDefaultValues() {
        entityXPos = x;
        entityYPos = y; 
        direction = "down";
    }

    public void getSlimeImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/entities/FlameSlime1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/entities/FlameSlime2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteCounter = 0;
            spriteNum++;
            if (spriteNum > 2) {
                spriteNum = 1;
            }
        }

    }

    @Override
    public void draw(Graphics2D g2) {

        if (direction.equals("down")) {
            if (spriteNum == 1) {
                image = down1;
            } else {
                image = down2;
            }
        }
        int worldX = entityXPos * Constants.tileSize;
        int worldY = entityYPos * Constants.tileSize;
        int screenX = worldX - gp.player.entityXPos + Constants.playerX;
        int screenY = worldY - gp.player.entityYPos + Constants.playerY;
        g2.drawImage(image, screenX, screenY, Constants.tileSize, Constants.tileSize, null);
    }

}
