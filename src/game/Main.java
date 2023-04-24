package game;
import entity.Rumah;
import entity.World;

import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        SimplicityUIManager suim = new SimplicityUIManager();
        GameFrame frame = new GameFrame();
        MainPanel mainPanel = new MainPanel(frame);
        mainPanel.gf = frame;
        MainMenuPanel mainMenuPanel = new MainMenuPanel(mainPanel);
        mainPanel.add(mainMenuPanel);
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // game clock
    }
}

