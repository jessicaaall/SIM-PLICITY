package game;

import entity.Objek;
import entity.Perabotan;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class PerabotanLabel extends JLabel {
    HousePanel housePanel;
    RoomPanel roomPanel;
    public void setPut(boolean put) {
        this.put = put;
    }

    private boolean put = false;
    public Perabotan getPerabotan() {
        return perabotan;
    }

    public void setPerabotan(Perabotan perabotan) {
        this.perabotan = perabotan;
    }

    private Perabotan perabotan;

    private ImageIcon image;
    private Point startDragPoint;
    public PerabotanLabel(Perabotan perabotan, HousePanel housePanel, RoomPanel roomPanel){
        DragListener dragListener = new DragListener();
        this.perabotan = perabotan;
        this.housePanel = housePanel;
        this.roomPanel = roomPanel;
        int width = perabotan.getDimensi().width*PerabotanLabel.this.housePanel.unitSize;
        int height = perabotan.getDimensi().height*PerabotanLabel.this.housePanel.unitSize;
        Image imagenya = perabotan.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagenya);
        this.setIcon(image);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(perabotan.getDimensi().width*PerabotanLabel.this.housePanel.unitSize, perabotan.getDimensi().height*PerabotanLabel.this.housePanel.unitSize));
        this.setBounds(perabotan.getKiriAtas().x*PerabotanLabel.this.housePanel.unitSize
                , perabotan.getKiriAtas().y*PerabotanLabel.this.housePanel.unitSize
                , perabotan.getDimensi().width*PerabotanLabel.this.housePanel.unitSize
                , perabotan.getDimensi().height*PerabotanLabel.this.housePanel.unitSize);
        this.addMouseListener(dragListener);
        this.addMouseMotionListener(dragListener);
