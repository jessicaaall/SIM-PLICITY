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

public class WorldPanel extends JPanel implements Runnable, MouseListener, MouseMotionListener, ActionListener, MouseWheelListener {
    private World world;


    public MainPanel mp;
    public WorldOptionPanel wop;
    public MainMenuPanel mmp;
    private boolean isDragging = false;
    public int UNIT_SIZE = 40;
    private int mapX =0, mapY=0;
    private int cameraWidth;
    private int cameraHeight;
    private int lastMouseX, lastMouseY;
//    private Rumah[][] koordinat;
    private int mouseHoverX = -1;
    private int mouseHoverY = -1;


    TileManager tileManager = new TileManager(this);
    Sound sound = new Sound();
    public WorldPanel(World world, MainPanel mp, MainMenuPanel mmp) {
        this.mp = mp;
        this.mmp = mmp;
        cameraWidth = mp.height-50;
        cameraHeight = mp.height-50;

        mapX = 0;
        mapY = 0;
        this.world = world;
        setPreferredSize(new Dimension(cameraWidth, cameraHeight));
        setLayout(null);
        setBounds(0, (mp.height-cameraHeight)/2, cameraWidth, cameraHeight);
        addMouseListener(this);
        addMouseMotionListener(this);
        addMouseWheelListener(this);
        setDoubleBuffered(true);
        playMusic(0);
        wop = new WorldOptionPanel( this.mp, this);
        mp.add(wop);
    }

    Thread mainThread;
    int FPS = 60;
    int currentFPS;



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
//                System.out.println("FPS = "+drawCount);
                currentFPS = drawCount;
                drawCount = 0;
                timer = 0;
            }

            //change the time
            wop.timeLabel.setText("<html>"+ getWorld().getWaktu().tampilkanWaktu()[0] +"<br>" +
                    getWorld().getWaktu().tampilkanWaktu()[1] + "<br>" +
                    getWorld().getWaktu().tampilkanWaktu()[2] + "</html>");

        }
    }

    public void update(){

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(-mapX, -mapY);
        for (int i = 0; i <= world.getWidth(); i++){
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
            g2d.fillRect((location.x*UNIT_SIZE+(UNIT_SIZE/4)), (location.y*UNIT_SIZE+(UNIT_SIZE/2)), UNIT_SIZE/2, UNIT_SIZE/2-UNIT_SIZE/8);
            Polygon roof = new Polygon();
            roof.addPoint(location.x*UNIT_SIZE, location.y*UNIT_SIZE+UNIT_SIZE/2);
            roof.addPoint(location.x*UNIT_SIZE+UNIT_SIZE/2, location.y*UNIT_SIZE);
            roof.addPoint(location.x*UNIT_SIZE+UNIT_SIZE, location.y*UNIT_SIZE+UNIT_SIZE/2);
            g2d.setColor(new Color(0x964B00));
            g2d.fillPolygon(roof);
            // check if mouse is in the house
            if (mouseHoverX >= 0 && mouseHoverX < world.getWidth()*UNIT_SIZE && mouseHoverY >= 0 && mouseHoverY < world.getHeight()*UNIT_SIZE){
                int currentMouseX = mouseHoverX/UNIT_SIZE;
                int currentMouseY = mouseHoverY/UNIT_SIZE;
                Point mousePoint = new Point(currentMouseX, currentMouseY);
                if (rumah.getLokasi().equals(mousePoint)){
                    g2d.setColor(Color.white);
                    g2d.setFont(new Font("Courier New", Font.PLAIN, 10));
                    g2d.drawString("Click to Enter the house", location.x*UNIT_SIZE, location.y*UNIT_SIZE -UNIT_SIZE/8);
                }
            }
        }

        // calculate the position of the FPS text
        int fpsX = mapX + 10;
        int fpsY = mapY + 20;

        // draw the FPS text
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        g2d.drawString("FPS = " + currentFPS, fpsX, fpsY);

        // draw the current world position under the mouse cursor
        if (mouseHoverX >= 0 && mouseHoverX < world.getWidth()*UNIT_SIZE && mouseHoverY >= 0 && mouseHoverY < world.getHeight()*UNIT_SIZE) {
            String worldPosition = "(" + (mouseHoverX / UNIT_SIZE) + ", " + (mouseHoverY / UNIT_SIZE) + ")" + ", " +
                    "Unit Size: " + UNIT_SIZE;
            g2d.setColor(Color.WHITE);
            g2d.drawString(worldPosition,  mapX + 10, mapY + cameraHeight - 20);
        }




    }

    private long startClickTime = 0;

    @Override
    public void mouseClicked(MouseEvent e) {
        long currentClickTime = System.currentTimeMillis();
        if (mouseHoverX >= 0 && mouseHoverX < world.getWidth()*UNIT_SIZE && mouseHoverY >= 0 && mouseHoverY < world.getHeight()*UNIT_SIZE){
            int currentMouseX = mouseHoverX/UNIT_SIZE;
            int currentMouseY = mouseHoverY/UNIT_SIZE;
            Point mousePoint = new Point(currentMouseX, currentMouseY);
            for (Rumah rumah : world.getDaftarRumah()){
                if (rumah.getLokasi().equals(mousePoint)){
                    if (currentClickTime - startClickTime >= 1000){
                        System.out.println("You entered the house");
                        HousePanel hp = new HousePanel(this, rumah);
                        this.mainThread.interrupt();
                        mainThread = null;
                        mp.remove(wop);
                        mp.remove(this);
                        mp.add(hp);
                        mp.revalidate();
                        mp.repaint();
                        startClickTime = currentClickTime;
                        stopMusic();
                    }
                }
            }
        }


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
                mapX = Math.max(0, Math.min(mapX + dx, world.getWidth()*UNIT_SIZE - cameraWidth));
                mapY = Math.max(0, Math.min(mapY + dy, world.getHeight()*UNIT_SIZE - cameraHeight));

                lastMouseX = e.getX();
                lastMouseY = e.getY();
                repaint();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // set mouseHoverX and mouseHoverY to the mouse position
        mouseHoverX = e.getX() + mapX;
        mouseHoverY = e.getY() + mapY;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0){
            UNIT_SIZE += 2;
        }
        else if (notches > 0){
            UNIT_SIZE -= 2;
        }

        if (UNIT_SIZE < 10){
            UNIT_SIZE = 10;
        } else if (UNIT_SIZE > 160) {
            UNIT_SIZE = 160;
        }
        repaint();
    }
}
