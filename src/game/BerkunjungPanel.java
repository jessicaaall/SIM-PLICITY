package game;

import entity.Objek;
import entity.Sim;
import thread.ThreadAksi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
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
                    Sim simnya = housePanel.selectedSim.sim;
                    int waktu = (int)Math.sqrt(Math.pow((visitedSim.getKepemilikanRumah().getLokasi().getX() - simnya.getKepemilikanRumah().getLokasi().getX()), 2) + Math.pow((visitedSim.getKepemilikanRumah().getLokasi().getY() - simnya.getKepemilikanRumah().getLokasi().getY()), 2));
                    ThreadAksi threadAksi = new ThreadAksi(simnya.getNamaLengkap() + " berkunjung", waktu, housePanel.rumah.world);
                    housePanel.rumah.world.setThreadAksi(threadAksi);
                    TimerAksiPanel timerAksiPanel = new TimerAksiPanel(housePanel, "Berkunjung", threadAksi);
                    housePanel.centerPanel.add(timerAksiPanel, 0);
                    simnya.berkunjung(visitedSim);
                    threadAksi.startThread();
                    timerAksiPanel.startThread();
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

    private class SimTableCellRenderer implements TableCellRenderer{

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Sim sim = null;
            Point point = null;
            JLabel label = null;

            if (value instanceof Sim){
                sim = (Sim) value;
                label = new JLabel(sim.getNamaLengkap());
            }
            else if (value instanceof Point){
                point = (Point) value;
                label = new JLabel(String.format("(%d, %d)", point.x, point.y));
            }
            if (isSelected){
                label.setBackground(table.getSelectionBackground());
                label.setForeground(table.getSelectionForeground());
            }
            else{
                label.setBackground(table.getBackground());
                label.setBackground(table.getForeground());
            }
            return label;
        }
    }

    private class DaftarSimPanel extends JScrollPane{
        SimTableModel model;
        JTable table;
        DaftarSimPanel(){
            model = new SimTableModel(housePanel.rumah.world.getDaftarSim());
            table = new JTable(model);
            table.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting()){
                        int row = table.getSelectedRow();
                        if (row != -1){
                            visitedSim = (Sim) table.getValueAt(row, 0);
                        }
                    }
                }
            });
            table.getColumnModel().getColumn(0).setCellRenderer(new SimTableCellRenderer());
            table.getColumnModel().getColumn(1).setCellRenderer(new SimTableCellRenderer());
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
                    return sim;
                case 1:
                    return sim.getKepemilikanRumah().getLokasi();
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
