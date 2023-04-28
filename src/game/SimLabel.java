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
        width = 20;
        height = unitSize;
        simLabelImage = sim.getSimImage().getScaledInstance(width,height, Image.SCALE_DEFAULT);
        setPreferredSize(new Dimension(width, height));
        setSize(width, height);
        setIcon(new ImageIcon(simLabelImage));
        setBounds(sim.getPosisi().x*unitSize, sim.getPosisi().y*unitSize, width, height);
        setOpaque(false);
        revalidate();
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (Component component : housePanel.centerPanel.getComponents()){
            if (component instanceof SimLabel){
                ((SimLabel) component).setBorder(null);
            }
        }
        if (!selected){
            selected = true;
            Color color = new Color(200, 100, 60);
            setBorder(BorderFactory.createLineBorder(color, 3, true));
            System.out.printf("memilih %s", sim.getNamaLengkap());
            return;
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
