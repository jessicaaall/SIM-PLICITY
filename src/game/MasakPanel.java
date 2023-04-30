package game;

import entity.Kompor;
import entity.Makanan;

import javax.swing.*;
import java.awt.*;

public class MasakPanel extends JPanel {
    Kompor kompor;
    HousePanel housePanel;
    JPanel makananPanel;

    MasakPanel(HousePanel housePanel, Kompor kompor){
        super(new BorderLayout());
        this.housePanel =  housePanel;
        this.kompor = kompor;
        setPreferredSize(new Dimension(housePanel.centerPanel.getWidth(), housePanel.centerPanel.getHeight()*3/4));
        setBackground(new Color(150,178,102, 180));
        setDoubleBuffered(true);
        makananPanel = new JPanel(new GridLayout(0,1));

    }
}
