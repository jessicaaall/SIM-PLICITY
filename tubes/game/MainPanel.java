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
        Component[] components = this.getComponents();
        for (Component component : components){
            if (component instanceof MainMenuPanel){
                remove(component);
                break;
            }
        }
        World world = World.getWorld();
        WorldPanel worldPanel = new WorldPanel(world);
        worldPanel.startMainThread();
        add(worldPanel, BorderLayout.CENTER);
        revalidate(); // to update the layout
        repaint(); // to repaint the panel
    }


}
