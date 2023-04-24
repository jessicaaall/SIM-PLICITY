package game;

import javax.swing.*;
import java.awt.*;

public class SimplicityDialog extends JDialog {
    public SimplicityDialog(String title) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle(title);
        getContentPane().setBackground(Color.yellow);
        getContentPane().setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
        setUndecorated(true);
    }
}
