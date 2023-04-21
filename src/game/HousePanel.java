package game;

import entity.Ruangan;
import entity.Rumah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HousePanel extends JPanel implements ActionListener, Runnable {
    public WorldPanel worldPanel;
    public Rumah rumah;
    public int unitSize = 40;
    public MainMenuPanel mainMenuPanel;
    public MainPanel mainPanel;
    private boolean isDragging = false;
    public int mapX = 0;
    public int mapY = 0;
    private int cameraWidth;
    private int cameraHeight;
    private int lastMouseX, lastMouseY;
    private int mouseHoverX = -1;
    private int mouseHoverY = -1;
    private Thread thread;
    private int FPS = 60;
    private int currentFPS;
    JButton backToMainMenuButton = new JButton("To Main Menu");
    JButton backToWorldButton = new JButton("Keluar rumah");
    HousePanel(WorldPanel worldPanel, Rumah rumah){
        this.worldPanel = worldPanel;
        this.rumah = rumah;
        this.mainMenuPanel = worldPanel.mmp;
        this.mainPanel = worldPanel.mp;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        backToMainMenuButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        backToMainMenuButton.setBackground(Color.green);
        backToMainMenuButton.setForeground(Color.black);
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(this);
        backToWorldButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 10));
        backToWorldButton.setBackground(Color.green);
        backToWorldButton.setForeground(Color.black);
        backToWorldButton.setFocusable(false);
        backToWorldButton.addActionListener(this);
        JPanel eastPanel = new JPanel(null);
        eastPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        eastPanel.setBackground(Color.pink);
        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        westPanel.setBackground(Color.pink);
        westPanel.add(backToMainMenuButton);
        westPanel.add(backToWorldButton);
        JPanel centerPanel = new JPanel(null);
        centerPanel.setPreferredSize(new Dimension(3*mainPanel.width/5, mainPanel.height));
        centerPanel.setBackground(Color.black);
        for (Ruangan ruangan : rumah.getDaftarRuangan()){
            RoomPanel rp = new RoomPanel(ruangan, this.rumah, this);
            centerPanel.add(rp);
        }
        this.add(eastPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
        startThread();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenuButton){
            mainPanel.remove(this);
            mainPanel.add(mainMenuPanel);
            thread.interrupt();
            thread = null;
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        else if (e.getSource() == backToWorldButton){
            mainPanel.remove(this);
            mainPanel.add(worldPanel.wop);
            mainPanel.add(worldPanel);
            thread.interrupt();
            thread = null;
            mainPanel.revalidate();
            mainPanel.repaint();
            worldPanel.startMainThread();
            worldPanel.playMusic(0);
        }
    }

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (thread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                repaint();
                delta--;
                drawCount++;
            }
            if (timer >= Math.pow(10, 9)){
                currentFPS = drawCount;
                drawCount = 0;
                timer = 0;
            }

        }
    }
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }
}
