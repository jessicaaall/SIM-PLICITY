package game;

import entity.Objek;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Map;

public class InventorySlotPanel extends JPanel {
    HousePanel hp;
    int slot = 25;

    Point location;
    MouseEvent pressed;
    InventorySlotPanel(HousePanel hp){

        super(new GridLayout(5,5, 0, 0));
        this.hp = hp;
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
            oil.setIcon(new ImageIcon(objekEntry.getKey().getImage().getScaledInstance(hp.unitSize, hp.unitSize, Image.SCALE_DEFAULT)));
            oil.setPreferredSize(new Dimension(hp.unitSize, hp.unitSize));
            this.remove(iterate);
            oil.repaint();
            this.add(oil, iterate);
            iterate++;
        }

    }


}
