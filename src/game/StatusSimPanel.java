package game;

import entity.Sim;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class StatusSimPanel extends JPanel implements MouseListener, MouseMotionListener {
    Sim sim;
    public HousePanel housePanel;
    MouseEvent pressed;
    Point location;

    JLabel kekenyanganLabel;
    JLabel moodLabel;
    JLabel kesehatanLabel;
    JLabel uangLabel;
    JLabel pekerjaanLabel;
    StatusSimPanel(Sim sim, HousePanel housePanel){
        super(new BorderLayout());
        this.sim = sim;
        this.housePanel = housePanel;
        setLocation(new Point(0,0));
        setPreferredSize(new Dimension(640, 320));
        setBackground(new Color(150, 178, 102));
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK, 5, true));

        JPanel infoPanel = new JPanel(new GridLayout(2, 2, 0, 10));
        infoPanel.setBackground(new Color(150, 178, 102));
        JPanel statusPanel = new JPanel(new GridLayout(0, 1));
        statusPanel.setBackground(new Color(150, 178, 102));
        JLabel namaSim = new JLabel(sim.getNamaLengkap());
        namaSim.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        JLabel fotoSim = new JLabel(new ImageIcon(sim.getSimImage().getScaledInstance(40, 80, Image.SCALE_FAST)));
        kekenyanganLabel = new JLabel("Kekenyangan: "+sim.getKekenyangan());
        moodLabel = new JLabel("Mood: " + sim.getMood());
        kesehatanLabel = new JLabel("Kesehatan: " + sim.getKesehatan());
        uangLabel = new JLabel("Uang: " + sim.getUang());
        pekerjaanLabel = new JLabel("Pekerjaan: " + sim.getPekerjaan().getNamaPekerjaan());
        infoPanel.add(fotoSim);
        infoPanel.add(namaSim);
        infoPanel.add(pekerjaanLabel);
        statusPanel.add(kekenyanganLabel);
        statusPanel.add(moodLabel);
        statusPanel.add(kesehatanLabel);
        statusPanel.add(uangLabel);

        JButton button = new JButton("Close");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        infoPanel.add(button);
        add(statusPanel, BorderLayout.WEST);
        add(infoPanel, BorderLayout.CENTER);
        setBounds(0,0, 640, 320);
        addMouseListener(this);
        addMouseMotionListener(this);
        revalidate();
        repaint();
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = e;
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (pressed == null) {
            System.out.println("null");
            return;
        }
        Component component = e.getComponent();
        location = component.getLocation();
        int dx = (int) (location.x - pressed.getX() + e.getX());
        int dy = (int) (location.y - pressed.getY() + e.getY());
        component.setLocation(dx, dy);
        component.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public void update(){
        if (kekenyanganLabel == null || moodLabel == null || kesehatanLabel == null || pekerjaanLabel == null || uangLabel == null){
            return;
        }
        kekenyanganLabel.setText("Kekenyangan: "+sim.getKekenyangan());
        moodLabel.setText("Mood: " + sim.getMood());
        kesehatanLabel.setText("Kesehatan: " + sim.getKesehatan());
        pekerjaanLabel.setText("Uang: " + sim.getUang());
        uangLabel.setText("Uang: " + sim.getUang());
    }
}
