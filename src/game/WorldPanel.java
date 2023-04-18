package game;

import entity.Rumah;
import entity.World;
import tiles.TileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class WorldPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener, ActionListener {
    private World world;
    private MainPanel mp;
    private boolean isDragging = false;
    public final int UNIT_SIZE = 40;
    public int WORLD_WIDTH;
    public int WORLD_HEIGHT;
    private int mapX =0, mapY=0;
    private int cameraWidth;
    private int cameraHeight;
    private int lastMouseX, lastMouseY;
//    private Rumah[][] koordinat;
    public MainMenuPanel mmp;

    public JButton toMainMenuButton = new JButton("X");
    public JButton addHouseButton = new JButton("Add House");

    TileManager tileManager = new TileManager(this);
    Sound sound = new Sound();
    public WorldPanel(World world, MainPanel mp) {
        this.mp = mp;
        cameraWidth = mp.height-50;
        cameraHeight = mp.height-50;
        toMainMenuButton.setFocusable(false);
        toMainMenuButton.setHorizontalTextPosition(JButton.CENTER);
        toMainMenuButton.setVerticalTextPosition(JButton.CENTER);
        toMainMenuButton.setFont(new Font("Comic Sans", Font.BOLD, 15));
        toMainMenuButton.setBounds(5,5,30, 30);
        toMainMenuButton.addActionListener(this);
        toMainMenuButton.setBackground(Color.yellow);
        this.mp.add(toMainMenuButton);

        mapX = 0;
        mapY = 0;
        this.world = world;
        WORLD_WIDTH = world.getWidth()*UNIT_SIZE;
        WORLD_HEIGHT= world.getHeight()*UNIT_SIZE;
        setPreferredSize(new Dimension(cameraWidth, cameraHeight));
        setLayout(null);
        setBounds(0, (mp.height-cameraHeight)/2, cameraWidth, cameraHeight);
        addMouseListener(this);
        addMouseMotionListener(this);
        setDoubleBuffered(true);
        playMusic(0);
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
    public void playMusic(int i){
        sound.setFile(i);
        sound.play();
        sound.loop();
    }
    public void stopMusic(){
        sound.stop();
    }
    public void playSFX(int i){
        sound.setFile(i);
        sound.play();
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
        g2d.translate(-mapX, -mapY);
        for (int i = 0; i < world.getWidth(); i++){
            for (int j = 0; j < world.getHeight(); j++){
                tileManager.draw(g2d, i*UNIT_SIZE, j*UNIT_SIZE);

            }
        }
        for (int i = 0; i <= world.getWidth(); i++){
            for (int j = 0; j <= world.getHeight(); j++){
                int linePos1 = i*UNIT_SIZE;
                int linePos2 = j*UNIT_SIZE;
                g2d.drawLine(linePos1, 0, linePos1, world.getWidth()*UNIT_SIZE);
                g2d.drawLine(0, linePos2, world.getWidth()*UNIT_SIZE, linePos2);
            }

        }
        // draw houses
        for (Rumah rumah : world.getDaftarRumah()) {
            Point location = rumah.getLokasi();
            Color color = rumah.getColor();
            g2d.setColor(color);
            g2d.fillRect(location.x*UNIT_SIZE, location.y*UNIT_SIZE, UNIT_SIZE, UNIT_SIZE);
        }
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
            isDragging = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            // stop dragging the map
            isDragging = false;
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
            if (isDragging) {
                int dx = e.getX() - lastMouseX;
                int dy = e.getY() - lastMouseY;
                mapX = Math.max(0, Math.min(mapX + dx, WORLD_WIDTH - cameraWidth));
                mapY = Math.max(0, Math.min(mapY + dy, WORLD_HEIGHT - cameraHeight));

                lastMouseX = e.getX();
                lastMouseY = e.getY();
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toMainMenuButton){
            System.out.println("Exit Game");
            stopMusic();
            this.mainThread.interrupt();
            this.mainThread =null;
            mp.remove(toMainMenuButton);
            mp.add(mmp, BorderLayout.CENTER);
            mp.remove(this);
            mp.revalidate();
            mp.repaint();
        }
    }
}
