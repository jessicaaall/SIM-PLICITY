package game;

import entity.*;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;

public class RoomPanel extends JPanel {
    public HousePanel hp;
    public Ruangan ruangan;
    public Rumah rumah;
    public int unitSize;
    private TileManager tileManager;
    public RoomPanel(Ruangan ruangan, Rumah rumah, HousePanel hp){
        tileManager = new TileManager(this);
        this.ruangan = ruangan;
        this.rumah = rumah;
        this.hp = hp;
        unitSize = hp.unitSize;
        setLayout(null);
        setPreferredSize(new Dimension(ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize));
        setBounds(ruangan.getPosisi().x, ruangan.getPosisi().y
                ,ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //draw the tile
        for (int x = 0; x <= ruangan.getDimensi().width; x++){
            for (int y = 0; y <= ruangan.getDimensi().height; y++){
                tileManager.drawFloor(g2d, x*unitSize, y*unitSize);
            }
        }
    }

}