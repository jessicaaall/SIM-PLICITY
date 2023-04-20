package game;
import entity.Rumah;
import entity.World;

import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        World world = new World();
//        world.tambahRumah(new Rumah(10,10, Color.yellow, world));
//        world.tambahRumah(new Rumah(63,63, Color.red, world));
        MainPanel mainPanel = new MainPanel(world);
        GameFrame frame = new GameFrame(mainPanel);
        MainMenuPanel mainMenuPanel = new MainMenuPanel(mainPanel);
        mainPanel.add(mainMenuPanel);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);

        // game clock
    }
}

