package game;

import entity.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class GantiKerjaPanel extends JPanel {

    HousePanel housePanel;
    Pekerjaan selected;
    public GantiKerjaPanel(Sim sim, HousePanel housePanel) {
        super(new BorderLayout());
        this.housePanel = housePanel;
        setBounds(160,200, 350, 200);
        setBackground(new Color(150, 178, 102));
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK, 3, true));
        setFocusable(false);

        JPanel mainPanel = new JPanel(new GridLayout(0,1,0,5));
        mainPanel.setOpaque(false);
        mainPanel.setFocusable(false);

        ArrayList<Pekerjaan> pekerjaan = new ArrayList<>();
        for (int i = 24; i <= 34; i++) {
            pekerjaan.add(new Pekerjaan(i));
        }
        pekerjaan.remove(sim.getPekerjaan().getId()-24);

        String[] namaPekerjaan = new String[pekerjaan.size()];
        for (int i = 0; i < namaPekerjaan.length; i++) {
            namaPekerjaan[i] = pekerjaan.get(i).getNamaPekerjaan();
        }

        JLabel choosePekerjaanLabel = new JLabel("PILIH PEKERJAAN BARU");
        choosePekerjaanLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
        choosePekerjaanLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel comboBoxWrap = new JPanel();
        comboBoxWrap.setOpaque(false);
        JComboBox<String> choosePekerjaan = new JComboBox<>(namaPekerjaan);
        choosePekerjaan.setFont(new Font("Comic Sans MS", Font.PLAIN, 12));
        choosePekerjaan.setBackground(new Color(150, 178, 102));
        ((JLabel) choosePekerjaan.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
        choosePekerjaan.setPreferredSize(new Dimension(170, 35));
        comboBoxWrap.add(choosePekerjaan);

        JLabel gajiLabel = new JLabel();
        gajiLabel.setHorizontalAlignment(SwingConstants.CENTER);

        selected = pekerjaan.get(choosePekerjaan.getSelectedIndex());
        int gaji = selected.getGaji();
        gajiLabel.setText("Gaji Harian (4 menit kerja) = " + gaji);

                choosePekerjaan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selected = pekerjaan.get(choosePekerjaan.getSelectedIndex());
                int gaji = selected.getGaji();
                gajiLabel.setText("Gaji Harian (4 menit kerja) = " + gaji);
            }
        });

        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        optionPanel.setOpaque(false);
        JButton okButton = new JButton("OK");
        okButton.setBackground(Color.WHITE);
        okButton.setForeground(Color.BLACK);
        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setBackground(Color.WHITE);
        cancelButton.setForeground(Color.BLACK);
        optionPanel.add(okButton);
        optionPanel.add(cancelButton);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sim.gantiPekerjaan(selected);
                    housePanel.centerPanel.remove(GantiKerjaPanel.this);
                } catch (Exception er) {
                    JOptionPane.showMessageDialog(null, er.getMessage(), "Penggantian Pekerjaan Gagal", JOptionPane.PLAIN_MESSAGE);
                }
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                housePanel.centerPanel.remove(GantiKerjaPanel.this);

            }
        });

        mainPanel.add(choosePekerjaanLabel);
        mainPanel.add(comboBoxWrap);
        mainPanel.add(gajiLabel);
        mainPanel.add(optionPanel);

        add(mainPanel);
        revalidate();
        repaint();
    }
    
}