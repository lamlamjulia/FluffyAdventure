package main;
import Entity.Player;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16;
    final int scale = 3;
    public int tileSize = originalTileSize * scale;

    final int maxCol = 16;
    final int maxRow = 12;

    final int screenWidth = tileSize * maxCol;
    final int screenHeight = tileSize * maxRow;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }
    Thread gameThread;
    int FPS = 60;
    int playerX = 0;
    int playerY = 0;
    int playerSpeed = 3;

    KeyHandler keyHandler = new KeyHandler();
    Player player = new Player(this, keyHandler);

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
        Graphics2D g2d = (Graphics2D) g;
        player.draw(g2d);
        //g2d.setColor(Color.white);
        //g2d.fillRect(playerX, playerY, tileSize, tileSize);
        g2d.dispose();
    }
}
