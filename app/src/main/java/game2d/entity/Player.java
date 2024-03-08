package game2d.entity;

import java.awt.Graphics2D;
import java.util.List;

import javax.imageio.ImageIO;

import game2d.main.Constants;
import game2d.main.GamePanel;
import game2d.main.KeyHandler;
import game2d.tile.TileManager;

public class Player extends Entity {

    private KeyHandler key;
    private TileManager tm;
    private GamePanel gp;
    private int eatenSlimes = 0;


    public Player(KeyHandler key, TileManager tm, GamePanel gp) {

        this.gp = gp;
        this.key = key;
        this.tm = tm;

        setDefaultValues();
        getPlayerImage();

    }

    public void setDefaultValues() {
        entityXPos = Constants.tileSize * 8;
        entityYPos = Constants.tileSize * 56;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/Front1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/Front2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/Back1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/Back2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/Right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/Right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/Left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/Left2.png"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void checkBorderCollision() {
        if (entityXPos < 0) {
            entityXPos = 0;
        }
        if (entityXPos > Constants.worldWidth - Constants.tileSize) {
            entityXPos = Constants.worldWidth - Constants.tileSize;
        }
        if (entityYPos < 0) {
            entityYPos = 0;
        }
        if (entityYPos > Constants.worldHeight - Constants.tileSize) {
            entityYPos = Constants.worldHeight - Constants.tileSize;
        }
    }

    private boolean checkTileCollision(int entityX, int entityY) {
        int tileX = entityX / Constants.tileSize;
        int tileY = entityY / Constants.tileSize;
        int otherTileX = (entityX + Constants.tileSize - 1) / Constants.tileSize;
        int otherTileY = (entityY + Constants.tileSize - 1) / Constants.tileSize;
        return tm.isTileCollidable(tm.getTileID(tileX, tileY))
                || tm.isTileCollidable(tm.getTileID(otherTileX, otherTileY))
                || tm.isTileCollidable(tm.getTileID(tileX, otherTileY))
                || tm.isTileCollidable(tm.getTileID(otherTileX, tileY));

    }

    private boolean checkFlameCollsion(int entityX, int entityY) {
        int tileX = entityX / Constants.tileSize;
        int tileY = entityY / Constants.tileSize;

        if (gp.getSlimes().size() > 0) {
            for (FlameSlime flameSlime : gp.getSlimes()) {
                if (flameSlime.entityXPos == tileX && flameSlime.entityYPos == tileY) {
                    return true;
                }
            }
        }
        return false;
    }

    private void removeSlime(){
        for (int i = 0; i < gp.getSlimes().size(); i++) {
            if (gp.getSlimes().get(i).getX() == entityXPos/Constants.tileSize && gp.getSlimes().get(i).getY() == entityYPos/Constants.tileSize) {
                gp.getSlimes().remove(i);
                eatenSlimes++;
                break;
            }
        }
    }
    public int getEatenSlimes() {
        return eatenSlimes;
    }


    public void update() {
        if (key.downPressed || key.leftPressed || key.rightPressed
                || key.upPressed) {
            if (key.upPressed) {
                direction = "up";
                if (!checkTileCollision(entityXPos, entityYPos - speed))
                    entityYPos -= speed;
                if (checkFlameCollsion(entityXPos, entityYPos)) {
                    removeSlime();
                }
            }
            if (key.downPressed) {
                direction = "down";
                if (!checkTileCollision(entityXPos, entityYPos + speed))
                    entityYPos += speed;
                if (checkFlameCollsion(entityXPos, entityYPos)) {
                    removeSlime();
                }
            }
            if (key.leftPressed) {
                direction = "left";
                if (!checkTileCollision(entityXPos - speed, entityYPos))
                    entityXPos -= speed;
                if (checkFlameCollsion(entityXPos, entityYPos)) {
                    removeSlime();
                }
            }
            if (key.rightPressed) {
                direction = "right";
                if (!checkTileCollision(entityXPos + speed, entityYPos))
                    entityXPos += speed;
                if (checkFlameCollsion(entityXPos, entityYPos)) {
                    removeSlime();
                }
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;

            }
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                else
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                else
                    image = down2;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                else
                    image = right2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                else
                    image = left2;
                break;
        }
        g2.drawImage(image, Constants.playerX, Constants.playerY, Constants.tileSize, Constants.tileSize, null);
    }
}
