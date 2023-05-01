package game;

import entity.Waktu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaktuPanel extends JPanel implements Runnable {
    Waktu waktu;
    HousePanel housePanel;
    JPanel northPanel;
    DaftarThreadPane southPanel;
    JPanel centerPanel;
    Thread updateThread;
    WaktuPanel(Waktu waktu, HousePanel housePanel){
        super(new BorderLayout());
        this.waktu = waktu;
        this.housePanel = housePanel;
        northPanel = new JPanel(new GridLayout(0,1));
        southPanel = new DaftarThreadPane(waktu.getWorld(), housePanel.selectedSim.sim);
        centerPanel = new JPanel(new FlowLayout());
        setBackground(new Color(100, 200, 150));
        //set size
        setPreferredSize(new Dimension(320, 480));
        northPanel.setPreferredSize(new Dimension(320, 80));
        southPanel.setPreferredSize(new Dimension(320, 360));
        centerPanel.setPreferredSize(new Dimension(320, 40));
        JButton closeButton = new JButton("close");
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                housePanel.centerPanel.remove(WaktuPanel.this);
                housePanel.centerPanel.revalidate();
                housePanel.centerPanel.repaint();
            }
        });
        centerPanel.add(closeButton);
        JLabel timeLabel = new JLabel("<html> "+ waktu.tampilkanWaktu()[0] +"<br>" +
                waktu.tampilkanWaktu()[1] + "<br>" +
                waktu.tampilkanWaktu()[2] + "</html>");
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        northPanel.add(timeLabel);
        add(northPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        revalidate();
        repaint();
        startThread();
    }

    @Override
    public void run() {
        while (updateThread != null){
            southPanel.update();
            revalidate();
            repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void startThread(){
        updateThread= new Thread(this);
        updateThread.start();
    }
}
