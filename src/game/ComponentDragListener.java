package game;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class ComponentDragListener extends MouseInputAdapter {
    Component targetComponent;
    Point location;
    Point pressedPoint;
    MouseEvent pressed;

    public ComponentDragListener(Component targetComponent) {
        this.targetComponent = targetComponent;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("mengklik " + e.getSource().getClass().getSimpleName());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        System.out.print("pressed ");
        pressed = e;
        pressedPoint = e.getPoint();
        System.out.println(pressed.getSource().getClass().getSimpleName());
        if (pressed == null) {
            pressed = e;
            if (pressed == null) {
                pressed = e;
                if (pressed == null) {
                    pressed = e;
                }
            }
        }

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("dragged " + e.getSource().getClass().getSimpleName());
        if (pressed == null) {
            System.out.println("null");
            return;
        }
        Component component = e.getComponent();
        location = component.getLocation();
        int dx = (int) (location.x - pressed.getX() + e.getX());
        int dy = (int) (location.y - pressed.getY() + e.getY());
        component.setLocation(dx, dy);
        component.repaint();
    }
}
