package main;

import Entity.Entity;

public class CollisionCheck {
    GamePanel gp;
    public CollisionCheck(GamePanel gp) {
        this.gp = gp;
    }
    public void checkCollision(Entity e) {
        int entityLeftWorldX = e.worldX + e.solidArea.x;
        int entityRightWorldX = e.worldX + e.solidArea.x + e.solidArea.width;
        int entityTopWorldY = e.worldY + e.solidArea.y;
        int entityBottomWorldY = e.worldY + e.solidArea.y + e.solidArea.height;


        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow= entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;
        switch (e.direction){
            case "up":
                entityTopRow = (entityTopWorldY - e.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapNum[entityRightCol][entityTopRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + e.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - e.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + e.speed)/gp.tileSize;
                tileNum1 = gp.tileManager.mapNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tiles[tileNum1].collision || gp.tileManager.tiles[tileNum2].collision){
                    e.collisionOn = true;
                }
                break;
        }

    }
}
