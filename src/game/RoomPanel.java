package game;

import entity.*;
import tiles.TileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoomPanel extends JPanel{
    public HousePanel hp;
    public Ruangan ruangan;
    public Rumah rumah;
    public int unitSize;
    public boolean isDragging = false;
    public int dx, dy;
    private TileManager tileManager;
    public RoomPanel(Ruangan ruangan, Rumah rumah, HousePanel hp){
        tileManager = new TileManager(this);
        this.ruangan = ruangan;
        this.rumah = rumah;
        this.hp = hp;
        unitSize = hp.unitSize;
        setLayout(null);
        setFocusable(false);
        setPreferredSize(new Dimension(ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize));
        setBounds(ruangan.getPosisi().x*unitSize, ruangan.getPosisi().y*unitSize
                ,ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //draw the tile
        for (int x = 0; x <= ruangan.getDimensi().width; x++){
            for (int y = 0; y <= ruangan.getDimensi().height; y++){
                tileManager.drawFloor(g2d, x*unitSize, y*unitSize, 2, unitSize);
            }
        }

        //draw every item on the house
        for (Objek objek : ruangan.getDaftarObjek()){
            if (objek instanceof Perabotan){
                g2d.drawImage(objek.getImage(),
                        ((Perabotan) objek).getKiriAtas().x*unitSize,
                        ((Perabotan) objek).getKiriAtas().y*unitSize,
                        ((Perabotan) objek).getDimensi().width*unitSize,
                        ((Perabotan) objek).getDimensi().height*unitSize,
                        null);
            }
        }
    }
}
