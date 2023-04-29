package game;

import entity.Objek;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Objects;

public class InventorySlotPanel extends JPanel {
    HousePanel hp;
    int slot = 25;
    InventoryPanel ip;

    Point location;
    MouseEvent pressed;
    InventorySlotPanel(HousePanel hp, InventoryPanel ip){

        super(new GridLayout(5,5, 0, 0));
        this.hp = hp;
        this.ip = ip;
//        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setBackground(new Color(255,255,255));
        setPreferredSize(new Dimension(5*hp.unitSize + 50, 5*hp.unitSize+50));
        setBorder(BorderFactory.createLineBorder(Color.gray, 5));
        setFocusable(false);
        setOpaque(true);
        setDoubleBuffered(true);
        for (int i = 0; i < slot; i++){
            JLabel itemSlot = new JLabel();
            itemSlot.setBackground(Color.white);
            itemSlot.setHorizontalAlignment(SwingConstants.CENTER);
            itemSlot.setBorder(BorderFactory.createLineBorder(Color.gray));
            itemSlot.setOpaque(true);
            add(itemSlot);
        }
        int iterate = 0;
        for (Map.Entry<Objek, Integer> objekEntry : hp.rumah.getSim().getInventory().getContainer().entrySet()){
            ObjekInventoryLabel oil = new ObjekInventoryLabel(objekEntry, this);
            oil.setIcon(new ImageIcon(oil.image.getScaledInstance(hp.unitSize, hp.unitSize, Image.SCALE_DEFAULT)));
            oil.setPreferredSize(new Dimension(hp.unitSize, hp.unitSize));
            this.remove(iterate);
            oil.repaint();
            this.add(oil, iterate);
            iterate++;
        }

    }

    /* add item after failed to be placed */
    public void removeItem(Objek objek){
        //cek apakah item ada
        boolean exist = false;
        boolean onlyOne = false;
        if (ip.hp.rumah.getSim().getInventory().getContainer().get(objek) == 1){
            onlyOne = true;
        }
        for (Component component : this.getComponents()){
            if (component instanceof ObjekInventoryLabel){
                //cek apakah itu objeknya
                ObjekInventoryLabel oil = (ObjekInventoryLabel) component;
                if (oil.getObjek().getKey().equals(objek)){
                    System.out.println("exist");
                    exist = true;
                }
            }
        }
        if (exist){
            ip.hp.rumah.getSim().getInventory().removeItem(objek);
            if (onlyOne){
                for (Component component : this.getComponents()){
                    if (component instanceof ObjekInventoryLabel){
                        //cek apakah itu objeknya
                        ObjekInventoryLabel oil = (ObjekInventoryLabel) component;
                        if (oil.getObjek().getKey().equals(objek)){
                            this.remove(oil);
                        }
                    }
                }
            }
        }
    }


}
