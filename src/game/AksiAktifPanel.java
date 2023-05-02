package game;

import entity.*;
import thread.ThreadAksi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class AksiAktifPanel extends JPanel implements ActionListener {
    HousePanel housePanel;
    Perabotan perabotan;

    String aksi;
    JButton OKButton;
    JButton cancelButton;
    JTextField durasiText;
    AksiAktifPanel(HousePanel housePanel, Perabotan perabotan, String aksi){
        this.housePanel = housePanel;
        this.perabotan = perabotan;
        setBackground(new Color(150, 178, 102, 150));
        setOpaque(true);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/2, housePanel.centerPanel.getHeight()/2));
        setBounds(housePanel.centerPanel.getWidth()/4, housePanel.centerPanel.getHeight()/4
                , housePanel.centerPanel.getWidth()/2,housePanel.centerPanel.getHeight()/2);
        setBorder(new LineBorder(Color.BLACK, 4, true));
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, housePanel.centerPanel.getWidth()/32, 5, housePanel.centerPanel.getWidth()/32);
        gbc.gridx = 0;
        int gridy = 0;
        JLabel aksiLabel = new JLabel("Aksi " + aksi);
        JLabel _aksiLabel = new JLabel("Durasi");
        durasiText = new JTextField();
        IntegerFilter _if = new IntegerFilter();
        ((AbstractDocument) durasiText.getDocument()).setDocumentFilter(_if);
        durasiText.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/4, housePanel.unitSize/2));
        aksiLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
        aksiLabel.setAlignmentX(SwingConstants.CENTER);
        _aksiLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        _aksiLabel.setAlignmentX(SwingConstants.CENTER);
        OKButton = new JButton("OK");
        OKButton.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/8, 2*housePanel.unitSize/3));
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/8, 2*housePanel.unitSize/3));
        add(aksiLabel, gbc);
        gbc.gridy = ++gridy;
        add(_aksiLabel, gbc);
        gbc.gridy = ++gridy;
        add(durasiText, gbc);
        gbc.gridy = ++gridy;
        add(OKButton, gbc);
        gbc.gridy = ++gridy;
        add(cancelButton, gbc);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);
        if (perabotan instanceof Toilet){
            durasiText.setText("10");
            durasiText.setEnabled(false);
        }
        if (aksi.equals("bersih kasur")){
            Random rand = new Random();
            int durasi = rand.nextInt(71) + 20;
            durasiText.setText(Integer.toString(durasi));
            durasiText.setEnabled(false);
        }
        revalidate();
        repaint();
    }
    AksiAktifPanel(HousePanel housePanel, String aksi){
        this.housePanel = housePanel;
        this.perabotan = null;
        this.aksi = aksi;
        setBackground(new Color(150, 178, 102, 150));
        setOpaque(true);
        setDoubleBuffered(true);
        setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/2, housePanel.centerPanel.getHeight()/2));
        setBounds(housePanel.centerPanel.getWidth()/4, housePanel.centerPanel.getHeight()/4
                , housePanel.centerPanel.getWidth()/2,housePanel.centerPanel.getHeight()/2);
        setBorder(new LineBorder(Color.BLACK, 4, true));
        GridBagConstraints gbc = new GridBagConstraints();
        setLayout(new GridBagLayout());
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, housePanel.centerPanel.getWidth()/32, 5, housePanel.centerPanel.getWidth()/32);
        gbc.gridx = 0;
        int gridy = 0;
        JLabel aksiLabel = new JLabel("Aksi " + aksi);
        JLabel _aksiLabel = new JLabel("Durasi");
        durasiText = new JTextField();
        IntegerFilter _if = new IntegerFilter();
        ((AbstractDocument) durasiText.getDocument()).setDocumentFilter(_if);
        durasiText.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/4, housePanel.unitSize/2));
        aksiLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 27));
        aksiLabel.setAlignmentX(SwingConstants.CENTER);
        _aksiLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
        _aksiLabel.setAlignmentX(SwingConstants.CENTER);
        OKButton = new JButton("OK");
        OKButton.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/8, 2*housePanel.unitSize/3));
        cancelButton = new JButton("Cancel");
        cancelButton.setPreferredSize(new Dimension(housePanel.centerPanel.getWidth()/8, 2*housePanel.unitSize/3));
        add(aksiLabel, gbc);
        gbc.gridy = ++gridy;
        add(_aksiLabel, gbc);
        gbc.gridy = ++gridy;
        add(durasiText, gbc);
        gbc.gridy = ++gridy;
        add(OKButton, gbc);
        gbc.gridy = ++gridy;
        add(cancelButton, gbc);
        OKButton.addActionListener(this);
        cancelButton.addActionListener(this);
        revalidate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == OKButton){
            int duration = Integer.parseInt(durasiText.getText());
            //check apakah durasi valid
            if (duration == 0){
                JOptionPane.showMessageDialog(null, "Input durasi tidak boleh nol");
            }
            else if (perabotan == null){
                if (aksi.equals("kerja")){
                    //cek durasi kerja
                    if (!(duration % 120 == 0)){
                        JOptionPane.showMessageDialog(null, "Input durasi harus kelipatan 120", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Sim simnya = housePanel.selectedSim.sim;
                    ThreadAksi aksiKerja = new ThreadAksi(simnya.getNamaLengkap() + " kerja", duration, housePanel.rumah.world);
                    housePanel.rumah.world.setThreadAksi(aksiKerja);
                    TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Kerja", aksiKerja);
                    housePanel.centerPanel.add(timerAksiPanel, 0);
                    simnya.kerja(duration);
                    timerAksiPanel.startThread();
                    aksiKerja.startThread();
                    housePanel.centerPanel.remove(this);
                }
                else if (aksi.equals("olahraga")){
                    if (!(duration % 20 == 0)){
                        JOptionPane.showMessageDialog(null, "Input harus kelipatan 20", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    Sim simnya = housePanel.selectedSim.sim;
                    ThreadAksi aksiOlahraga = new ThreadAksi(simnya.getNamaLengkap() + " olahraga", duration, housePanel.rumah.world);
                    housePanel.rumah.world.setThreadAksi(aksiOlahraga);
                    TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Olahraga", aksiOlahraga);
                    simnya.olahraga(duration);
                    housePanel.centerPanel.add(timerAksiPanel, 0);
                    timerAksiPanel.startThread();
                    aksiOlahraga.startThread();
                    housePanel.centerPanel.remove(this);
                }
                else if (aksi.equals("berkunjung")){
                    housePanel.centerPanel.remove(this);
                }
            }
            else{
                if (perabotan instanceof Kasur kasur){
                    if (aksi.equals("tidur")){
                        if (!(duration % 180 == 0)){
                            JOptionPane.showMessageDialog(null, "Input harus kelipatan 3 menit (180)", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        Sim simnya = housePanel.selectedSim.sim;
                        ThreadAksi aksiTidur = new ThreadAksi(simnya.getNamaLengkap() + " tidur", duration, housePanel.rumah.world);
                        housePanel.rumah.world.setThreadAksi(aksiTidur);
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Tidur",aksiTidur);
                        housePanel.centerPanel.add(timerAksiPanel, 0);
                        housePanel.selectedSim.sim.tidur(duration, kasur);
                        timerAksiPanel.startThread();
                        aksiTidur.startThread();
                        housePanel.centerPanel.remove(this);
                    }
                    else if (aksi.equals("bersih kasur")){
                        Sim simnya = housePanel.selectedSim.sim;
                        ThreadAksi aksiBersihKasur = new ThreadAksi(simnya.getNamaLengkap()+ " bersih kasur", duration, housePanel.rumah.world);
                        housePanel.rumah.world.setThreadAksi(aksiBersihKasur);
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Bersih Kasur", aksiBersihKasur);
                        housePanel.centerPanel.add(timerAksiPanel, 0);
                        kasur.membersihkanKasur(simnya, duration);
                        timerAksiPanel.startThread();
                        aksiBersihKasur.startThread();
                        housePanel.centerPanel.remove(this);
                    }
                }
                else if (perabotan instanceof Toilet toilet){
                    Sim simnya = housePanel.selectedSim.sim;
                    toilet.buangAir(simnya);
                    ThreadAksi aksiNgising = new ThreadAksi(simnya.getNamaLengkap() + " ngising", duration, housePanel.rumah.world);
                    housePanel.rumah.world.setThreadAksi(aksiNgising);
                    TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Buang Air", aksiNgising);
                    housePanel.centerPanel.add(timerAksiPanel, 0);
                    timerAksiPanel.startThread();
                    aksiNgising.startThread();
                    housePanel.centerPanel.remove(this);
                }
                else if (perabotan instanceof BakMandi bakMandi){
                    Sim simnya = housePanel.selectedSim.sim;
                    ThreadAksi aksiMandi  = new ThreadAksi(simnya.getNamaLengkap() + " mandi", duration, housePanel.rumah.world);
                    housePanel.rumah.world.setThreadAksi(aksiMandi);
                    TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, " Mandi", aksiMandi);
                    housePanel.centerPanel.add(timerAksiPanel, 0);
                    bakMandi.mandi(simnya, duration);
                    timerAksiPanel.startThread();
                    aksiMandi.startThread();
                    housePanel.centerPanel.remove(this);
                }
            }

        }
        else if (e.getSource() == cancelButton){
            housePanel.centerPanel.remove(this);
        }
    }
}
