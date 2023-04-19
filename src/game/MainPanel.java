package game;

import entity.World;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel  {
    int width = 1120;
    int height = 630;
    public MainPanel(World world){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
    }



}
