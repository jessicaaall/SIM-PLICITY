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
/*        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // If the escape key is pressed, restore the original size of the frame
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    setResizable(true);
                    mp.setOriginalSize();
                }
            }
        });*/

        // Set the frame to full screen when the expand icon is clicked
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        if (gd.isFullScreenSupported()) {
            setUndecorated(true);
            setResizable(false);
            mp.setFullScreen();
        } else {
            mp.setOriginalSize();
        }
    }
}
