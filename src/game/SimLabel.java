package game;

import entity.Ruangan;
import entity.Sim;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class SimLabel extends JLabel implements MouseListener {
    public Sim sim;
    public Image simLabelImage;
    public HousePanel housePanel;
    public int width;
    public int height;
    public boolean selected = false;
    public int unitSize = 40;
    public SimLabel(Sim sim, HousePanel housePanel){
        this.sim = sim;
        this.housePanel = housePanel;
        width = 22;
        height = unitSize;
        try {
            simLabelImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sim"+sim.getRandomSkin()+".png"))).getScaledInstance(width,height, Image.SCALE_DEFAULT);;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setPreferredSize(new Dimension(height, height));
        setSize(height, height);
        setIcon(new ImageIcon(simLabelImage));
        setAlignmentX(SwingConstants.CENTER);
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
        setAlignmentY(SwingConstants.CENTER);

        setBounds(sim.getPosisi().x*unitSize, sim.getPosisi().y*unitSize, height, height);
        setOpaque(false);
        selected = false;
        addMouseListener(this);
        revalidate();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.printf("memilih %s\n", sim.getNamaLengkap());
        for (Component component : housePanel.centerPanel.getComponents()){
            if (component instanceof SimLabel){
                ((SimLabel) component).setBorder(null);
                //cek apakah dia sudah enggak diselect dan selectedSim sudah ada
                if (housePanel.selectedSim == null){
                    continue;
                }
                if (component.getLocation().equals(housePanel.selectedSim.getLocation())){
                    housePanel.selectedSim = null;
                }
            }
        }
        if (!selected){
            selected = true;
            Color color = new Color(200, 100, 60);
            setBorder(BorderFactory.createLineBorder(color, 1, true));
            housePanel.selectedSim = this;
            return;
        }
        else{
            selected = false;
        }
        JPanel wadahButton = new JPanel(new GridLayout(0,1,0,3));
        wadahButton.setPreferredSize(new Dimension(3*unitSize, 3*unitSize));
        wadahButton.setBounds(getX()+getWidth(),getY()+getHeight(), 3*unitSize, 3*unitSize);
        wadahButton.setBackground(new Color(150, 178, 102, 200));
        AksiButton kerjaButton = new AksiButton("Aksi");
        AksiButton tutupButton = new AksiButton("Tutup");

        // Tambah Action Listener di sini
        kerjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        wadahButton.add(kerjaButton);
        wadahButton.add(tutupButton);

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
