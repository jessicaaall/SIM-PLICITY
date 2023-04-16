package game;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public GameFrame(){
//        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        setTitle("Sim-Plicity Kelompok 3");
//        setSize(800, 640);
//        setUndecorated(true);
//        device.setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
//        setBounds(0,0, 800, 640);
    }
}
