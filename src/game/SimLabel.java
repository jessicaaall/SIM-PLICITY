package game;

import entity.Ruangan;
import entity.Sim;

import javax.swing.*;
import java.awt.*;
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
        simLabelImage = new ImageIcon("/res/sim"+(new Random().nextInt(4)+1)+".png").getImage();
        width = unitSize;
        height = unitSize;
        setPreferredSize(new Dimension(unitSize, unitSize));
        setSize(unitSize, unitSize);
        setBounds(sim.getPosisi().x*unitSize, sim.getPosisi().y*unitSize, unitSize, unitSize);
        setOpaque(false);

    }
}
