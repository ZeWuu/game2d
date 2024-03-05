package game2d.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;



import game2d.main.Constants;


public class Entity {
    
    public int entityXPos,entityYPos;
    public int speed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1,right2;
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    protected BufferedImage image = null;


    public void draw(Graphics2D g2) {
        g2.drawImage(image,entityXPos, entityYPos, Constants.tileSize, Constants.tileSize, null);
    }
}
