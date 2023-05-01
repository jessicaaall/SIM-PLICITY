package game;

import entity.*;
import thread.ThreadAksi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class TimerAksiPanel extends JPanel implements MouseListener, MouseMotionListener, Runnable {
    MouseEvent pressed;
    Point location;

    Thread thread;
    ThreadAksi threadAksi;
    HousePanel hp;
    JLabel timer;

    TimerAksiPanel(HousePanel hp, String aksi, ThreadAksi threadAksi){
        super(new BorderLayout());
        this.hp = hp;
        this.threadAksi = threadAksi;
        setLocation(hp.centerPanel.getWidth()/6-18, hp.centerPanel.getHeight()/3+30);
        setBounds(hp.centerPanel.getWidth()/6-18, hp.centerPanel.getHeight()/3+30, 480, 150);
        setBackground(new Color(255, 255, 255, 200));
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK, 3, true));
        setFocusable(false);

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255, 200));
        mainPanel.setOpaque(false);
        mainPanel.setFocusable(false);

        GridLayout layout = new GridLayout(2, 1);
        layout.setVgap(5);
        mainPanel.setLayout(layout);

        JLabel aksiTitle = new JLabel("Sim sedang " + aksi + " ...");
        aksiTitle.setFocusable(false);
        aksiTitle.setVerticalAlignment(SwingConstants.CENTER);
        aksiTitle.setHorizontalAlignment(SwingConstants.CENTER);
        aksiTitle.setFont(new Font("Monospaced", Font.BOLD, 20));
      
        timer = new JLabel(String.valueOf(100));
        timer.setFocusable(false);
        timer.setVerticalAlignment(SwingConstants.CENTER);
        timer.setHorizontalAlignment(SwingConstants.CENTER);
        timer.setFont(new Font("Monospaced", Font.BOLD, 20));

        mainPanel.add(aksiTitle);
        mainPanel.add(timer);

        add(mainPanel, BorderLayout.CENTER);
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
    }

    @Override
    public void run() {
        hp.isAction = true;
        hp.disabledAllButton();
        hp.rumah.world.isActive = true;
        while (hp.rumah.world.getThreadAksi() != null){
            timer.setText(String.valueOf(threadAksi.getSisaWaktu()));
            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        hp.rumah.world.isActive = false;
        hp.centerPanel.remove(this);
        hp.enabledAllButton();
        hp.isAction = false;

    }

    public void startThread(){
        Thread thread1 = new Thread(this);
        thread1.start();
    }

}
