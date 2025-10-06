package Entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    int x, y, speed;
    BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    String direction;
    int spriteCounter = 0;
    int spriteNum = 1;
}
