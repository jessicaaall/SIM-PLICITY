package game;

import javax.swing.*;
import java.awt.*;

public class SimplicityUIManager {
    public SimplicityUIManager(){
        UIManager.put("OptionPane.background", new Color(255,255,255));
//        UIManager.put("OptionPane.border", BorderFactory.createLineBorder(Color.black, 3));
        UIManager.put("OptionPane.messageFont", new Font("Comic Sans MS", Font.PLAIN, 12));
        UIManager.put("OptionPane.font", new Font("Comic Sans MS", Font.PLAIN, 12));
        UIManager.put("OptionPane.messageForeground", Color.black);
        UIManager.put("OptionPane.titleFont", new Font("Comic Sans MS", Font.PLAIN, 16));
        UIManager.put("OptionPane.buttonFont", new Font("Comic Sans MS", Font.PLAIN, 12));
        UIManager.put("Button.foreground", new Color(51, 102, 0));
        UIManager.put("Button.focusable", false);
        UIManager.put("TextField.background", new Color(255,204,229));
        UIManager.put("TextField.font", new Font("Comic Sans MS", Font.PLAIN, 12));
        UIManager.put("Label.font", new Font("Comic Sans MS", Font.PLAIN, 12));
        UIManager.put("InternalFrame.titleFont",  new Font("Comic Sans MS", Font.PLAIN, 12));
    }
}
