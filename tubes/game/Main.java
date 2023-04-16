package game;
import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
//        MenuPanel menuPanel = new MenuPanel();
        MainMenuPanel mainPanel = new MainMenuPanel();

//        mainPanel.add(menuPanel, mainPanel.gbc);
        frame.setBackground(Color.pink);
        frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);

//        boolean running = false;
//
//        // the main menu
//        Thread aksiAktif;
//        String selected;
//        if (selected.equals("Start")){
//            running = true;
//        }
//
//        // the help menu
//        if(selected.equals("Help")){
//
//        }
//
//        //show the time on screen
//        World world = World.getWorld();
//        Thread worldThread = new Thread(() -> {
//            while (running && aksiAktif.isAlive()){
//                world.getWaktu().increaseWaktu();
//            }
//        });
    }
}

