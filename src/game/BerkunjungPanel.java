package game;

import entity.Objek;
import entity.Sim;
import thread.ThreadAksi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BerkunjungPanel extends JPanel {
    Sim visitedSim;
    HousePanel housePanel;

    DaftarSimPanel daftarSimPanel;
    BerkunjungPanel(HousePanel housePanel){
        this.housePanel = housePanel;
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(housePanel.centerPanel.getWidth(), housePanel.centerPanel.getHeight()));
        setBounds(0,0, housePanel.centerPanel.getWidth(), housePanel.centerPanel.getHeight());
        setBorder(new LineBorder(Color.BLACK, 5, true));
        setBackground(new Color(150,178,102));
        setDoubleBuffered(true);
        daftarSimPanel = new DaftarSimPanel();
        JPanel OKCancelPanel = new JPanel(new GridLayout(1, 0));
        JButton OKButton = new JButton("Kunjungi");
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        JButton cancelButton = new JButton("Batal");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.centerPanel.remove(BerkunjungPanel.this);
            }
        });
        OKButton.setFocusable(false);
        cancelButton.setFocusable(false);
        OKCancelPanel.add(OKButton);
        OKCancelPanel.add(cancelButton);
        OKCancelPanel.setPreferredSize(new Dimension(640, 80));
        add(daftarSimPanel, BorderLayout.CENTER);
        add(OKCancelPanel, BorderLayout.SOUTH);

    }

    private class DaftarSimPanel extends JScrollPane{
        SimTableModel model;
        JTable table;
        DaftarSimPanel(){
            model = new SimTableModel(housePanel.rumah.world.getDaftarSim());
            table = new JTable(model);
            setViewportView(table);
        }

    }

    private class SimTableModel extends AbstractTableModel{
        public static final int NUM_OF_COLUMNS = 2;
        public static final String[] COLUMNS_NAME = {"Nama", "Posisi Rumah"};

        public List<Sim> sims;
        public SimTableModel(List<Sim> sims){
            this.sims = sims;
        }

        @Override
        public int getRowCount() {
            return sims.size();
        }

        @Override
        public int getColumnCount() {
            return NUM_OF_COLUMNS;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Sim sim = sims.get(rowIndex);
            switch (columnIndex){
                case 0:
                    return sim.getNamaLengkap();
                case 1:
                    return "(" + sim.getKepemilikanRumah().getLokasi().x + ", " + sim.getKepemilikanRumah().getLokasi().y+ ")";
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS_NAME[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            switch (columnIndex){
                case 0:
                    return Sim.class;
                case 1:
                    return Point.class;
                default:
                    throw new IllegalArgumentException("Index out of range");
            }
        }
    }
}
