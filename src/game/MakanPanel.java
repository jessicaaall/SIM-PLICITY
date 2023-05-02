package game;

import entity.*;
import thread.ThreadAksi;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class MakanPanel extends JPanel {
    HousePanel housePanel;
    JPanel makananYangAda;

    MejaDanKursi mejaDanKursi;
    MakananButton highlightedButton;
    MakanPanel(HousePanel housePanel, MejaDanKursi mejaDanKursi){
        super(new BorderLayout());
        this.housePanel =  housePanel;
        this.mejaDanKursi = mejaDanKursi;
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(320, 320));
        setBackground(new Color(70, 180, 120));
        makananYangAda = new JPanel(new GridLayout(0,1));
        makananYangAda.setOpaque(false);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(makananYangAda);
        Inventory<Objek> inventory = housePanel.selectedSim.sim.getInventory();
        for (Map.Entry<Objek, Integer> entry : inventory.getContainer().entrySet()){
            if (entry.getKey() instanceof BisaDimakan){
                makananYangAda.add(new MakananButton((BisaDimakan) entry.getKey(), entry.getValue()));
            }
        }
        JPanel OKCancelPanel = new JPanel(new GridLayout(1, 0));
        JButton OKButton = new JButton("Makan");
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sim sim = housePanel.selectedSim.sim;
                ThreadAksi aksiMakan  = new ThreadAksi(sim.getNamaLengkap() + " makan", 30, housePanel.rumah.world);
                housePanel.rumah.world.setThreadAksi(aksiMakan);
                TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, " Makan", aksiMakan);
                housePanel.centerPanel.add(timerAksiPanel, 0);
                mejaDanKursi.makan(sim, highlightedButton.makanan);
                timerAksiPanel.startThread();
                aksiMakan.startThread();
                System.out.println("Makan " + ((Objek) highlightedButton.makanan).getNama() + ". enak");
                housePanel.centerPanel.remove(MakanPanel.this);
            }
        });
        JButton cancelButton = new JButton("Batal");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.centerPanel.remove(MakanPanel.this);
            }
        });
        OKButton.setFocusable(false);
        cancelButton.setFocusable(false);
        OKCancelPanel.add(OKButton);
        OKCancelPanel.add(cancelButton);
        OKCancelPanel.setPreferredSize(new Dimension(320, 80));
        add(scrollPane, BorderLayout.CENTER);
        add(OKCancelPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
    }
    private class MakananButton extends JButton{
        BisaDimakan makanan;
        int quantity;
        boolean highlighted = false;
        Border defaultBorder;
        MakananButton(BisaDimakan makanan, int quantity){
            super();
            this.makanan = makanan;
            this.quantity = quantity;
            Image image = MakanPanel.ObjekImage.generateScaledImage(housePanel.unitSize, housePanel.unitSize, ((Objek)makanan).getNama());
            setFocusable(false);
            defaultBorder = getBorder();
            if (image != null){
                setIcon(new ImageIcon(image));
            }
            if (makanan instanceof Makanan mak){
                setText("<html><b>" + mak.getNama()+"</b><br>" + mak.toStringResep() + "<br>kuantitas: "+quantity+"</html>");
            }
            else if (makanan instanceof BahanMakanan bahanMakanan){
                setText("<html><b>" + bahanMakanan.getNama()+"</b><br>Bahan Makanan " + bahanMakanan.getNama() + "<br>kuantitas: "+quantity +"</html>");
            }

            setHorizontalTextPosition(JButton.RIGHT);
            setVerticalTextPosition(JButton.CENTER);
            setIconTextGap(5);
            setBackground(Color.white);
            addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (highlightedButton != null && highlightedButton != MakananButton.this){
                        highlightedButton.setBorder(highlightedButton.defaultBorder);
                    }
                    if (highlighted){
                        setBorder(defaultBorder);
                        highlighted = false;
                        highlightedButton = null;
                    }
                    else{
                        setBorder(new LineBorder(Color.yellow, 5, false));
                        highlighted = true;
                        highlightedButton = MakananButton.this;
                    }
                }
            });
        }
    }

    private class ObjekImage {
        public static Image generateScaledImage(int width, int height, String nama){
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(MakanPanel.ObjekImage.class.getResourceAsStream("/res/"+nama+".png")));
                return image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            } catch (IOException e) {
                try {
                    BufferedImage image = ImageIO.read(Objects.requireNonNull(MakanPanel.ObjekImage.class.getResourceAsStream("/res/mystery box.png")));
                    return image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
            return null;
        }
    }
}
