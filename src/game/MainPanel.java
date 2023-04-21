package game;

import entity.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainPanel extends JPanel  {

    GameFrame gf;
    int width = 1120;
    int height = 630;
    KeyHandler keyH = new KeyHandler();
    public MainPanel(GameFrame gf){
        this.gf = gf;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
        addKeyListener(keyH);
    }

    public void setFullScreen(){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        width = (int) screenSize.getWidth();
        height = (int) screenSize.getHeight();
        setPreferredSize(new Dimension(width, height));
        revalidate();
        repaint();
    }
    public void setOriginalSize(){
        width = 1120;
        height = 630;
        revalidate();
        repaint();
    }




}