//        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK));
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d= (Graphics2D) g;
        float alpha = 1f;
        if (this.getMousePosition() != null && !this.getMousePosition().equals(startDragPoint)){
            alpha = 0.5f;
        }
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
        g2d.setComposite(alphaComposite);
        g2d.dispose();

    }
    Point clickedPoint;
    private class DragListener extends MouseInputAdapter{
        Point location;
        MouseEvent pressed;

        @Override
        public void mouseClicked(MouseEvent e) {
            PerabotanLabel clickedLabel = (PerabotanLabel) e.getSource();
            Perabotan clickedPerabot = clickedLabel.getPerabotan();
            System.out.println("Mengklik " + clickedPerabot.getNama());
            if (put) {
                boolean isOccupied = false;
                boolean isOutOfBoundary = false;
                PerabotanLabel.this.setBounds(((int) PerabotanLabel.this.getX() / housePanel.unitSize) * housePanel.unitSize,
                        ((int) PerabotanLabel.this.getY() / housePanel.unitSize) * housePanel.unitSize,
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());

                RoomPanel ruanganAcuan = null;
                for (Component component: housePanel.centerPanel.getComponents()){
                    if (component instanceof RoomPanel){
                        RoomPanel currentRoom = (RoomPanel) component;

                        //cek apakah sudah dioccupied
                        for (Objek objek : currentRoom.ruangan.getDaftarObjek()) {
                            if (objek instanceof Perabotan) {
                                Perabotan perabotan1 = (Perabotan) objek;
                                //cek untuk semua titik di labelnya
                                for (int i = PerabotanLabel.this.getX(); i < PerabotanLabel.this.getX() + PerabotanLabel.this.getWidth(); i += housePanel.unitSize) {
                                    for (int j = PerabotanLabel.this.getY(); j < PerabotanLabel.this.getY() + PerabotanLabel.this.getHeight(); j += housePanel.unitSize) {
                                        for (int k = (int) (perabotan1.getKiriAtas().getX() * housePanel.unitSize);
                                             k < perabotan1.getKiriAtas().getX() * housePanel.unitSize + perabotan1.getDimensi().getWidth() * housePanel.unitSize;
                                             k += housePanel.unitSize) {
                                            for (int l = (int) (perabotan1.getKiriAtas().getY() * housePanel.unitSize);
                                                 l < perabotan1.getKiriAtas().getY() * housePanel.unitSize + perabotan1.getDimensi().getHeight() * housePanel.unitSize;
                                                 l += housePanel.unitSize) {
                                                Point point1 = new Point(i, j);
                                                Point point2 = new Point(k, l);
                                                if (point1.equals(point2)) {
                                                    isOccupied = true;
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    //check boundary
                    // 1. cek semua ruangan di house panel, dan cari di mana letak perabotan label berada di ruangan mana
                    for (Component komponen : housePanel.centerPanel.getComponents()){
                        if (komponen instanceof RoomPanel){
                            RoomPanel targetPanel = (RoomPanel) komponen;
                            // cek apakah titik lokasi dari label berada di antara
                            if (
                                    PerabotanLabel.this.getX() >= targetPanel.getX() && PerabotanLabel.this.getX()
                                            <  targetPanel.getX() + targetPanel.getWidth()
                                            &&  PerabotanLabel.this.getY() >= targetPanel.getY() && PerabotanLabel.this.getY()
                                            <  targetPanel.getY() + targetPanel.getHeight()
                            ){
                                ruanganAcuan = targetPanel;
                            }
                        }
                    }

                    if (ruanganAcuan == null){
                        isOutOfBoundary = true;

                    }
                    else{
                        if (PerabotanLabel.this.getX() + PerabotanLabel.this.getWidth() > ruanganAcuan.getX() + ruanganAcuan.getWidth() ||
                                PerabotanLabel.this.getY() + PerabotanLabel.this.getHeight() > ruanganAcuan.getY() + ruanganAcuan.getHeight()) {
                            isOutOfBoundary = true;
                        }
                    }
                }
                if (isOccupied) {
                    System.out.println("Tempat sudah dipakai");
                    //dimasukin lagi ke inventory
                    PerabotanLabel.this.perabotan.setKiriAtas(null);
                    housePanel.centerPanel.remove(PerabotanLabel.this);

                } else if (isOutOfBoundary) {
                    System.out.println("Di luar batas");
                    PerabotanLabel.this.perabotan.setKiriAtas(null);
                    housePanel.centerPanel.remove(PerabotanLabel.this);

                } else {
                    PerabotanLabel.this.getPerabotan().setKiriAtas(new Point(PerabotanLabel.this.getX() / housePanel.unitSize,
                            PerabotanLabel.this.getY() / housePanel.unitSize));
                    PerabotanLabel.this.roomPanel = ruanganAcuan;
                    housePanel.inventoryPanel.inventorySlot.removeItem(PerabotanLabel.this.getPerabotan());
                    housePanel.centerPanel.remove(housePanel.inventoryPanel);
                }
                put = false;
                housePanel.centerPanel.revalidate();
                housePanel.centerPanel.repaint();
                housePanel.revalidate();
                housePanel.repaint();
            }
            else {
                /* Masukkan panel untuk memasukkan ke inventory*/
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if (put){
                return;
            }
            pressed = e;
            startDragPoint = new Point(perabotan.getKiriAtas().x*housePanel.unitSize,
                    perabotan.getKiriAtas().y*roomPanel.unitSize);

        }



        @Override
        public void mouseMoved(MouseEvent e) {
            if (put){
                //item follow the mouse
                Component component = e.getComponent();
                Point p = e.getPoint();
                location = component.getLocation();
                location.translate(-clickedPoint.x+e.getX()-component.getWidth()/2
                        , -clickedPoint.y+e.getY()-component.getHeight()/2);
                component.setLocation(location);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (put){
                return;
            }
            boolean isOccupied = false;
            boolean isOutOfBoundary = false;
            PerabotanLabel.this.setBounds(((int)PerabotanLabel.this.getX()/housePanel.unitSize)*housePanel.unitSize,
                    ((int)PerabotanLabel.this.getY()/housePanel.unitSize)*housePanel.unitSize,
                    PerabotanLabel.this.getWidth(),
                    PerabotanLabel.this.getHeight());
            RoomPanel ruanganAcuan = null;
            //cek availability
            for (Component component: housePanel.centerPanel.getComponents()){
                if (component instanceof RoomPanel){
                    RoomPanel currentRoom = (RoomPanel) component;

                    //cek apakah sudah dioccupied
                    for (Objek objek : currentRoom.ruangan.getDaftarObjek()) {
                        if (objek instanceof Perabotan) {
                            Perabotan perabotan1 = (Perabotan) objek;
                            if (PerabotanLabel.this.getPerabotan().equals(perabotan1) && PerabotanLabel.this.getPerabotan().getKiriAtas().equals(perabotan1.getKiriAtas())) {
                                continue;
                            }
                            //cek untuk semua titik di labelnya
                            for (int i = PerabotanLabel.this.getX(); i < PerabotanLabel.this.getX() + PerabotanLabel.this.getWidth(); i += housePanel.unitSize) {
                                for (int j = PerabotanLabel.this.getY(); j < PerabotanLabel.this.getY() + PerabotanLabel.this.getHeight(); j += housePanel.unitSize) {
                                    for (int k = (int) (perabotan1.getKiriAtas().getX() * housePanel.unitSize);
                                         k < perabotan1.getKiriAtas().getX() * housePanel.unitSize + perabotan1.getDimensi().getWidth() * housePanel.unitSize;
                                         k += housePanel.unitSize) {
                                        for (int l = (int) (perabotan1.getKiriAtas().getY() * housePanel.unitSize);
                                             l < perabotan1.getKiriAtas().getY() * housePanel.unitSize + perabotan1.getDimensi().getHeight() * housePanel.unitSize;
                                             l += housePanel.unitSize) {
                                            Point point1 = new Point(i, j);
                                            Point point2 = new Point(k, l);
                                            if (point1.equals(point2)) {
                                                isOccupied = true;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                //check boundary
                // 1. cek semua ruangan di house panel, dan cari di mana letak perabotan label berada di ruangan mana
                for (Component komponen : housePanel.centerPanel.getComponents()){
                    if (komponen instanceof RoomPanel){
                        RoomPanel targetPanel = (RoomPanel) komponen;
                        // cek apakah titik lokasi dari label berada di antara
                        if (
                                PerabotanLabel.this.getX() >= targetPanel.getX() && PerabotanLabel.this.getX()
                                        <  targetPanel.getX() + targetPanel.getWidth()
                                        &&  PerabotanLabel.this.getY() >= targetPanel.getY() && PerabotanLabel.this.getY()
                                        <  targetPanel.getY() + targetPanel.getHeight()
                        ){
                            ruanganAcuan = targetPanel;
                        }
                    }
                }

                //cek apakaha ada, kalau tidak ada, maka nanti ruangan acuan akan bernilai null
                if (ruanganAcuan == null){
                    isOutOfBoundary = true;

                }
                else{
                    // cek apakah dia tembok ruangan
                    if (PerabotanLabel.this.getX() + PerabotanLabel.this.getWidth() > ruanganAcuan.getX() + ruanganAcuan.getWidth() ||
                            PerabotanLabel.this.getY() + PerabotanLabel.this.getHeight() > ruanganAcuan.getY() + ruanganAcuan.getHeight()) {
                        isOutOfBoundary = true;
                    }
                }
            }
            if (isOccupied){
                System.out.println("Tempat sudah dipakai");
                PerabotanLabel.this.setBounds((int) startDragPoint.getX()/housePanel.unitSize*housePanel.unitSize,
                        (int) startDragPoint.getY()/housePanel.unitSize*housePanel.unitSize,
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());
            }
            else if (isOutOfBoundary){
                System.out.println("Di luar batas");
                PerabotanLabel.this.setBounds((int) startDragPoint.getX()/housePanel.unitSize*housePanel.unitSize,
                        (int) startDragPoint.getY()/housePanel.unitSize*housePanel.unitSize,
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());
            }
            else {
                PerabotanLabel.this.getPerabotan().setKiriAtas(new Point(PerabotanLabel.this.getX()/housePanel.unitSize,
                        PerabotanLabel.this.getY()/housePanel.unitSize));
                PerabotanLabel.this.roomPanel = ruanganAcuan;
            }
            startDragPoint = null;
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (put){
                return;
            }
            Component component = e.getComponent();
            location = component.getLocation();
            int dx = location.x - pressed.getX() + e.getX();
            int dy =  location.y - pressed.getY() + e.getY();
            component.setLocation(dx, dy);
            repaint();
        }
    }


}
