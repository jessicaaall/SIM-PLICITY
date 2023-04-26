package game;

import entity.World;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MainPanel extends JPanel  {

    GameFrame gf;
    int width = 1120;
    int height = 630;
    KeyHandler keyH = new KeyHandler();
    BufferedImage image;
    public MainPanel(GameFrame gf){
        this.gf = gf;
        this.setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
        setFocusable(true);
        setOpaque(true);
        requestFocus();
        addKeyListener(keyH);
        repaint();
        try {
            image = ImageIO.read(new File("res/background.jpg"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setFullScreen(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        setPreferredSize(new Dimension(width, height));
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(new ImageIcon("res/background.jpg").getImage(), 0,0, width,height, null);
    }
}
