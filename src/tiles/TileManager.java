package tiles;

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
        tiles = new Tile[3];
        mapNum = new int[gp.maxCol][gp.maxRow];
        getTileImage();
        loadMap("/maps/firstMap.txt");
    }
    public void getTileImage()
    {
        try {
            tiles[1] = new Tile();
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/032.png"));

            tiles[0] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/002.png"));

            tiles[2] = new Tile();
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/018.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2)
    {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;
        while(col < gp.maxCol && row < gp.maxRow) {
            int tileNum = mapNum[col][row];
            g2.drawImage(tiles[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.maxCol) {
                row++;
                col = 0;
                x = 0;
                y += gp.tileSize;
            }
        }
    }
    public void loadMap(String filePath)
    {
        try
        {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.maxCol && row < gp.maxRow) {
                String line = br.readLine();
                while(col < gp.maxCol && row < gp.maxRow) {
                    String nums[] = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    System.out.println("num: " + num);

                    mapNum[col][row] = num;
                    System.out.println("mapnum: " + mapNum[col][row]);
                    col++;

                }
                if(col == gp.maxCol) {
                    col = 0;
                    row++;
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
