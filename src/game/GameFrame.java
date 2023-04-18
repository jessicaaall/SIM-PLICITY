package game;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    public MainPanel mp;
    public GameFrame(MainPanel mainPanel){
//        GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        mp = mainPanel;
        setTitle("Sim-Plicity Kelompok 3");
//        setSize(800, 640);
//        setUndecorated(true);
//        device.setFullScreenWindow(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0,0));
        JPanel northPanel = new JPanel();
        northPanel.setPreferredSize(new Dimension(mp.getWidth(), 1));
        northPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(northPanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setPreferredSize(new Dimension(mp.getWidth(), 1));
        southPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(southPanel, BorderLayout.SOUTH);

        JPanel eastPanel = new JPanel();
        eastPanel.setPreferredSize(new Dimension(1, mp.getHeight()));
        eastPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(eastPanel, BorderLayout.EAST);

        JPanel westPanel = new JPanel();
        westPanel.setPreferredSize(new Dimension(1, mp.getHeight()));
        westPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(westPanel, BorderLayout.WEST);

        JPanel centerPanel = new JPanel();
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        add(centerPanel, BorderLayout.CENTER);
//        setPreferredSize(new Dimension(mp.getWidth(), mp.getHeight()));
//        setBounds(0,0, mp.getWidth(), mp.getHeight());
    }
}
