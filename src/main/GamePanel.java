package main;
import Entity.Player;
import tiles.TileManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import Object.SuperObject;

public class GamePanel extends JPanel implements Runnable{
    public final int originalTileSize = 16;
    public int scale = 3;
    public int tileSize = originalTileSize * scale;
    public int maxCol = 16;
    public int maxRow = 16;

    public int maxWorldCol = 50;
    public int maxWorldRow = 50;
    public int worldWidth = tileSize * maxWorldCol;
    public int worldHeight = tileSize * maxWorldRow;

    public int screenWidth = tileSize * maxCol;
    public int screenHeight = tileSize * maxRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    public void setObject()
    {
        assetSetter.setObject();
    }
    Thread gameThread;
    int FPS = 60;
    TileManager tileManager = new TileManager(this);
    public CollisionCheck collisionCheck = new CollisionCheck(this);
    public AssetSetter assetSetter = new AssetSetter(this);
    public SuperObject obj[] = new SuperObject[10];
    KeyHandler keyHandler = new KeyHandler();
    public Player player = new Player(this, keyHandler);

    public void startGameThread()
    {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;

        long lastTime = System.nanoTime();
        long currentTime;
        double delta = 0;
        int drawCount = 0;
        long timer = 0;

        while(gameThread != null)
        {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer = currentTime - lastTime;
            lastTime = currentTime;
            if(delta >= 1)
            {
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if(timer >= 1000000000)
            {
                System.out.println("FPS: " + drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update()
    {
       player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        for(int i = 0; i < obj.length; i++)
        {
            if(obj[i] != null)
                obj[i].draw(g2,this);
        }
        player.draw(g2);
        g2.dispose();

    }
}
