package game;

import entity.World;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel  {
    int width = 1120;
    private World world;
    int height = 630;
    public MainPanel(World world){
        this.world = world;
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.pink);
        this.setDoubleBuffered(true);
    }
    public void showWorldPanel() {
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
        add(worldPanel, BorderLayout.WEST);
        revalidate(); // to update the layout
        repaint(); // to repaint the panel
    }


}
