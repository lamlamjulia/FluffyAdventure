package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    public GamePanel gp;
    public KeyHandler keyHandler;
    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        this.gp = gp;
        this.keyHandler = keyHandler;
        screenY = gp.screenWidth/2 -(gp.tileSize/2);
        screenX = gp.screenHeight/2 -(gp.tileSize/2);
        SetDefault();
        getPlayerImage();
    }
    public void SetDefault()
    {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void update()
    {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed){
                direction = "up";
                worldY -= speed;
            }
            else if(keyHandler.downPressed){
                direction = "down";
                worldY += speed;
            }
            else if(keyHandler.leftPressed){
                direction = "left";
                worldX -= speed;
            }
            else if(keyHandler.rightPressed){
                direction = "right";
                worldX += speed;
            }
            spriteCounter++;
            if(spriteCounter > 12)
            {
                if(spriteNum == 1)
                    spriteNum = 2;
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }

    }
    public void draw(Graphics2D g2)
    {
        BufferedImage img = null;
        switch (direction) {
            case "up":
                if(spriteNum == 1)
                    img = up1;
                else if(spriteNum == 2)
                    img = up2;
                break;
            case "down":
                if(spriteNum == 1)
                    img = down1;
                else if(spriteNum == 2)
                    img = down2;
                break;
            case "left":
                if(spriteNum == 1)
                    img = left1;
                else if(spriteNum == 2)
                    img = left2;
                break;
            case "right":
                if(spriteNum == 1)
                    img = right1;
                else if(spriteNum == 2)
                    img = right2;
                break;
        }
        g2.drawImage(img, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
    public void getPlayerImage()
    {
        try
        {
            up1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Player/boy_right_2.png"));

        }catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
