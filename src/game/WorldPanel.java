package game;

import entity.World;

import javax.swing.*;
import java.awt.*;

public class WorldPanel extends JPanel implements Runnable{
    private World world;
    public final int UNIT_SIZE = 8;
    public final int WORLD_WIDTH = 640;
    public final int WORLD_HEIGHT= 640;

    public WorldPanel(World world) {
        this.world = world;
        setPreferredSize(new Dimension(640, 640));
        setLayout(null);
        setBounds(80, 0, WORLD_WIDTH, WORLD_HEIGHT);
    }

    Thread mainThread;
    int FPS = 60;

    public World getWorld() {
        return world;
    }
    public void setWorld(World world){
        this.world = world;
    }

    public void startMainThread(){
        mainThread = new Thread(this);
        mainThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (mainThread != null){
//            System.out.println("Game loop is running");
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= Math.pow(10, 9)){
                System.out.println("FPS = "+drawCount);
                drawCount = 0;
                timer = 0;
            }

        }

    }

    public void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
//        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i <= WORLD_WIDTH/UNIT_SIZE; i++){
            int linePos = i*UNIT_SIZE;
            g.drawLine(linePos, 0, linePos, 640);
            g.drawLine(0, linePos, 640, linePos);
        }
        g.dispose();
    }
}
