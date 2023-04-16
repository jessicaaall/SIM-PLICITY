package game;

import entity.World;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class WorldPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener {
    private World world;
    public final int UNIT_SIZE = 40;
    public int WORLD_WIDTH;
    public int WORLD_HEIGHT;
    private int mapX, mapY;
    private int lastMouseX, lastMouseY;
//    private Rumah[][] koordinat;

    TileManager tileManager = new TileManager(this);

    public WorldPanel(World world) {
        mapX = 0;
        mapY = 0;
        this.world = world;
        WORLD_WIDTH = world.getWidth()*UNIT_SIZE;
        WORLD_HEIGHT= world.getHeight()*UNIT_SIZE;
        setPreferredSize(new Dimension(640, 640));
        setLayout(null);
        setBounds(0, 0, 640, 640);
        addMouseListener(this);
        addMouseMotionListener(this);
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
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(mapX, mapY);
        for (int i = 0; i < world.getWidth(); i++){
            for (int j = 0; j < world.getHeight(); j++){
                tileManager.draw(g2d, i*UNIT_SIZE-mapX, j*UNIT_SIZE-mapY);

            }
        }
        for (int i = 0; i <= world.getWidth(); i++){
            int linePos = i*UNIT_SIZE;
            g2d.drawLine(linePos - mapX, 0-mapY, linePos-mapX, world.getWidth()*UNIT_SIZE-mapY);
            g2d.drawLine(0 - mapX, linePos-mapY, world.getWidth()*UNIT_SIZE-mapX, linePos-mapY);
        }
        g2d.fillOval(mapX, mapY, UNIT_SIZE, UNIT_SIZE);
        g2d.dispose();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // start dragging the map
            lastMouseX = e.getX();
            lastMouseY = e.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // stop dragging the map
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            // update the position of the map based on the mouse movement
            int dx = e.getX() - lastMouseX;
            int dy = e.getY() - lastMouseY;
            mapX += dx;
            mapY += dy;
            // limit the map position to prevent scrolling too far out of bounds
            mapX = Math.max(Math.min(mapX, getWidth() - world.getWidth()), 0);
            mapY = Math.max(Math.min(mapY, getHeight() - world.getHeight()), 0);
            lastMouseX = e.getX();
            lastMouseY = e.getY();
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
