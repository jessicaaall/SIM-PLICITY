package game;

import entity.World;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainPanel extends JPanel  {
    int width = 1120;
    int height = 630;
    public MainPanel(World world){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
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
