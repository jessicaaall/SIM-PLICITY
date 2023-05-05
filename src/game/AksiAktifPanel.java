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
        if (perabotan instanceof Toilet){
            if (aksi.equals("ngising")){
                durasiText.setText("10");
                durasiText.setEnabled(false);
            }
            else {
                durasiText.setText("30");
                durasiText.setEnabled(false);
            }

        }
        if (aksi.equals("bersih kasur")){
            Random rand = new Random();
            int durasi = rand.nextInt(71) + 20;
            durasiText.setText(Integer.toString(durasi));
            durasiText.setEnabled(false);
        }
        if (perabotan instanceof Wastafel){
            int durasi = 0;
            if (aksi.equals("cuci tangan")){
                Random rand = new Random();
                durasi = rand.nextInt((20 - 5) + 1) + 5;
            }
            else if (aksi.equals("sikat gigi")){
                Random rand = new Random();
                durasi = rand.nextInt((60 - 10) + 1) + 10;
            }
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
            try {
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
                        ThreadAksi aksiKerja = new ThreadAksi(simnya.getNamaLengkap() + " kerja", duration, housePanel.rumah.getWorld());
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Kerja", aksiKerja);
                        simnya.kerja(duration);
                        housePanel.centerPanel.add(timerAksiPanel, 0);
                        housePanel.rumah.getWorld().setThreadAksi(aksiKerja);
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
                        ThreadAksi aksiOlahraga = new ThreadAksi(simnya.getNamaLengkap() + " olahraga", duration, housePanel.rumah.getWorld());
                        housePanel.rumah.getWorld().setThreadAksi(aksiOlahraga);
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Olahraga", aksiOlahraga);
                        simnya.olahraga(duration);
                        housePanel.centerPanel.add(timerAksiPanel, 0);
                        timerAksiPanel.startThread();
                        aksiOlahraga.startThread();
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
                            ThreadAksi aksiTidur = new ThreadAksi(simnya.getNamaLengkap() + " tidur", duration, housePanel.rumah.getWorld());
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Tidur",aksiTidur);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            housePanel.selectedSim.sim.tidur(duration, kasur);
                            housePanel.rumah.getWorld().setThreadAksi(aksiTidur);
                            timerAksiPanel.startThread();
                            aksiTidur.startThread();
                            housePanel.centerPanel.remove(this);
                        }
                        else if (aksi.equals("bersih kasur")){
                            Sim simnya = housePanel.selectedSim.sim;
                            ThreadAksi aksiBersihKasur = new ThreadAksi(simnya.getNamaLengkap()+ " bersih kasur", duration, housePanel.rumah.getWorld());
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Bersih Kasur", aksiBersihKasur);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            kasur.membersihkanKasur(simnya, duration);
                            housePanel.rumah.getWorld().setThreadAksi(aksiBersihKasur);
                            timerAksiPanel.startThread();
                            aksiBersihKasur.startThread();
                            housePanel.centerPanel.remove(this);
                        }
                    }
                    else if (perabotan instanceof Toilet toilet){
                        if (aksi.equals("ngising")){
                            Sim simnya = housePanel.selectedSim.sim;
                            ThreadAksi aksiNgising = new ThreadAksi(simnya.getNamaLengkap() + " ngising", duration, housePanel.rumah.getWorld());
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Buang Air", aksiNgising);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            housePanel.rumah.getWorld().setThreadAksi(aksiNgising);
                            toilet.buangAir(simnya);
                            timerAksiPanel.startThread();
                            aksiNgising.startThread();
                            housePanel.centerPanel.remove(this);
                        }
                        else if (aksi.equals("siram WC")){
                            Sim sim = housePanel.selectedSim.sim;
                            ThreadAksi aksiSiramToilet = new ThreadAksi(sim.getNamaLengkap() + " siram toilet",
                                    duration, housePanel.rumah.getWorld());
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Siram Toilet", aksiSiramToilet);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            toilet.siramToilet(sim);
                            housePanel.rumah.getWorld().setThreadAksi(aksiSiramToilet);
                            timerAksiPanel.startThread();
                            aksiSiramToilet.startThread();
                            housePanel.centerPanel.remove(this);

                        }

                    }
                    else if (perabotan instanceof BakMandi bakMandi){
                        Sim simnya = housePanel.selectedSim.sim;
                        ThreadAksi aksiMandi  = new ThreadAksi(simnya.getNamaLengkap() + " mandi", duration, housePanel.rumah.getWorld());
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, " Mandi", aksiMandi);
                        housePanel.centerPanel.add(timerAksiPanel, 0);
                        bakMandi.mandi(simnya, duration);
                        housePanel.rumah.getWorld().setThreadAksi(aksiMandi);
                        timerAksiPanel.startThread();
                        aksiMandi.startThread();
                        housePanel.centerPanel.remove(this);
                    }
                    else if (perabotan instanceof Wastafel wastafel){
                        if (aksi.equals("cuci tangan")){
                            Sim simnya = housePanel.selectedSim.sim;
                            ThreadAksi aksiCuciTangan  = new ThreadAksi(simnya.getNamaLengkap() + " cuci tangan"
                            , duration, housePanel.rumah.getWorld());
                            housePanel.rumah.getWorld().setThreadAksi(aksiCuciTangan);
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Cuci Tangan", aksiCuciTangan);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            wastafel.cuciTangan(simnya, duration);
                            timerAksiPanel.startThread();
                            aksiCuciTangan.startThread();
                            housePanel.centerPanel.remove(this);
                        } else if (aksi.equals("sikat gigi")){
                            Sim simnya = housePanel.selectedSim.sim;
                            ThreadAksi aksiSikatGigi = new ThreadAksi(simnya.getNamaLengkap() + " sikat gigi"
                            , duration, housePanel.rumah.getWorld());
                            housePanel.rumah.getWorld().setThreadAksi(aksiSikatGigi);
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Sikat Gigi", aksiSikatGigi);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            wastafel.sikatGigi(simnya, duration);
                            timerAksiPanel.startThread();
                            aksiSikatGigi.startThread();
                            housePanel.centerPanel.remove(this);
                        }
                    }
                    else if (perabotan instanceof Komputer komputer){
                        if (aksi.equals("bermain game")){
                            Sim sim = housePanel.selectedSim.sim;
                            ThreadAksi aksiMainGame = new ThreadAksi(sim.getNamaLengkap()+" main game", duration, housePanel.rumah.getWorld());
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Bermain Game", aksiMainGame);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            housePanel.rumah.getWorld().setThreadAksi(aksiMainGame);
                            komputer.mainGame(sim, duration);
                            timerAksiPanel.startThread();
                            aksiMainGame.startThread();
                            housePanel.centerPanel.remove(this);
                        }
                        else if (aksi.equals("kerjain tubes")){
                            Sim sim = housePanel.selectedSim.sim;
                            ThreadAksi aksiNgerjainTubes = new ThreadAksi(sim.getNamaLengkap()+" ngerjain tubes", duration, housePanel.rumah.getWorld());
                            TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Gawe Tubes", aksiNgerjainTubes);
                            housePanel.centerPanel.add(timerAksiPanel, 0);
                            komputer.ngerjainTubes(sim, duration);
                            housePanel.rumah.getWorld().setThreadAksi(aksiNgerjainTubes);
                            timerAksiPanel.startThread();
                            aksiNgerjainTubes.startThread();
                            housePanel.centerPanel.remove(this);
                        }
                    }
                    else if (perabotan instanceof TV tv){
                        Sim sim = housePanel.selectedSim.sim;
                        ThreadAksi aksiNontonTV = new ThreadAksi(sim.getNamaLengkap() + " nonton TV",
                                duration, housePanel.rumah.getWorld());
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Nonton TV", aksiNontonTV);
                        housePanel.centerPanel.add(timerAksiPanel, 0);
                        tv.nontonTV(sim, duration);
                        housePanel.rumah.getWorld().setThreadAksi(aksiNontonTV);
                        timerAksiPanel.startThread();
                        aksiNontonTV.startThread();
                        housePanel.centerPanel.remove(this);

                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
        else if (e.getSource() == cancelButton){
            housePanel.centerPanel.remove(this);
        }
    }
}
