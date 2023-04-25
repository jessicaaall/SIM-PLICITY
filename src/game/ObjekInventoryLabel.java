package game;

import entity.Objek;
import entity.Perabotan;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Map;

public class ObjekInventoryLabel extends JLabel {
    public Map.Entry<Objek, Integer> getObjek() {
        return objek;
    }
    ObjekInventoryLabel label;

    private Map.Entry<Objek, Integer> objek;
    public InventorySlotPanel ip;
    ObjekInventoryLabel(Map.Entry<Objek, Integer> objek, InventorySlotPanel ip){
        super();
        this.objek = objek;
        this.ip = ip;
        this.setFont(new Font("Comic Sans MS", Font.ITALIC, 10));
//        this.setBorder(new LineBorder(Color.lightGray, 2, true));
        this.setOpaque(true);
        this.setFocusable(false);
        this.setOpaque(true);
        MouseListener ml = new MouseListener();
        this.addMouseListener(ml);
        repaint();

    }

    private class MouseListener extends MouseInputAdapter{

        @Override
        public void mouseClicked(MouseEvent e) {
            //akan men-summon panel baru relatif dari objek inventory
            System.out.println("objek clicked");
            TaruhBarangPanel taruhBarangPanel = new TaruhBarangPanel(new FlowLayout(FlowLayout.CENTER));
            taruhBarangPanel.setPreferredSize(new Dimension(2*ip.hp.unitSize, 2*ip.hp.unitSize));
            taruhBarangPanel.setSize(new Dimension(2*ip.hp.unitSize, 2*ip.hp.unitSize));
            JButton tombolTaruh = new JButton("Taruh");
            JButton tombolBatal = new JButton("Batal");
            tombolTaruh.setFocusable(false);
            tombolTaruh.setBackground(Color.white);
            tombolTaruh.setForeground(new Color(51, 102, 0));
            tombolTaruh.setBounds(0,0, 2*ip.hp.unitSize, ip.hp.unitSize);
            tombolBatal.setBounds(0,ip.hp.unitSize, 2*ip.hp.unitSize, ip.hp.unitSize);
            taruhBarangPanel.setBounds((int)e.getX(), (int)e.getY(), taruhBarangPanel.getPreferredSize().width, taruhBarangPanel.getPreferredSize().height);
            taruhBarangPanel.setBackground(Color.red);
            tombolTaruh.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //menu inventory akan otomatis tertutup dan akan menjadi draggable panel
                    ip.ip.remove(taruhBarangPanel);
                    ip.ip.setVisible(false);
                    if (objek instanceof Perabotan){
                        PerabotanLabel newPerabot = new PerabotanLabel((Perabotan) objek.getKey(), ip.hp, null);
                    }


                }
            });
            if (ObjekInventoryLabel.this.objek.getKey() instanceof Perabotan){
                tombolTaruh.setEnabled(true);
            }
            else{
                tombolTaruh.setEnabled(false);
            }
            tombolBatal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    ip.ip.remove(taruhBarangPanel);
                }
            });
            taruhBarangPanel.add(tombolTaruh);
            taruhBarangPanel.add(tombolBatal);
            //cek apakah sudah ada di dalam Inventory Panel
            boolean taruhBarangPanelExist = false;
            for (Component component : ip.ip.getComponents()){
                if (component instanceof TaruhBarangPanel){
                    taruhBarangPanelExist = true;
                }
            }
            if (!taruhBarangPanelExist){
                ip.ip.add(taruhBarangPanel, 0);
                ip.ip.setComponentZOrder(taruhBarangPanel, 0);
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            label = (ObjekInventoryLabel) e.getComponent();
            if(label.getObjek() != null){
                label.setToolTipText(label.getObjek().getKey().getNama());
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setToolTipText(null);
            label = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(objek.getKey().getImage(), 2,2, getWidth()-4, getHeight()-4, null);
        g2d.setColor(Color.lightGray);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(0,0, getWidth(), getHeight(), 10, 10);
        g2d.setFont(new Font("Comic Sans MS", Font.BOLD, 10));
        g2d.setColor(Color.black);
        g2d.drawString(objek.getValue()+"x", 3*getWidth()/4, 3*getHeight()/4 + getHeight()/8);
        g2d.dispose();
    }

    private class TaruhBarangPanel extends JPanel{
        public TaruhBarangPanel(LayoutManager mgr){
            super(mgr);
        }
    }
}
