package game;

import entity.Rumah;
import entity.Sim;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class WorldOptionPanel extends JPanel implements ActionListener {
    public JButton toMainMenuButton = new JButton("<html>Back to<br>Main Menu</html>");
    public JButton addHouseButton = new JButton("Add Sim");
    public JSlider volumeSlider = new JSlider(500, 860, 700);
    public JLabel timeLabel;
    public MainPanel mp;
    public WorldPanel wp;
    public WorldOptionPanel(MainPanel mainPanel, WorldPanel worldPanel){
        mp = mainPanel; wp = worldPanel;
//        this.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        setFocusable(false);
        timeLabel = new JLabel("<html> "+ wp.getWorld().getWaktu().tampilkanWaktu()[0] +"<br>" +
                wp.getWorld().getWaktu().tampilkanWaktu()[1] + "<br>" +
                wp.getWorld().getWaktu().tampilkanWaktu()[2] + "</html>");
//        BoxLayout layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(new GridLayout(0,1));
        Dimension size = new Dimension(160, wp.getHeight()/3);
        this.setSize(size);
        this.setPreferredSize(size);
        this.setBounds(wp.getWidth() - this.getWidth() - 10,10, 160, wp.getHeight()/3);
        this.setBackground(Color.white);
        timeLabel.setFocusable(false);
//        timeLabel.setOpaque(true);
        timeLabel.setVerticalTextPosition(SwingConstants.CENTER);
        timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
        timeLabel.setBounds(0,0, this.getWidth(), timeLabel.getFontMetrics(timeLabel.getFont()).getHeight()*2+15);
        toMainMenuButton.setFocusable(false);
        toMainMenuButton.setHorizontalTextPosition(JButton.CENTER);
        toMainMenuButton.setVerticalTextPosition(JButton.CENTER);
        toMainMenuButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));

        toMainMenuButton.addActionListener(this);
        toMainMenuButton.setBackground(Color.yellow);
        toMainMenuButton.setBounds(0, timeLabel.getY()+timeLabel.getHeight(),
                this.getWidth(),
                toMainMenuButton.getFontMetrics(toMainMenuButton.getFont()).getHeight()*2+5);
        addHouseButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        addHouseButton.addActionListener(this);
        addHouseButton.setFocusable(false);
        addHouseButton.setHorizontalTextPosition(JButton.CENTER);
        addHouseButton.setVerticalTextPosition(JButton.CENTER);
        addHouseButton.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        addHouseButton.setBounds(0,
                toMainMenuButton.getY() + toMainMenuButton.getHeight(),
                this.getWidth(),
                addHouseButton.getFontMetrics(addHouseButton.getFont()).getHeight()+5);
        add(timeLabel);
        add(toMainMenuButton);
        add(addHouseButton);
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
                addHouseButton.getY()+addHouseButton.getHeight(),
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

        else if (e.getSource() == addHouseButton){
            JPanel panel = new JPanel(new GridLayout(0, 1));
            JLabel label = new JLabel("<html>Set the coordinate of your house:<br></html>");
            JTextField xField = new JTextField();
            JTextField yField = new JTextField();
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
        }
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        RoundRectangle2D shape = new RoundRectangle2D.Double(0,0, getWidth(), getHeight(), 10, 10);
        g2d.setClip(shape);
        super.paintComponent(g);
    }
}
