package game;

import entity.Objek;
import entity.Perabotan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PerabotanLabel extends JLabel {
    RoomPanel roomPanel;
    public Perabotan getPerabotan() {
        return perabotan;
    }

    public void setPerabotan(Perabotan perabotan) {
        this.perabotan = perabotan;
    }

    private Perabotan perabotan;

    private ImageIcon image;
    private Point imageCorner;
    private Point prevPoint;
    private Point startDragPoint;
    private Point titikAwal;
    public PerabotanLabel(Perabotan perabotan, RoomPanel roomPanel){
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.perabotan = perabotan;
        this.roomPanel = roomPanel;
        int width = perabotan.getDimensi().width*roomPanel.unitSize;
        int height = perabotan.getDimensi().height*roomPanel.unitSize;
        Image imagenya = perabotan.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        image = new ImageIcon(imagenya);
        this.setIcon(image);
        this.setOpaque(false);
        this.setPreferredSize(new Dimension(perabotan.getDimensi().width*roomPanel.unitSize, perabotan.getDimensi().height*roomPanel.unitSize));
        this.setBounds(perabotan.getKiriAtas().x*roomPanel.unitSize
                , perabotan.getKiriAtas().y*roomPanel.unitSize
                , perabotan.getDimensi().width*roomPanel.unitSize
                , perabotan.getDimensi().height*roomPanel.unitSize);
        imageCorner = new Point(perabotan.getKiriAtas().x*roomPanel.unitSize, perabotan.getKiriAtas().y*roomPanel.unitSize);
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
//        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK));
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2d= (Graphics2D) g;
        float alpha = 1f;
        if (this.getMousePosition() != null && !this.getMousePosition().equals(prevPoint)){
            alpha = 0.5f;
        }
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,alpha);
        g2d.setComposite(alphaComposite);
        image.paintIcon(this, g2d, (int) imageCorner.getX(), (int)imageCorner.getY());
        g2d.dispose();

    }

    private class ClickListener extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
