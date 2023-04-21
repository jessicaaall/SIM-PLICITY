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


        GameFrame frame = new GameFrame();
        MainPanel mainPanel = new MainPanel(frame);
        mainPanel.gf = frame;
        MainMenuPanel mainMenuPanel = new MainMenuPanel(mainPanel);
        mainPanel.add(mainMenuPanel);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // game clock
    }
}

