package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameFrame extends JFrame {
    public MainPanel mp;
    public Dimension originalSize;
    public GameFrame(MainPanel mainPanel){
        mp = mainPanel;
        setTitle("Sim-Plicity Kelompok 3");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0,0));
        // Store the original size of the frame
        originalSize = mp.getPreferredSize();

        // Add a key listener to the frame
        // Add a listener to the maximize button
        addWindowStateListener(new WindowStateListener() {
            @Override
            public void windowStateChanged(WindowEvent e) {
                if ((e.getNewState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
                    // If the window is maximized, set it to full screen
                    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                    device.setFullScreenWindow(GameFrame.this);
                }
            }
        });

        // Add a key listener to the frame
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // If the escape key is pressed, restore the original size of the frame
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                    device.setFullScreenWindow(null);
                    mp.setPreferredSize(originalSize);
                    pack();
                }
            }
        });

    }
}
