package game;

import entity.Objek;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class ObjekInventoryLabel extends JLabel {
    private Objek objek;
    ObjekInventoryLabel(Objek objek){
        super();
        this.objek = objek;
        this.setFont(new Font("Comic Sans MS", Font.ITALIC, 10));
        this.setBorder(new LineBorder(Color.lightGray, 2, true));
        this.setOpaque(true);
        this.setFocusable(false);

    }

    private class MouseListener extends MouseInputAdapter{
        @Override
        public void mouseEntered(MouseEvent e) {

        }
    }
}