//            System.out.println("You pressed the label");
            PerabotanLabel pl = (PerabotanLabel) e.getSource();
            prevPoint = e.getPoint();
            startDragPoint = new Point(perabotan.getKiriAtas().x*roomPanel.unitSize,
                    perabotan.getKiriAtas().y*roomPanel.unitSize);
            titikAwal = new Point(pl.getX(), pl.getY());

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            boolean isOccupied = false;
            boolean isOutOfBoundary = false;
            PerabotanLabel.this.setBounds(((int)PerabotanLabel.this.getX()/roomPanel.unitSize)*roomPanel.unitSize,
                    ((int)PerabotanLabel.this.getY()/roomPanel.unitSize)*roomPanel.unitSize,
                    PerabotanLabel.this.getWidth(),
                    PerabotanLabel.this.getHeight());
            //cek apakah sudah dioccupied
            for (Objek objek: roomPanel.ruangan.getDaftarObjek()){
                if (objek instanceof Perabotan){
                    Perabotan perabotan1 = (Perabotan) objek;
                    if (PerabotanLabel.this.getPerabotan().equals(perabotan1) && PerabotanLabel.this.getPerabotan().getKiriAtas().equals(perabotan1.getKiriAtas())){
                        continue;
                    }
                    //cek untuk semua titik di labelnya
                    for (int i = PerabotanLabel.this.getX(); i < PerabotanLabel.this.getX() + PerabotanLabel.this.getWidth(); i += roomPanel.unitSize){
                        for (int j = PerabotanLabel.this.getY(); j < PerabotanLabel.this.getY() + PerabotanLabel.this.getHeight(); j += roomPanel.unitSize){
                            for (int k = (int) (perabotan1.getKiriAtas().getX()*roomPanel.unitSize);
                                 k < perabotan1.getKiriAtas().getX()*roomPanel.unitSize + perabotan1.getDimensi().getWidth()*roomPanel.unitSize;
                                 k += roomPanel.unitSize){
                                for (int l = (int) (perabotan1.getKiriAtas().getY()*roomPanel.unitSize);
                                     l < perabotan1.getKiriAtas().getY()*roomPanel.unitSize + perabotan1.getDimensi().getHeight()*roomPanel.unitSize;
                                     l += roomPanel.unitSize){
                                    Point point1 = new Point(i, j);
                                    Point point2 = new Point(k, l);
                                    if (point1.equals(point2)){
                                        isOccupied = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
/*                    boolean occupied = PerabotanLabel.this.getX() >= ((Perabotan) objek).getKiriAtas().getX()*roomPanel.unitSize &&
                            PerabotanLabel.this.getX() < ((Perabotan) objek).getKiriAtas().getX()*roomPanel.unitSize + perabotan1.getDimensi().getWidth() &&
                            PerabotanLabel.this.getY() >= ((Perabotan) objek).getKiriAtas().getY()*roomPanel.unitSize &&
                            PerabotanLabel.this.getY() < ((Perabotan) objek).getKiriAtas().getY()*roomPanel.unitSize + perabotan1.getDimensi().getHeight();
                    if (occupied) {
                        isOccupied = true;
                    }*/

                    //check boundary
                    if (PerabotanLabel.this.getX()+PerabotanLabel.this.getWidth() > roomPanel.getWidth() ||
                            PerabotanLabel.this.getY()+PerabotanLabel.this.getHeight() > roomPanel.getHeight()||
                            PerabotanLabel.this.getX() < 0 || PerabotanLabel.this.getY() < 0) {
                        isOutOfBoundary = true;
                    }
                }
            }
            if (isOccupied){
                System.out.println("Tempat sudah dipakai");
                PerabotanLabel.this.setBounds((int) startDragPoint.getX()/roomPanel.unitSize*roomPanel.unitSize,
                        (int) startDragPoint.getY()/roomPanel.unitSize*roomPanel.unitSize,
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());
            }
            else if (isOutOfBoundary){
                System.out.println("Di luar batas");
                PerabotanLabel.this.setBounds((int) startDragPoint.getX()/roomPanel.unitSize*roomPanel.unitSize,
                        (int) startDragPoint.getY()/roomPanel.unitSize*roomPanel.unitSize,
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());
            }
            else {
                PerabotanLabel.this.getPerabotan().setKiriAtas(new Point(PerabotanLabel.this.getX()/roomPanel.unitSize,
                        PerabotanLabel.this.getY()/roomPanel.unitSize));
            }
//            System.out.println("Mouse Released");
            startDragPoint = null;
            repaint();
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            PerabotanLabel clickedLabel = (PerabotanLabel) e.getSource();
            Perabotan clickedPerabot = clickedLabel.getPerabotan();
            System.out.println("Mengklik " + clickedPerabot.getNama());
        }
    }
    private class DragListener extends MouseMotionAdapter{

        @Override
        public void mouseDragged(MouseEvent e) {
//            System.out.println("You dragged the label");
            PerabotanLabel.this.setBackground(new Color(255,255,255, 128));
            Point currentPoint = e.getPoint();
/*            if (currentPoint.getX() > roomPanel.getWidth()){
                currentPoint.x = roomPanel.getWidth()-1;
            }
            if (currentPoint.getX() < 0){
                currentPoint.x = 0;
            }
            if (currentPoint.getY() > roomPanel.getHeight()){
                currentPoint.y = roomPanel.getHeight();
            }
            if (currentPoint.getY() < 0){
                currentPoint.y = 0;
            }*/
            double dx = currentPoint.getX() - prevPoint.getX();
            double dy =  (currentPoint.getY()- prevPoint.getY());

/*            imageCorner.translate(
                    (int)(currentPoint.getX()-prevPoint.getX()),
                    (int)(currentPoint.getY()-prevPoint.getY())
            );*/
            prevPoint = currentPoint;
            PerabotanLabel.this.setBounds(titikAwal.x+(int)dx, titikAwal.y+(int)dy, PerabotanLabel.this.getWidth(),
                    PerabotanLabel.this.getHeight());
            titikAwal = new Point(PerabotanLabel.this.getX(), PerabotanLabel.this.getY());
//            PerabotanLabel.this.getPerabotan().setKiriAtas();
/*            try {
                Thread.sleep(1000/60);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }*/
            repaint();
        }
    }


}
