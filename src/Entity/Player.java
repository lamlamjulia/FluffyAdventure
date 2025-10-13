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
    public int hasKey;

    public Player(GamePanel gp, KeyHandler keyHandler)
    {
        this.gp = gp;
        this.keyHandler = keyHandler;
        screenY = gp.screenWidth/2 -(gp.tileSize/2);
        screenX = gp.screenHeight/2 -(gp.tileSize/2);
        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;
        SetDefault();
        getPlayerImage();
    }
    public void SetDefault()
    {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
        hasKey = 0;
    }
    public void update()
    {
        if (keyHandler.upPressed || keyHandler.downPressed || keyHandler.leftPressed || keyHandler.rightPressed) {
            if(keyHandler.upPressed){
                direction = "up";
            }
            else if(keyHandler.downPressed){
                direction = "down";
            }
            else if(keyHandler.leftPressed){
                direction = "left";
            }
            else if(keyHandler.rightPressed){
                direction = "right";
            }
            collisionOn = false;
            gp.collisionCheck.checkTile(this);

            int index = gp.collisionCheck.checkObject(this, true);
            pickUpObject(index);

            if(!collisionOn)
            {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;

                    case "right":
                        worldX += speed;
                        break;
                }
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
    public void pickUpObject(int i)
    {
        if(i != 999)
        {
            switch (gp.obj[i].name)
            {
                case "Key":
                    gp.playSE(1);
                    hasKey++;
                    gp.obj[i] = null;
                    break;
                case "Door":
                    if(hasKey>0)
                    {
                        gp.playSE(2);
                        hasKey--;
                        gp.obj[i] = null;
                    }
                    break;
                case "Chest":
                    gp.obj[i] = null;
                    break;
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
