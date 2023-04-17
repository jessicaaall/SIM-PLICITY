package game;

import entity.World;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel  {

    public MainPanel(){
        this.setPreferredSize(new Dimension(800, 640));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
    }
    public void showWorldPanel() {
        World world = new World();
        WorldPanel worldPanel = new WorldPanel(world, this);
        Component[] components = this.getComponents();
        for (Component component : components){
            if (component instanceof MainMenuPanel){
                worldPanel.mmp = (MainMenuPanel) component;
                remove(component);
                break;
            }
        }

        worldPanel.startMainThread();
        add(worldPanel, BorderLayout.PAGE_START);
        revalidate(); // to update the layout
        repaint(); // to repaint the panel
    }


}
