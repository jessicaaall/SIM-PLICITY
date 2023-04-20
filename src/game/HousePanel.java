package game;

import entity.Ruangan;
import entity.Rumah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HousePanel extends JPanel implements ActionListener {
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
    JButton backToMainMenuButton = new JButton("To Main Menu");
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
        JPanel eastPanel = new JPanel(null);
        eastPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        eastPanel.setBackground(Color.pink);
        JPanel westPanel = new JPanel(new FlowLayout());
        westPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        westPanel.setBackground(Color.pink);
        westPanel.add(backToMainMenuButton);
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenuButton){
            mainPanel.remove(this);
            mainPanel.add(mainMenuPanel);
            mainPanel.revalidate();
            mainPanel.repaint();
        }
    }
}
