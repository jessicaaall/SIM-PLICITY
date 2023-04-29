package game;

import entity.Rumah;
import entity.Sim;
import data.*;
import entity.World;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class WorldOptionPanel extends JPanel implements ActionListener {
    public JButton toMainMenuButton = new JButton("Back to Main Menu");
    public JButton addHouseButton = new JButton("Add Sim");
    public JButton changeSimButton = new JButton("Change Sim");
    public JButton viewCurrentLocButton = new JButton("View Current Location");
    public JButton saveButton = new JButton("Save");
    public JSlider volumeSlider = new JSlider(500, 860, 700);
    public JLabel timeLabel;
    public MainPanel mp;
    public WorldPanel wp;
    public World loadedWorld;
    public WorldOptionPanel(MainPanel mainPanel, WorldPanel worldPanel){
        mp = mainPanel; wp = worldPanel;
//        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        loadedWorld = wp.getWorld();
        setFocusable(false);
        timeLabel = new JLabel("<html> "+ wp.getWorld().getWaktu().tampilkanWaktu()[0] +"<br>" +
                wp.getWorld().getWaktu().tampilkanWaktu()[1] + "<br>" +
                wp.getWorld().getWaktu().tampilkanWaktu()[2] + "</html>");
//        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        GridLayout layout = new GridLayout(0, 1);
        layout.setVgap(5);
        setLayout(layout);
        Dimension size = new Dimension(170, wp.getHeight()/2+10);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setBounds(wp.getWidth() - this.getWidth() - 10,10, 170, wp.getHeight()/2+10);
        this.setBackground(Color.white);
        timeLabel.setFocusable(false);
//        timeLabel.setOpaque(true);
        timeLabel.setVerticalTextPosition(SwingConstants.CENTER);
        timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
        timeLabel.setBounds(0,0, this.getWidth(), timeLabel.getFontMetrics(timeLabel.getFont()).getHeight()*2+15);

        toMainMenuButton.setFocusable(false);                                           // done
        toMainMenuButton.setHorizontalTextPosition(JButton.CENTER);                               // done
        toMainMenuButton.setVerticalTextPosition(JButton.CENTER);                                 // done
        toMainMenuButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));             // done
        toMainMenuButton.addActionListener(this);                                                 // done
        toMainMenuButton.setBackground(Color.yellow);
        toMainMenuButton.setBounds(0, timeLabel.getY()+timeLabel.getHeight(),
                this.getWidth(),
                toMainMenuButton.getFontMetrics(toMainMenuButton.getFont()).getHeight()+5);

        addHouseButton.addActionListener(this);                                                   // done
        addHouseButton.setFocusable(false);                                             // done
        addHouseButton.setHorizontalTextPosition(JButton.CENTER);                                 // done
        addHouseButton.setVerticalTextPosition(JButton.CENTER);                                   // done
        addHouseButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));               // done
        addHouseButton.setBounds(0,
                toMainMenuButton.getY() + toMainMenuButton.getHeight(),
                this.getWidth(),
                addHouseButton.getFontMetrics(addHouseButton.getFont()).getHeight()+5);

        changeSimButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        changeSimButton.setVerticalTextPosition(JButton.CENTER);
        changeSimButton.setHorizontalTextPosition(JButton.CENTER);
        changeSimButton.setFocusable(false);
        changeSimButton.setBounds(0,
                changeSimButton.getY() + changeSimButton.getHeight(),
                this.getWidth(),
                changeSimButton.getFontMetrics(changeSimButton.getFont()).getHeight()+5);

        viewCurrentLocButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        viewCurrentLocButton.setVerticalTextPosition(JButton.CENTER);
        viewCurrentLocButton.setHorizontalTextPosition(JButton.CENTER);
        viewCurrentLocButton.setFocusable(false);
        viewCurrentLocButton.addActionListener(this);
        viewCurrentLocButton.setBounds(0,
                viewCurrentLocButton.getY() + viewCurrentLocButton.getHeight(),
                this.getWidth(),
                viewCurrentLocButton.getFontMetrics(viewCurrentLocButton.getFont()).getHeight()+5);

        saveButton.setFont(new Font("Comic Sans MS", Font.BOLD, 12));
        saveButton.setVerticalTextPosition(JButton.CENTER);
        saveButton.setHorizontalTextPosition(JButton.CENTER);
        saveButton.setFocusable(false);
        saveButton.addActionListener(this);
        saveButton.setBounds(0,
                addHouseButton.getY() + addHouseButton.getHeight(),
                this.getWidth(),
                saveButton.getFontMetrics(saveButton.getFont()).getHeight()+5);

        add(timeLabel);
        add(toMainMenuButton);
        add(addHouseButton);
        add(changeSimButton);
        add(viewCurrentLocButton);
        add(saveButton);

        volumeSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                float volume = (float) volumeSlider.getValue()/10f;
                if (!(volume < 61f)){
                    wp.sound.setVolume(-80f+volume);
                }
                else{
                    wp.sound.setVolume(-86f);
                }
            }
        });
        JLabel music = new JLabel("Music Volume");
        music.setFont(new Font("Courier New", Font.BOLD, 10));
        music.setHorizontalTextPosition(SwingConstants.CENTER);
        music.setBackground(Color.white);
        music.setOpaque(true);
        music.setBounds((this.getWidth()-music.getFontMetrics(music.getFont()).stringWidth(music.getText()))/2,
                saveButton.getY()+saveButton.getHeight(),
                this.getWidth(),
                music.getFontMetrics(music.getFont()).getHeight()+3);
        add(music);
        volumeSlider.setBounds(0, music.getY()+music.getHeight(), this.getWidth(), 15);
        add(volumeSlider);
        wp.wop = this;
    }

    private Sim chosenSim;
    private String chosenSimName;
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toMainMenuButton){

            JPanel warningMessage = new JPanel(new GridLayout(0,1));
            JLabel label = new JLabel("Pastikan bahwa file data telah disimpan.");
            JLabel label2 = new JLabel("Yakin ingin keluar?");
            warningMessage.add(label);warningMessage.add(label2);
            int result = JOptionPane.showConfirmDialog(null, warningMessage, "Reminder"
                    , JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
            if (result == JOptionPane.OK_OPTION){
                System.out.println("Exit Game");
                wp.stopMusic();
                wp.mainThread.interrupt();
                wp.mainThread =null;
                mp.remove(toMainMenuButton);
                mp.add(wp.mmp, BorderLayout.CENTER);
                mp.remove(this);
                mp.remove(wp);
                mp.revalidate();
                mp.repaint();
            }

        } else if (e.getSource() == addHouseButton){
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel label = new JLabel("<html>Set the coordinate of your house:<br></html>");
            JTextField xField = new JTextField();
            ((AbstractDocument)xField.getDocument()).setDocumentFilter(new IntegerFilter());
            JTextField yField = new JTextField();
            ((AbstractDocument)yField.getDocument()).setDocumentFilter(new IntegerFilter());
            JButton colorButton = new JButton("Choose Color");
            colorButton.setFocusable(false);
            final Color[] color = new Color[1];
            colorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    Color chosenColor = JColorChooser.showDialog(null, "Choose a color", Color.white);
                    color[0] = chosenColor;
                }
            });
            ArrayList<Sim> daftarSim = wp.getWorld().getDaftarSim();
            String[] listNamaSim = new String[daftarSim.size()];
            for (int i = 0; i < listNamaSim.length; i++){
                listNamaSim[i] = daftarSim.get(i).getNamaLengkap();
            }
            // masukan nama sim
            JTextField namaSimField = new JTextField();
            panel.add(new JLabel("<html>Masukkan Nama Sim"));
            panel.add(namaSimField);
            panel.add(label);
            panel.add(new JLabel("<html>X Coordinate:<br></html>"));
            panel.add(xField);
            panel.add(new JLabel("<html>Y Coordinate:<br></html>"));
            panel.add(yField);
            panel.add(new JLabel("<html>Color:<br></html>"));
            panel.add(colorButton);
            int result = JOptionPane.showConfirmDialog(null, panel, "Tambah Sim",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String namaSim = namaSimField.getText();
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                // cek apakah nama sim, dan koordinat sudah dipakai sudah ada
                boolean hasSim = false;
                boolean occupied = false;
                for (Sim sim: wp.getWorld().getDaftarSim()){
                    if (sim.getNamaLengkap().equals(namaSim)){
                        hasSim = true;
                        break;
                    }
                }
                for (Rumah rumah:wp.getWorld().getDaftarRumah()){
                    if (rumah.getLokasi().equals(new Point(x,y))){
                        occupied = true;
                    }
                }
                if (hasSim){
                    /* add popup that show message that the sim Name has already been taken */
                    JOptionPane.showMessageDialog(null, "The Sim name has already been taken.");
                }
                else if (occupied){
                    /* add popup that show message that the place is occupied */
                    JOptionPane.showMessageDialog(null, "The position is already occupied.");
                }
                else if (wp.getWorld().isLimitSimCreation()){
                    JOptionPane.showMessageDialog(null, "Anda hanya bisa membuat Sim 1 kali sehari");
                }
                else{
                    Sim simBaru = new Sim(namaSim, wp.getWorld());
                    Rumah rumahBaru = new Rumah(x, y,simBaru, color[0],wp.getWorld());
                    wp.getWorld().tambahRumah(rumahBaru);
                    wp.getWorld().tambahSim(simBaru);
                }
            }
        } else if (e.getSource() == saveButton) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JTextField namaFile = new JTextField();

            panel.add(new JLabel("<html>Masukkan Nama File Yang Ingin Disimpan"));
            panel.add(namaFile);
            int result = JOptionPane.showConfirmDialog(null, panel, "Save",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                String saveFile = namaFile.getText();
                SaveLoad saveLoad = new SaveLoad();
                saveLoad.save(saveFile, loadedWorld);
            }

        } else if (e.getSource() == viewCurrentLocButton) {
            JPanel panel = new JPanel(new GridLayout(0,1));
            panel.setBackground(new Color(150, 178, 102));
            JLabel titleLabel = new JLabel("LOKASI SIM SAAT INI");
            JLabel lineLabel = new JLabel("-------------------------------------------");
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lineLabel.setHorizontalAlignment(SwingConstants.CENTER);
            JLabel rumahLocLabel = new JLabel(" Rumah    : (" + (int) loadedWorld.getChosenSim().getLocRuang().getInfoRumah().getLokasi().getX() + ", " + (int) loadedWorld.getChosenSim().getLocRuang().getInfoRumah().getLokasi().getY() + ")");
            JLabel ruanganLocLabel = new JLabel(" Ruangan  : " + loadedWorld.getChosenSim().getLocRuang().getNama());
            panel.add(titleLabel);
            panel.add(lineLabel);
            panel.add(rumahLocLabel);
            panel.add(ruanganLocLabel);
            panel.setOpaque(true);
            panel.setBorder(new LineBorder(Color.BLACK, 3, true));
            panel.setLocation(wp.getMapX()+wp.getWidth()/2, wp.getMapY()+wp.getHeight()/2);
            panel.setBounds(wp.getMapX()+wp.getWidth()/2, wp.getMapY()+wp.getHeight()/2, 200, 100);
            panel.setFocusable(false);

            wp.add(panel);
            wp.revalidate();
            wp.repaint();
        }
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RoundRectangle2D shape = new RoundRectangle2D.Double(0,0, getWidth(), getHeight(), 10, 10);
        g2d.setClip(shape);
    }
}