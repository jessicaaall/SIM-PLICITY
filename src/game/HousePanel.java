package game;

import entity.*;
import thread.ThreadAksi;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HousePanel extends JPanel implements ActionListener, Runnable {
    public WorldPanel worldPanel;
    public Rumah rumah;
    public int unitSize = 40;
    public MainMenuPanel mainMenuPanel;
    public MainPanel mainPanel;
    public InventoryPanel inventoryPanel;
    public int cameraWidth;
    public int cameraHeight;
    private Thread thread;
    private int FPS = 60;
    private int currentFPS;
    JButton backToMainMenuButton = new JButton("To Main Menu");
    JButton backToWorldButton = new JButton("Keluar rumah");
    HousePanelButton beliItemButton = new HousePanelButton("Beli Item");
    HousePanelButton lihatInventoryButton = new HousePanelButton("Lihat Inventory");
    JPanel eastPanel;
    JPanel westPanel;
    JPanel centerPanel;

    RoomPanel ruanganAcuanPanel;

    private int slotCol = 0;
    private int slotRow = 0;

    // information Label
    JLabel currentFPSLabel;
    JLabel saldoSimLabel;
    DaftarThreadPane daftarThreadPane;

    private class HousePanelButton extends JButton{
        HousePanelButton(String text){
            super(text);
            this.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
            this.setBackground(Color.green);
            this.setForeground(Color.black);
            this.setFocusable(false);
            this.addActionListener(HousePanel.this);
        }
    }
    HousePanel(WorldPanel worldPanel, Rumah rumah){
        this.worldPanel = worldPanel;
        this.rumah = rumah;
        this.mainMenuPanel = worldPanel.mmp;
        this.mainPanel = worldPanel.mp;
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        unitSize = 40;

        Font standardFont = new Font("Comic Sans MS", Font.PLAIN, 15);
        backToMainMenuButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        backToMainMenuButton.setBackground(Color.green);
        backToMainMenuButton.setForeground(Color.black);
        backToMainMenuButton.setFocusable(false);
        backToMainMenuButton.addActionListener(this);
        backToWorldButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        backToWorldButton.setBackground(Color.green);
        backToWorldButton.setForeground(Color.black);
        backToWorldButton.setFocusable(false);
        backToWorldButton.addActionListener(this);
        eastPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        eastPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        eastPanel.setBackground(Color.pink);
        eastPanel.setFocusable(false);

        // set label untuk panel timur
        currentFPSLabel = new JLabel("FPS = 0");
        currentFPSLabel.setFont(standardFont);
        currentFPSLabel.setFocusable(false);
        currentFPSLabel.setHorizontalTextPosition(JLabel.CENTER);
        currentFPSLabel.setVerticalTextPosition(JLabel.CENTER);
        currentFPSLabel.setPreferredSize(new Dimension(eastPanel.getWidth()
                , getFontMetrics(currentFPSLabel.getFont()).getHeight()*3));

        saldoSimLabel = new JLabel("<html>Total uang " + rumah.getSim().getNamaLengkap() + " :<br>0</html>");
        saldoSimLabel.setFocusable(false);
        saldoSimLabel.setFont(standardFont);
        saldoSimLabel.setPreferredSize(new Dimension(eastPanel.getWidth()
                , getFontMetrics(saldoSimLabel.getFont()).getHeight()*3));
//        saldoSimLabel.setBorder(BorderFactory.createDashedBorder(Color.black));
/*        saldoSimLabel.setPreferredSize(new Dimension(saldoSimLabel.getFontMetrics(standardFont).stringWidth(saldoSimLabel.getText())+15,
                saldoSimLabel.getFontMetrics(standardFont).getHeight() + 10));*/
        saldoSimLabel.setHorizontalTextPosition(JLabel.CENTER);
        saldoSimLabel.setVerticalTextPosition(JLabel.CENTER);
        eastPanel.add(currentFPSLabel);
        eastPanel.add(saldoSimLabel);

        // set panel barat
        westPanel = new JPanel(new FlowLayout());
        westPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        westPanel.setBackground(Color.pink);
        westPanel.setFocusable(false);

        //add button to west panel
        westPanel.add(backToMainMenuButton);
        westPanel.add(backToWorldButton);
        westPanel.add(beliItemButton);
        westPanel.add(lihatInventoryButton);

        centerPanel = new JPanel(null);
        centerPanel.setPreferredSize(new Dimension(3*mainPanel.width/5, mainPanel.height));
        centerPanel.setBackground(Color.black);
        for (Ruangan ruangan : rumah.getDaftarRuangan()){
            RoomPanel rp = new RoomPanel(ruangan, this.rumah, this);
            if (rp.ruangan.getPosisi().equals(new Point(0,0))){
                ruanganAcuanPanel = rp;
            }
            centerPanel.add(rp);
            centerPanel.repaint();
        }
        centerPanel.setFocusable(false);
        this.add(eastPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
        this.add(westPanel, BorderLayout.WEST);
        cameraWidth = 3*mainPanel.width/5;
        cameraHeight = mainPanel.height;
        startThread();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToMainMenuButton){
            mainPanel.remove(this);
            mainPanel.add(mainMenuPanel);
            thread.interrupt();
            thread = null;
            mainPanel.revalidate();
            mainPanel.repaint();
        }
        else if (e.getSource() == backToWorldButton){
            mainPanel.remove(this);
            worldPanel.add(worldPanel.wop);
            mainPanel.add(worldPanel);
            thread.interrupt();
            thread = null;
            mainPanel.revalidate();
            mainPanel.repaint();
            worldPanel.startMainThread();
            worldPanel.playMusic(0);
        }
        else if (e.getSource() == beliItemButton){
            //cek list harga
            JPanel optionPanel = new JPanel(new GridLayout(0,1,0,5));
            List<Objek> item_item = new ArrayList<>();
            int kuantitas;
            int totalHarga;
            Objek selectedItem = null;

            for (int i = 0; i < rumah.getSim().getTheirWorld().getListObjek().length; i++){
                if (rumah.getSim().getTheirWorld().getListObjek()[i] instanceof BisaDibeli){
                    item_item.add(rumah.getSim().getTheirWorld().getListObjek()[i]);
                }

            }
            String[] namaItem = new String[item_item.size()];
            for(int i = 0; i < namaItem.length; i++){
                namaItem[i] = item_item.get(i).getNama();
            }
            JLabel chooseItemLabel = new JLabel("Pilih item");
            JComboBox<String> itemChooser = new JComboBox<>(namaItem);
            JLabel kuantitasLabel = new JLabel("Jumlah:");
            JLabel totalHargaLabel = new JLabel();
            // add (-) (kuantitas) (+) button
            SpinnerNumberModel numberModel = new SpinnerNumberModel(1,1,10,1);
            JSpinner kuantitasSpinner = new JSpinner(numberModel);
            // get kuantitas from the plus-minus button

            optionPanel.add(chooseItemLabel);
            optionPanel.add(itemChooser);
            optionPanel.add(kuantitasLabel);
            optionPanel.add(kuantitasSpinner);
            optionPanel.add(totalHargaLabel);
            selectedItem = item_item.get(itemChooser.getSelectedIndex());
            kuantitas  = (int) kuantitasSpinner.getValue();
            totalHarga = selectedItem.getHarga()*kuantitas;
            totalHargaLabel.setText("Total harga = " + totalHarga);
            itemChooser.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    Objek selectedItem1 = item_item.get(itemChooser.getSelectedIndex());
                    int kuantitas1 = (int) kuantitasSpinner.getValue();
                    int totalHarga1 = selectedItem1.getHarga()*kuantitas1;
                    totalHargaLabel.setText("Total harga = " + totalHarga1);
                }
            });
            kuantitasSpinner.addChangeListener(new ChangeListener() {
                @Override
                public void stateChanged(ChangeEvent e) {
                    Objek selectedItem1 = item_item.get(itemChooser.getSelectedIndex());
                    int kuantitas1 = (int) kuantitasSpinner.getValue();
                    int totalHarga1 = selectedItem1.getHarga()*kuantitas1;
                    totalHargaLabel.setText("Total harga = " + totalHarga1);
                }
            });
            int option = JOptionPane.showConfirmDialog(this, optionPanel, "Beli Item",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (option == JOptionPane.OK_OPTION){
                selectedItem = item_item.get(itemChooser.getSelectedIndex());
                kuantitas  = (int) kuantitasSpinner.getValue();
                totalHarga = selectedItem.getHarga()*kuantitas;
                if (totalHarga > rumah.getSim().getUang()){
                    JOptionPane.showMessageDialog(this, "Uang tidak cukup",
                            "Pembelian Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // kalau sudah benar dan oke, tinggal lakukan operasi
                rumah.getSim().setUang(rumah.getSim().getUang()-totalHarga);
//                rumah.getSim().getInventory().addItem(selectedItem, kuantitas);
                BisaDibeli buyed = (BisaDibeli) selectedItem;
                Object[] parameters = {rumah.getSim(), totalHarga};
                Class<?>[] paramTypes = {Sim.class, int.class};
                Method method;
                try {
                    method = buyed.getClass().getMethod("beli", Sim.class, int.class);
                } catch (NoSuchMethodException ex) {
                    throw new RuntimeException(ex);
                }
                ThreadAksi threadAksi = new ThreadAksi("beli " + selectedItem.getNama(),
                        (new Random().nextInt(5)+1)*30, method, parameters, buyed, rumah.world);
                rumah.world.getListThreadAksi().add(threadAksi);
                threadAksi.start();
            }
        }
        if (e.getSource() == lihatInventoryButton){
            inventoryPanel = new InventoryPanel(this);
            boolean exist = false;
            //cek kalau sudah ada komponen inventoryPanel
            for (Component component: centerPanel.getComponents()){
                if (component instanceof InventoryPanel){
                    inventoryPanel = (InventoryPanel) component;
                    exist = true;
                }
            }
            if (exist){
                inventoryPanel.setVisible(true);
            }
            else {
                centerPanel.add(inventoryPanel, 0);
                centerPanel.revalidate();
                centerPanel.repaint();
                revalidate();
                repaint();
            }

        }
    }

    public void drawMiniWindow(JPanel panel, int x, int y, int width, int height){
        Graphics2D g2d = (Graphics2D) panel.getGraphics();
        Color c = new Color(0,0,0, 180);;
        g2d.setColor(c);
        g2d.fillRoundRect(x,y,width,height,20,20);
        c = Color.gray;
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRoundRect(x+2, y+2, width-10, height-10, 10, 10);
    }

    public void drawInventory(){
        Graphics2D g2d = (Graphics2D) centerPanel.getGraphics();
        int frameX = unitSize*8;
        int frameY = unitSize;
        int frameWidth = unitSize*5;
        int frameHeight = unitSize*8;
        drawMiniWindow(centerPanel, frameX, frameY, frameWidth, frameHeight);

        //slot
        final int slotStartX = frameX + 10;
        final int slotStartY = frameX + 10;
        int slotX = slotStartX;
        int slotY = slotStartY;

        //cursor
        int cursorX = slotStartX + (unitSize*slotCol);
        int cursorY = slotStartY + (unitSize*slotRow);
        int cursorWidth = unitSize;
        int cursorHeight = unitSize;

        //draw cursor
        g2d.setColor(Color.lightGray);
        g2d.drawRoundRect(cursorX, cursorY, cursorWidth, cursorHeight, 5, 5);
    }




    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while (thread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if (delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;

            }
            if (timer >= Math.pow(10, 9)){
                currentFPS = drawCount;
                drawCount = 0;
                timer = 0;
                currentFPSLabel.setText("FPS = " + currentFPS);
                saldoSimLabel.setText("<html>Total uang " + rumah.getSim().getNamaLengkap()
                        + " :<br>" + rumah.getSim().getUang() + "</html>");
                // update label saldo
                saldoSimLabel.setText("<html>Total uang " + rumah.getSim().getNamaLengkap() + " :<br>" +
                        rumah.getSim().getUang() + "</html>");

            }
        }

    }
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        // draw fps
    }


    public void update() {
        int speed = 4*unitSize/16; // kecepatan pergerakan kamera
        KeyHandler keyHandler = mainPanel.keyH;
//        int mapX = 0;
//        int mapY = 0;
        if(keyHandler.rightPressed){
//            System.out.println("Right");
//            centerPanel.setLocation(mapX + speed, mapY);
//            mapX += speed;
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel){
                    RoomPanel rp = (RoomPanel) component;
                    rp.setBounds(rp.getX() + speed, rp.getY(), rp.getWidth(), rp.getHeight());
                }
                if (component instanceof  PerabotanLabel){
                    PerabotanLabel pl = (PerabotanLabel) component;
                    pl.setBounds(pl.getX() + speed, pl.getY(), pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(speed, 0);
                    }
                }
            }
        }
        else if (keyHandler.leftPressed){
//            System.out.println("Left");
//            centerPanel.setLocation(mapX - speed, mapY);
//            mapX -= speed;
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel){
                    RoomPanel rp = (RoomPanel) component;
                    rp.setBounds(rp.getX()-speed, rp.getY(), rp.getWidth(), rp.getHeight());
                }
                if (component instanceof  PerabotanLabel){
                    PerabotanLabel pl = (PerabotanLabel) component;
                    pl.setBounds(pl.getX() - speed, pl.getY(), pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(-speed, 0);
                    }
                }
            }
        }
        else if (keyHandler.upPressed){
//            System.out.println("Up");
//            centerPanel.setLocation(mapX, mapY + speed);
//            mapY += speed;
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel){
                    RoomPanel rp = (RoomPanel) component;
                    rp.setBounds(rp.getX(), rp.getY()-speed, rp.getWidth(), rp.getHeight());
                }
                if (component instanceof  PerabotanLabel){
                    PerabotanLabel pl = (PerabotanLabel) component;
                    pl.setBounds(pl.getX(), pl.getY()-speed, pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(0, -speed);
                    }
                }
            }
        }
        else if (keyHandler.downPressed){
//            System.out.println("Down");
//            centerPanel.setLocation(mapX, mapY - speed);
//            mapY -= speed;
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel){
                    RoomPanel rp = (RoomPanel) component;
                    rp.setBounds(rp.getX(), rp.getY()+speed, rp.getWidth(), rp.getHeight());
                }
                if (component instanceof  PerabotanLabel){
                    PerabotanLabel pl = (PerabotanLabel) component;
                    pl.setBounds(pl.getX(), pl.getY()+speed, pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(0, speed);
                    }
                }
            }

        }
        repaint();
    }

    private class BuyItemPanel extends JPanel implements MouseListener, MouseMotionListener{


        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {

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

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    }

}
