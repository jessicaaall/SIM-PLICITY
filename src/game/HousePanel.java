package game;

import entity.Rumah;

import javax.swing.*;
import java.awt.*;

public class HousePanel extends JPanel {
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
    HousePanel(WorldPanel worldPanel, Rumah rumah){
        this.worldPanel = worldPanel;
        this.rumah = rumah;
        this.mainMenuPanel = worldPanel.mmp;
        this.mainPanel = worldPanel.mp;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        JPanel eastPanel = new JPanel(null);
        eastPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        eastPanel.setBackground(Color.white);
        JPanel westPanel = new JPanel(null);
        westPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        westPanel.setBackground(Color.white);
        JPanel centerPanel = new JPanel(null);
        centerPanel.setPreferredSize(new Dimension(3*mainPanel.width/5, mainPanel.height));
        centerPanel.setBackground(Color.black);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
    }
}
