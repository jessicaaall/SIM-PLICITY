package game;

import entity.*;
import tiles.TileManager;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RoomPanel extends JPanel {
    public HousePanel hp;
    public Ruangan ruangan;
    public Rumah rumah;
    public int unitSize;
    public boolean isDragging = false;
    public int dx, dy;
    private TileManager tileManager;

    private PerabotanLabel draggedPerabotan = null;
    private Point startDragPoint;
    public RoomPanel(Ruangan ruangan, Rumah rumah, HousePanel hp){
        tileManager = new TileManager(this);
        this.ruangan = ruangan;
        this.rumah = rumah;
        this.hp = hp;
        unitSize = hp.unitSize;
        setLayout(null);
        setFocusable(false);
        setPreferredSize(new Dimension(ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize));
        if (hp.ruanganAcuanPanel != null){
            setBounds(hp.ruanganAcuanPanel.getX()+ ruangan.getPosisi().x*unitSize, hp.ruanganAcuanPanel.getY()+ruangan.getPosisi().y*unitSize
                    ,ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize);
        }
        else{
            setBounds(ruangan.getPosisi().x*unitSize, ruangan.getPosisi().y*unitSize
                    ,ruangan.getDimensi().width*unitSize, ruangan.getDimensi().height*unitSize);
        }
        setBorder(new LineBorder(new Color(210,105,30), 4, false));

//        setBorder(BorderFactory.createLineBorder(Color.yellow, 3));
        //set perabotan label untuk ruangan ini
        for (Objek objek: ruangan.getDaftarObjek()){
            if (objek instanceof Perabotan){
//                System.out.println("kode ini dirun");
                Perabotan perabotan = (Perabotan) objek;
                PerabotanLabel pl = new PerabotanLabel(perabotan,hp ,this);
                this.hp.centerPanel.add(pl);
            }
        }
        for (Sim sim:ruangan.getDaftarSim()){
            SimLabel simLabel = new SimLabel(sim, hp);
            hp.centerPanel.add(simLabel, 0);
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        //draw the tile
        for (int x = 0; x <= ruangan.getDimensi().width; x++){
            for (int y = 0; y <= ruangan.getDimensi().height; y++){
                tileManager.drawFloor(g2d, x*unitSize, y*unitSize, 2, unitSize);
            }
        }

    }

}
