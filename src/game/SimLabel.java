package game;

import entity.Ruangan;
import entity.Sim;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class SimLabel extends JLabel {
    public Sim sim;
    public Image simLabelImage;
    public HousePanel housePanel;
    public int width;
    public int height;
    public int unitSize = 40;
    public SimLabel(Sim sim, HousePanel housePanel){
        this.sim = sim;
        this.housePanel = housePanel;
        width = unitSize;
        height = unitSize;
        try {
            simLabelImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sim"+(new Random().nextInt(4)+1)+".png"))).getScaledInstance(width,height, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setPreferredSize(new Dimension(unitSize, unitSize));
        setSize(unitSize, unitSize);
        setIcon(new ImageIcon(simLabelImage));
        setBounds(sim.getPosisi().x*unitSize, sim.getPosisi().y*unitSize, unitSize, unitSize);
        setOpaque(false);
        revalidate();
        repaint();
    }
}
