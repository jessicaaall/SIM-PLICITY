package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    public MainPanel mp;
    public Dimension originalSize;
    public GameFrame(){
        setTitle("Sim-Plicity Kelompok 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0,0));
        setFocusable(false);
        // Store the original size of the frame

    }
}
