package game2d.entity;

import java.awt.Graphics2D;

import javax.imageio.ImageIO;

import game2d.main.GamePanel;
import game2d.main.KeyHandler;

public class Player extends Entity {
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
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

    public void update() {
        if (keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true
                || keyH.upPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
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
        super.draw(g2);
    }
}
