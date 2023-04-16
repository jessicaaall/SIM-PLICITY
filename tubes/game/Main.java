package game;
import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        MainPanel mainPanel = new MainPanel();
        MainMenuPanel mainMenuPanel = new MainMenuPanel(mainPanel);
        //frame.setBackground(Color.pink);
        mainPanel.add(mainMenuPanel);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        // game clock
    }
}

