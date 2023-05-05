package game;

import entity.Kompor;
import entity.Makanan;
import thread.ThreadAksi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MasakPanel extends JPanel {
    Kompor kompor;
    HousePanel housePanel;
    JPanel daftarMasakanPanel;
    JPanel infoMasakanPanel;

    Makanan makananTerpilih;

    MasakPanel(HousePanel housePanel, Kompor kompor){
        super(new BorderLayout());
        this.housePanel =  housePanel;
        this.kompor = kompor;
        setPreferredSize(new Dimension(housePanel.centerPanel.getWidth(), housePanel.centerPanel.getHeight()*3/4));
        setBackground(new Color(150,178,102));
        setBounds(0, 0, housePanel.centerPanel.getWidth(), housePanel.centerPanel.getHeight()*3/4);
        setDoubleBuffered(true);
        daftarMasakanPanel = new JPanel(new GridLayout(0,1));
        daftarMasakanPanel.setFocusable(false);
        daftarMasakanPanel.setOpaque(false);
        JLabel daftarMakanan = new JLabel("Daftar Makanan");
        daftarMakanan.setAlignmentX(SwingConstants.CENTER);
        daftarMakanan.setHorizontalTextPosition(JLabel.CENTER);
        daftarMakanan.setHorizontalAlignment(SwingConstants.CENTER);
        daftarMakanan.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        daftarMasakanPanel.add(daftarMakanan);
        for (Makanan makanan : Makanan.daftarMakanan){
            daftarMasakanPanel.add(new MasakanButton(makanan));
        }
        daftarMasakanPanel.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()*3/4, housePanel.centerPanel.getHeight()*3/4));
        infoMasakanPanel = new JPanel(new BorderLayout());
//        infoMasakanPanel.setBackground(new Color(150,210,102, 180));
        infoMasakanPanel.setOpaque(false);
//        infoMasakanPanel.setBorder(new LineBorder(Color.BLACK, 5, true));
        infoMasakanPanel.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/4, housePanel.centerPanel.getHeight()*3/4));

        JButton OKButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");
        OKButton.setFocusable(false);
        cancelButton.setFocusable(false);
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.centerPanel.remove(MasakPanel.this);
            }
        });
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    kompor.masak(housePanel.selectedSim.sim, makananTerpilih);
                    housePanel.centerPanel.remove(MasakPanel.this);
                    int waktu = (int) ((double)makananTerpilih.getPoinKekenyangan() * 1.5);
                    ThreadAksi aksiMasak = new ThreadAksi("Masak "+ makananTerpilih.getNama(), waktu, housePanel.rumah.getWorld());
                    housePanel.rumah.getWorld().setThreadAksi(aksiMasak);
                    aksiMasak.start();
                    TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Masak",aksiMasak);
                    housePanel.centerPanel.add(timerAksiPanel, 0);
                    timerAksiPanel.startThread();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JPanel panelButton = new JPanel(new GridLayout(1, 2));
        panelButton.add(OKButton);
        panelButton.add(cancelButton);
        panelButton.setOpaque(false);
        infoMasakanPanel.add(panelButton, BorderLayout.SOUTH);
        add(daftarMasakanPanel, BorderLayout.CENTER);
        add(infoMasakanPanel, BorderLayout.WEST);
        revalidate();
        repaint();
    }

    private class MasakanButton extends JButton{
        Makanan masakan;
        MasakanButton(Makanan makanan){
            masakan = makanan;
            Image image = ObjekImage.generateScaledImage(housePanel.unitSize, housePanel.unitSize, makanan.getNama());
            setFocusable(false);
            if (image != null){
                setIcon(new ImageIcon(image));
            }
            setText("<html><b>" + makanan.getNama()+"</b><br>" + makanan.toStringResep() +"</html>");
            setHorizontalTextPosition(JButton.RIGHT);
            setVerticalTextPosition(JButton.CENTER);
            setIconTextGap(5);
            setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
            setBackground(Color.white);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    for (Component component : infoMasakanPanel.getComponents()){
                        if (component instanceof JLabel j){
                            infoMasakanPanel.remove(j);
                        }
                    }
                    JLabel infoMakanan = new JLabel("<html>" + makanan.getDeskripsi() +"</html>");
                    infoMakanan.setPreferredSize(new Dimension(MasakPanel.this.getPreferredSize().width/4 - 8, MasakPanel.this.getPreferredSize().height));
                    makananTerpilih = masakan;
                    infoMasakanPanel.add(infoMakanan, BorderLayout.CENTER);
                    infoMasakanPanel.revalidate();
                    infoMasakanPanel.repaint();

                }
            });
        }
    }

    private class ObjekImage {
        public static Image generateScaledImage(int width, int height, String nama){
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(ObjekImage.class.getResourceAsStream("/res/"+nama+".png")));
                return image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            } catch (IOException e) {
                try {
                    BufferedImage image = ImageIO.read(Objects.requireNonNull(ObjekImage.class.getResourceAsStream("/res/mystery box.png")));
                    return image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return null;
        }
    }
}
