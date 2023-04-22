package game;

import entity.Objek;
import entity.Perabotan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PerabotanLabel extends JLabel {
    RoomPanel roomPanel;
    public Perabotan getPerabotan() {
        return perabotan;
    }

    public void setPerabotan(Perabotan perabotan) {
        this.perabotan = perabotan;
    }

    private Perabotan perabotan;
    private Image image;
    public PerabotanLabel(Perabotan perabotan, RoomPanel roomPanel){
        this.perabotan = perabotan;
        this.roomPanel = roomPanel;
        int width = perabotan.getDimensi().width*roomPanel.unitSize;
        int height = perabotan.getDimensi().height*roomPanel.unitSize;
        image = perabotan.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        this.setIcon(new ImageIcon(image));
        this.setPreferredSize(new Dimension(perabotan.getDimensi().width*roomPanel.unitSize, perabotan.getDimensi().height*roomPanel.unitSize));
        this.setBounds(perabotan.getKiriAtas().x*roomPanel.unitSize
                , perabotan.getKiriAtas().y*roomPanel.unitSize
                , perabotan.getDimensi().width*roomPanel.unitSize
                , perabotan.getDimensi().height*roomPanel.unitSize);
    }

}
