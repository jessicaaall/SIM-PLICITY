package game;

import entity.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

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
    private boolean isDragging = false;

    private ImageIcon image;
    public Point startDragPoint;
    public PerabotanLabel(Perabotan perabotan, HousePanel housePanel, RoomPanel roomPanel){
        DragListener dragListener = new DragListener();
        this.perabotan = perabotan;
        this.housePanel = housePanel;
        this.roomPanel = roomPanel;
        int width = perabotan.getDimensi().width*PerabotanLabel.this.housePanel.unitSize;
        int height = perabotan.getDimensi().height*PerabotanLabel.this.housePanel.unitSize;
        Image imagenya = generateImage(perabotan.getNama()).getScaledInstance(width, height, Image.SCALE_DEFAULT);
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
                PerabotanLabel.this.setBounds((Math.floorDiv(PerabotanLabel.this.getX()-housePanel.ruanganAcuanPanel.getX(),housePanel.unitSize))*housePanel.unitSize+housePanel.ruanganAcuanPanel.getX(),
                        (Math.floorDiv(PerabotanLabel.this.getY()-housePanel.ruanganAcuanPanel.getY(),housePanel.unitSize))*housePanel.unitSize + housePanel.ruanganAcuanPanel.getY(),
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
                                        for (int k = (int) (perabotan1.getKiriAtas().getX() * housePanel.unitSize + housePanel.ruanganAcuanPanel.getX());
                                             k < housePanel.ruanganAcuanPanel.getX() + perabotan1.getKiriAtas().getX() * housePanel.unitSize + perabotan1.getDimensi().getWidth() * housePanel.unitSize;
                                             k += housePanel.unitSize) {
                                            for (int l = (int) (housePanel.ruanganAcuanPanel.getY() + perabotan1.getKiriAtas().getY() * housePanel.unitSize);
                                                 l < housePanel.ruanganAcuanPanel.getY() + perabotan1.getKiriAtas().getY() * housePanel.unitSize + perabotan1.getDimensi().getHeight() * housePanel.unitSize;
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
                    PerabotanLabel.this.getPerabotan().setKiriAtas(new Point((PerabotanLabel.this.getX()- housePanel.ruanganAcuanPanel.getX())/housePanel.unitSize ,
                            (PerabotanLabel.this.getY()- housePanel.ruanganAcuanPanel.getY())/housePanel.unitSize));
                    PerabotanLabel.this.roomPanel = ruanganAcuan;
                    PerabotanLabel.this.perabotan.setRuangan(ruanganAcuan.ruangan);
                    ruanganAcuan.ruangan.getDaftarObjek().add(PerabotanLabel.this.perabotan);
                    housePanel.inventoryPanel.inventorySlot.removeItem(PerabotanLabel.this.getPerabotan());
                    housePanel.centerPanel.remove(housePanel.inventoryPanel);
                }
                put = false;
                housePanel.centerPanel.revalidate();
                housePanel.centerPanel.repaint();
                //timpa lagi sim di atas ketika ada sesuatu
                for (Component component : housePanel.centerPanel.getComponents()){
                    if (component instanceof SimLabel){
                        SimLabel simLabel = (SimLabel) component;
                        housePanel.centerPanel.remove(component);
                        housePanel.centerPanel.add(simLabel, 0);
                    }
                }
                housePanel.revalidate();
                housePanel.repaint();
            }
            else if (housePanel.isGoToObject){
                //cek item nya itu bener perabot gak
                System.out.println("mau teleport ke sini");
                PerabotanLabel clickedComp = (PerabotanLabel) e.getSource();
                if (!clickedComp.roomPanel.ruangan.getPosisi().equals(housePanel.selectedSim.sim.getLocRuang().getPosisi())) {
                    System.out.println("di luar ruangan");
                    return;
                }
                housePanel.selectedSim.setLocation(clickedComp.getX(), clickedComp.getY());
                housePanel.selectedSim.sim.setPosisi(new Point(
                        Math.floorDiv(clickedComp.getX() - housePanel.ruanganAcuanPanel.getX(), housePanel.unitSize),
                        Math.floorDiv(clickedComp.getY() - housePanel.ruanganAcuanPanel.getY(), housePanel.unitSize)
                ));
                housePanel.isGoToObject = false;
                for (Component component : housePanel.westPanel.getComponents()){
                    if (component instanceof JButton jb){
                        if (jb.getText().equals(housePanel.goToObjectButton.getText())){
                            continue;
                        }
                        if (jb.isEnabled()){
                            continue;
                        }
                        jb.setEnabled(true);
                    }
                }
                /*
                String aksi;
                if (perabotan instanceof Kasur){
                    aksi = "tidur";
                }
                else if (perabotan instanceof Kompor kompor){
                    aksi = "masak";
                    MasakPanel masakPanel = new MasakPanel(housePanel, kompor);
                    housePanel.centerPanel.add(masakPanel, 0);
                    housePanel.centerPanel.revalidate();
                    housePanel.centerPanel.repaint();
                    return;
                }
                else if (perabotan instanceof Toilet){
                    aksi = "modol";
                }
                else if (perabotan instanceof MejaDanKursi mejaDanKursi){
                    aksi = "makan";
                    MakanPanel makanPanel = new MakanPanel(housePanel, mejaDanKursi);
                    makanPanel.setBounds(160, 80, 320, 320);
                    housePanel.centerPanel.add(makanPanel, 0);
                    housePanel.centerPanel.revalidate();
                    housePanel.centerPanel.repaint();
                    return;
                }
                else if (perabotan instanceof Jam jam){
                    aksi = "melihat waktu";
                    WaktuPanel waktuPanel = new WaktuPanel(jam.getWaktu(), housePanel);
                    waktuPanel.setBounds(160, 80, 320, 480);
                    housePanel.centerPanel.add(waktuPanel, 0);
                    housePanel.centerPanel.revalidate();
                    housePanel.centerPanel.repaint();
                    return;
                }
                else{
                    aksi = perabotan.getNama();
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(housePanel, perabotan, aksi);
                housePanel.centerPanel.add(aksiAktifPanel, 0);
                housePanel.centerPanel.revalidate();
                housePanel.centerPanel.repaint();*/
            }
            else {
                if (isDragging){
                    return;
                }
                // untuk metode aksi
                if (housePanel.selectedSim != null){
                    JPanel container = new JPanel(new FlowLayout());
                    container.setBounds(e.getX(), e.getY(), 3*housePanel.unitSize, 2*housePanel.unitSize);
                    if (PerabotanLabel.this.perabotan instanceof Kasur){
                        JButton tidurButton = new JButton("Tidur");
                        tidurButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                Kasur kasur = (Kasur) PerabotanLabel.this.perabotan;
                            }
                        });
                    }
                }
                /* Masukkan panel untuk memasukkan ke inventory*/
                for (Component component : housePanel.centerPanel.getComponents()){
                    if (component instanceof SimpanPanel){
                        housePanel.centerPanel.remove(component);
                    }
                }
                SimpanPanel simpanPanel = new SimpanPanel(e);
                housePanel.centerPanel.add(simpanPanel, 0);
                housePanel.centerPanel.revalidate();
                housePanel.centerPanel.repaint();
            }
        }

        private int clickedCount = 0;
        private JButton lastButtonPressed;
        @Override
        public void mousePressed(MouseEvent e) {
            if (put){
                return;
            }

            pressed = e;
            startDragPoint = new Point(housePanel.ruanganAcuanPanel.getX() + perabotan.getKiriAtas().x*housePanel.unitSize,
                    housePanel.ruanganAcuanPanel.getY() + perabotan.getKiriAtas().y*housePanel.unitSize);
            isDragging = true;

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
            RoomPanel ruangAcuan = null;
            //cek ruangan dengan x = 0 dan y = 0 untuk acuan koordinat
            for (Component component : housePanel.centerPanel.getComponents()){
                if (component instanceof RoomPanel){
                    //cek ruangan
                    RoomPanel rp = (RoomPanel) component;
                    Point posisiRuangan = rp.ruangan.getPosisi();
                    if (posisiRuangan.equals(new Point(0,0))){
                        ruangAcuan = rp;
                    }
                }
            }
            boolean isOccupied = false;
            boolean isOutOfBoundary = false;
            PerabotanLabel.this.setBounds((Math.floorDiv(PerabotanLabel.this.getX()-housePanel.ruanganAcuanPanel.getX(),housePanel.unitSize))*housePanel.unitSize+housePanel.ruanganAcuanPanel.getX(),
                    (Math.floorDiv(PerabotanLabel.this.getY()-housePanel.ruanganAcuanPanel.getY(),housePanel.unitSize))*housePanel.unitSize + housePanel.ruanganAcuanPanel.getY(),
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
                                    for (int k = (int) (housePanel.ruanganAcuanPanel.getX() + perabotan1.getKiriAtas().getX() * housePanel.unitSize);
                                         k < housePanel.ruanganAcuanPanel.getX() + perabotan1.getKiriAtas().getX() * housePanel.unitSize + perabotan1.getDimensi().getWidth() * housePanel.unitSize;
                                         k += housePanel.unitSize) {
                                        for (int l = (int) (housePanel.ruanganAcuanPanel.getY() + perabotan1.getKiriAtas().getY() * housePanel.unitSize);
                                             l < housePanel.ruanganAcuanPanel.getY() + perabotan1.getKiriAtas().getY() * housePanel.unitSize + perabotan1.getDimensi().getHeight() * housePanel.unitSize;
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
                PerabotanLabel.this.setBounds((int) (startDragPoint.getX()),
                        (int) (startDragPoint.getY()),
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());
            }
            else if (isOutOfBoundary){
                System.out.println("Di luar batas");
                PerabotanLabel.this.setBounds((int) (startDragPoint.getX()),
                        (int) (startDragPoint.getY()),
                        PerabotanLabel.this.getWidth(),
                        PerabotanLabel.this.getHeight());
            }
            else {
                PerabotanLabel.this.getPerabotan().setKiriAtas(new Point((PerabotanLabel.this.getX()- housePanel.ruanganAcuanPanel.getX())/housePanel.unitSize ,
                        (PerabotanLabel.this.getY()- housePanel.ruanganAcuanPanel.getY())/housePanel.unitSize));
                PerabotanLabel.this.roomPanel = ruanganAcuan;
                PerabotanLabel.this.perabotan.getRuangan().getDaftarObjek().remove(PerabotanLabel.this.perabotan);
                PerabotanLabel.this.perabotan.setRuangan(ruanganAcuan.ruangan);
                ruanganAcuan.ruangan.getDaftarObjek().add(PerabotanLabel.this.perabotan);
            }
            startDragPoint = null;
            isDragging = false;
            //timpa lagi sim di atas ketika ada sesuatu
            for (Component component : housePanel.centerPanel.getComponents()){
                if (component instanceof SimLabel){
                    SimLabel simLabel = (SimLabel) component;
                    housePanel.centerPanel.remove(component);
                    housePanel.centerPanel.add(simLabel, 0);
                }
            }
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
            housePanel.centerPanel.setComponentZOrder(component, 0);
            repaint();
        }

        private class SimpanPanel extends JPanel{
            SimpanPanel(MouseEvent e){
                super(new GridLayout(0,1));
                PerabotanLabel clickedLabel = (PerabotanLabel) e.getSource();
                Perabotan clickedPerabot = clickedLabel.getPerabotan();
                this.setPreferredSize(new Dimension(3*housePanel.unitSize, 3*housePanel.unitSize));
                JButton simpanButton = new JButton("Simpan");
                simpanButton.setSize(new Dimension((int) (2*housePanel.unitSize), housePanel.unitSize));
                JButton batalButton = new JButton("Batal");
                batalButton.setSize(new Dimension((int) (2*housePanel.unitSize), housePanel.unitSize));
                simpanButton.setFocusable(false);
                batalButton.setFocusable(false);
                this.add(simpanButton);
                this.add(batalButton);
                this.setBounds(clickedLabel.getX(), clickedLabel.getY()-housePanel.unitSize, 3*housePanel.unitSize, 2*housePanel.unitSize);
                simpanButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        housePanel.rumah.getSim().getInventory().addItem(clickedPerabot,1);
                        housePanel.centerPanel.remove(PerabotanLabel.this);
                        housePanel.centerPanel.remove(SimpanPanel.this);
                        clickedPerabot.getRuangan().hilangkan(clickedPerabot);
                        housePanel.centerPanel.revalidate();
                        housePanel.centerPanel.repaint();

                    }
                });
                batalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        housePanel.centerPanel.remove(0);
                        housePanel.centerPanel.remove(SimpanPanel.this);
                        housePanel.centerPanel.revalidate();
                        housePanel.centerPanel.repaint();
                    }
                });
            }
        }
    }

    private BufferedImage generateImage(String name) {
        BufferedImage image1 =null;
        try {
            image1 =  ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + name + ".png")));
            return image1;
        } catch (Exception e) {
//            e.printStackTrace();
            image1 = generateDefaultImage();
            try {
                image1 = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/mystery box.png")));
                return image1;
            } catch (Exception ed) {
                image1 = generateDefaultImage();
                return image1;
            }
        }

    }


    public ImageIcon getImage() {
        return image;
    }

    private BufferedImage generateDefaultImage() {
        // Menghasilkan gambar default atau placeholder
        // Misalnya, sebuah gambar dengan warna solid dan pesan "Gambar tidak tersedia"
        BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, 100, 100);
        g2d.setColor(Color.BLACK);
        g2d.drawString("Gambar tidak tersedia", 10, 50);
        g2d.dispose();
        return image;
    }


}
