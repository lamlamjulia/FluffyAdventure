package tiles;

import Entity.Player;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    public GamePanel gp;
    public Tile[] tiles;
    public int mapNum[][];
    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[40];
        mapNum = new int[gp.maxWorldRow][gp.maxWorldRow];
        getTileImage();
        loadMap("/maps/world01.txt");
    }
    public void getTileImage()
    {
        try {
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/034.png"));

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/002.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/018.png"));

            tiles[3] = new Tile();
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/017.png"));

            tiles[4] = new Tile();
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/016.png"));

            tiles[5] = new Tile();
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        int col = 0;
        int row = 0;


        while(col < gp.maxWorldCol && row < gp.maxWorldRow) {

            int tileNum = mapNum[col][row];
            int worldX = col* gp.tileSize;
            int worldY = row* gp.tileSize;

            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            g2.drawImage(tiles[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            col++;
            if(col == gp.maxWorldCol) {
                row++;
                col = 0;
            }
        }
    }
    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int worldX = 0;
            int worldY = 0;
            while(worldY < gp.maxWorldCol && worldX < gp.maxWorldRow) {
                String line = br.readLine();
                while(worldY < gp.maxWorldCol && worldX < gp.maxWorldRow) {
                    String nums[] = line.split(" ");
                    int num = Integer.parseInt(nums[worldY]);

                    mapNum[worldY][worldX] = num;
                    worldY++;

                }
                if(worldY == gp.maxWorldCol) {
                    worldY = 0;
                    worldX++;
                }
            }
            br.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
