package game;

import entity.*;
import thread.ThreadBeli;
import thread.ThreadUpgradeRumah;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HousePanel extends JPanel implements ActionListener, Runnable, MouseWheelListener {
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
    // JButton backToMainMenuButton = new JButton("To Main Menu");
    JButton backToWorldButton = new JButton("Back To World");
    HousePanelButton statusSimButton = new HousePanelButton("View Sim Info");
    HousePanelButton lihatInventoryButton = new HousePanelButton("View Inventory");
    HousePanelButton upgradeRumahButton = new HousePanelButton("Upgrade House");
    HousePanelButton moveRoomButton = new HousePanelButton("Move Room");
    HousePanelButton editRoomButton = new HousePanelButton("Edit Room");
    HousePanelButton listObjectButton = new HousePanelButton("List Object");
    HousePanelButton goToObjectButton = new HousePanelButton("Go To Object");
    HousePanelButton actionButton = new HousePanelButton("Action");

    HousePanelButton beliItemButton = new HousePanelButton("Beli Item");




    UpgradeRumahPanel upgradeRumahPanel;
    JPanel eastPanel;
    JPanel westPanel;
    JPanel centerPanel;

    RoomPanel ruanganAcuanPanel;
    HighlightedPanel highlightedRoom;
    HighlightedPanel selectedRoom;
    SimLabel selectedSim;
    private int slotCol = 0;
    private int slotRow = 0;

    // information Label
    JLabel currentFPSLabel;
    JLabel saldoSimLabel;

    StatusSimPanel statusSimPanel;
//    DaftarThreadPane daftarThreadPane;
    private boolean isUpgradeRumah = false;
    private boolean validSectionForUpgrade = false;

    boolean isGoToObject = false;

    boolean isAction = false;
    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
//        int notches = e.getWheelRotation();
//        if (notches < 0){
//            unitSize += 2;
//        }
//        else if (notches > 0){
//            unitSize -= 2;
//        }
//
//        if (unitSize < 10){
//            unitSize = 10;
//        } else if (unitSize > 160) {
//            unitSize = 160;
//        }
//        repaint();
    }

    public class HousePanelButton extends JButton{
        HousePanelButton(String text){
            super(text);
            Font font = new Font("Comic Sans MS", Font.PLAIN, 15);
            this.setFont(font);
            this.setBackground(Color.white);
            this.setForeground(Color.black);
            this.setFocusable(false);
//            this.setPreferredSize(new Dimension(getFontMetrics(font).stringWidth(text) + 20, 30));
            this.addActionListener(HousePanel.this);
        }
    }
    HousePanel(WorldPanel worldPanel, Rumah rumah){
        this.worldPanel = worldPanel;
        this.rumah = rumah;
        this.mainMenuPanel = worldPanel.mmp;
        this.mainPanel = worldPanel.mp;
        mainPanel.requestFocusInWindow();
        this.setLayout(new BorderLayout());
        this.setBackground(Color.black);
        unitSize = 40;

        Font standardFont = new Font("Comic Sans MS", Font.PLAIN, 15);
        backToWorldButton.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
        backToWorldButton.setBackground(Color.white);
        backToWorldButton.setForeground(Color.black);
        backToWorldButton.setFocusable(false);
        backToWorldButton.setPreferredSize(new Dimension(150, 30));
        eastPanel = new JPanel(new GridLayout(0, 1, 0, 5));
        eastPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        eastPanel.setBackground(new Color(105, 180, 110));
        eastPanel.setFocusable(false);


        backToWorldButton.addActionListener(this);

        // set label untuk panel timur
        currentFPSLabel = new JLabel("FPS = 0");
        currentFPSLabel.setFont(standardFont);
        currentFPSLabel.setFocusable(false);
        currentFPSLabel.setHorizontalTextPosition(JLabel.CENTER);
        currentFPSLabel.setVerticalTextPosition(JLabel.CENTER);
        currentFPSLabel.setPreferredSize(new Dimension(eastPanel.getWidth()
                , getFontMetrics(currentFPSLabel.getFont()).getHeight()*3));

        eastPanel.add(currentFPSLabel);

        // set panel barat
//        westPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 15));
        westPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15,10,10,10);
        gbc.gridx = 0;
        westPanel.setPreferredSize(new Dimension(mainPanel.width/5, mainPanel.height));
        westPanel.setBackground(new Color(105, 180, 110));
        westPanel.setFocusable(false);

        //add button to west panel
        //westPanel.add(backToMainMenuButton);
        int gridy = 0;
        westPanel.add(backToWorldButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(statusSimButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(lihatInventoryButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(upgradeRumahButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(moveRoomButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(editRoomButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(listObjectButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(goToObjectButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(actionButton, gbc);
        gbc.gridy = ++gridy;
        westPanel.add(beliItemButton, gbc);

        centerPanel = new JPanel(null);
        centerPanel.setPreferredSize(new Dimension(3*mainPanel.width/5, mainPanel.height));
        centerPanel.setBackground(Color.darkGray);
        centerPanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isUpgradeRumah){
                    Ruangan ruanganAcuan = null;
                    boolean occupied = false;
                    boolean inReach = false;
                    //only highlighted the available space
                    HighlightedPanel selectedSection = new HighlightedPanel(null);
                    selectedSection.setBounds(Math.floorDiv(e.getX()-ruanganAcuanPanel.getX(),6*unitSize)*(6*unitSize) + ruanganAcuanPanel.getX()
                            ,Math.floorDiv(e.getY()-ruanganAcuanPanel.getY(),6*unitSize)*(6*unitSize) + ruanganAcuanPanel.getY()
                            , 6*unitSize, 6*unitSize);
                    selectedSection.setOpaque(false);
                    for (Component component : centerPanel.getComponents()){
                        if (component instanceof HighlightedPanel){
                            centerPanel.remove(component);
                        }
                    }
                    //cek apakah si panel ini udah tepat lokasi nya
                    for(Component component : centerPanel.getComponents()){
                        if (component instanceof RoomPanel){
                            //cek kalau dia di dalam, ya gak bisa
                            RoomPanel rp = (RoomPanel) component;
                            if (selectedSection.getLocation().x == component.getX() && selectedSection.getY() == component.getY()){
                                occupied = true;
                            }
                            else{
                                //cek kalau dia di jangkauan ruangan yamg bisa
                                //untuk sisi atas
                                if (selectedSection.getX() == rp.getBounds().x && selectedSection.getBounds().y == rp.getY()-6*unitSize){
                                    inReach = true;
                                }
                                //untuk sisi kiri
                                if (selectedSection.getX() == rp.getBounds().x - 6*unitSize && selectedSection.getBounds().y == rp.getY()){
                                    inReach = true;
                                }
                                //untuk sisi bawah
                                if (selectedSection.getLocation().x == rp.getX() && selectedSection.getY() == rp.getY()+6*unitSize){
                                    inReach = true;
                                }
                                //untuk sisi kanan
                                if (selectedSection.getX() == rp.getX()+6*unitSize && selectedSection.getY() == rp.getY()){
                                    inReach = true;
                                }
                            }

                        }
                    }
                    if (occupied){
                        selectedSection.setBorder(new LineBorder(Color.red, 5, true));
                        centerPanel.add(selectedSection, 0);
                        highlightedRoom = selectedSection;
                        selectedSection.repaint();
                        validSectionForUpgrade = false;
                        return;
                    }
                    else if (inReach){
                        selectedSection.setBorder(new LineBorder(Color.yellow, 5, true));
                        centerPanel.add(selectedSection, 0);
                        highlightedRoom = selectedSection;
                        selectedSection.repaint();
                        validSectionForUpgrade = true;
                        return;
                    }
                }
            }
        });
        centerPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainPanel.requestFocusInWindow();
                if (isUpgradeRumah){
                    isUpgradeRumah = false;
                    if (validSectionForUpgrade){
                        rumah.busyUpgrading = true;
                        int durasi;
                        if (rumah.world.developerMode){
                            durasi = 10;
                        }
                        else{
                            durasi = 1080;
                        }
                        ThreadUpgradeRumah threadAksiPasif = new ThreadUpgradeRumah("Upgrade Rumah", durasi, HousePanel.this, rumah.world);
                        rumah.world.getListThreadAksiPasif().add(threadAksiPasif);
//                        threadAksiPasif.startThread();
//                        threadAksiPasif.start();
//                        ThreadAksi threadAksi = new ThreadAksi("Upgrade Rumah", 1080, method, HousePanel.this, rumah.world);
//                        rumah.world.getListThreadAksi().add(threadAksi);
//                        threadAksi.start();
                    }
                    else{
                        rumah.busyUpgrading = false;
                    }
                    for (Component component : centerPanel.getComponents()){
                        if (component instanceof HighlightedPanel){
                            component.setVisible(false);
                        }
                    }
                }
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
        });
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
        upgradeRumahPanel = new UpgradeRumahPanel();
        System.out.println(rumah.getSim().getKekenyangan());
        System.out.println(rumah.getSim().getKesehatan());
        System.out.println(rumah.getSim().getMood());
        System.out.println(rumah.getSim().getPekerjaan());
        startThread();
        repaint();
    }

    public void upgradeRumah(){
        if (isUpgradeRumah) {
            isUpgradeRumah = false;
        }
        Ruangan ruanganBaru = new Ruangan("Ruangan " + (rumah.getDaftarRuangan().size()+1)
                , rumah,new Point((highlightedRoom.getX()-ruanganAcuanPanel.getX())/unitSize,
                (highlightedRoom.getY()-ruanganAcuanPanel.getY())/unitSize));
        rumah.upgrade(ruanganBaru);
        RoomPanel newRoomPanel = new RoomPanel(ruanganBaru, rumah, HousePanel.this);
        centerPanel.add(newRoomPanel,0);
        centerPanel.revalidate();
        centerPanel.repaint();
        rumah.busyUpgrading = false;
        for (Component component : centerPanel.getComponents()){
            if (component instanceof HighlightedPanel){
                centerPanel.remove(component);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == backToWorldButton){
            mainPanel.remove(this);
            mainPanel.add(worldPanel, BorderLayout.CENTER);
            mainPanel.add(worldPanel.wop, BorderLayout.EAST);
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
            ((JSpinner.DefaultEditor) kuantitasSpinner.getEditor()).getTextField().setEditable(false);
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
                int durasi;
                if (worldPanel.getWorld().developerMode){
                    durasi = 5;
                }
                else{
                    durasi = (new Random().nextInt(5)+1)*30;
                }
                ThreadBeli threadBeli = new ThreadBeli("beli " + selectedItem.getNama(),
                        durasi, parameters, buyed, rumah.world);
                rumah.world.getListThreadAksiPasif().add((threadBeli));
//                threadBeli.startThread();
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
        if (e.getSource() == upgradeRumahButton){
            boolean exist = false;
            Component theComponent = null;
            for (Component component : centerPanel.getComponents()){
                if (component instanceof UpgradeRumahPanel){
                    exist = true;
                    theComponent = component;
                }
            }
            if (exist){
                theComponent.setVisible(true);
                centerPanel.setComponentZOrder(theComponent, 0);
            }
            else{
                upgradeRumahPanel = new UpgradeRumahPanel();
                centerPanel.add(upgradeRumahPanel, 0);
            }
        }
        if (e.getSource() == statusSimButton){
            //check whether there is any StatusSimPanel
            for (Component component : centerPanel.getComponents()){
                if (component instanceof StatusSimPanel){
                    centerPanel.remove(component);
                }
            }
            statusSimPanel = new StatusSimPanel(HousePanel.this.rumah.getSim(), HousePanel.this);
            HousePanel.this.rumah.getSim().viewSimInfo();
            centerPanel.add(statusSimPanel, 0);
            centerPanel.revalidate();
            centerPanel.repaint();
        }
        if (e.getSource() == actionButton) {
            for (Component component : centerPanel.getComponents()){
                if (component instanceof ActionPanel){
                    centerPanel.remove(component);
                }
            }
            ActionPanel actionPanel = new ActionPanel(this);
            centerPanel.add(actionPanel, 0);
            centerPanel.revalidate();
            centerPanel.repaint();
        }

        if (e.getSource() == goToObjectButton){
            if (!isGoToObject) {
                if (rumah.getJumlahPerabot() < 1){
                    JOptionPane.showMessageDialog(null, "Tidak ada object");
                    return;
                }
                for (Component component : westPanel.getComponents()){
                    if (component instanceof JButton jb){
                        if (jb.getText().equals(goToObjectButton.getText())){
                            continue;
                        }
                        if (!jb.isEnabled()){
                            continue;
                        }
                        jb.setEnabled(false);
                    }
                }
                System.out.println(selectedSim.sim.getNamaLengkap());
                System.out.println("silakan pilih objek yang ingin dituju");
                isGoToObject = true;
            }
            else{
                for (Component component : westPanel.getComponents()){
                    if (component instanceof JButton jb){
                        if (jb.getText().equals(goToObjectButton.getText())){
                            continue;
                        }
                        jb.setEnabled(true);
                    }
                }
                System.out.println("matikan go to object");
                isGoToObject = false;
            }
        }
        if (e.getSource() == listObjectButton){
            rumah.showAllFurniture();
        }
        if (e.getSource() == moveRoomButton){
            selectedSim.moving = !selectedSim.moving;
            if (selectedSim.moving){
                System.out.println("silakan pilih ruangan");
            }
        }
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
                if(rumah.busyUpgrading){
                    upgradeRumahButton.setEnabled(false);
                }
                else{
                    if (!isAction){
                        upgradeRumahButton.setEnabled(true);
                    }
                }
                mainPanel.setFocusable(true);
                if (selectedSim == null){
                    goToObjectButton.setEnabled(false);
                    moveRoomButton.setEnabled(false);
                    actionButton.setEnabled(false);
                }
                else {
                    if (!isAction){
                        goToObjectButton.setEnabled(true);
                        moveRoomButton.setEnabled(true);
                        actionButton.setEnabled(true);
                    }

                }


            }
            if (timer >= Math.pow(10, 9)){
                currentFPS = drawCount;
                drawCount = 0;
                timer = 0;
                currentFPSLabel.setText("FPS = " + currentFPS);
            }
        }

    }
    public void startThread(){
        thread = new Thread(this);
        thread.start();
    }

    public boolean isLoading(){
        boolean loading = false;
        for (Component component : centerPanel.getComponents()){
            if (component instanceof TimerAksiPanel){
                loading = true;
                break;
            }
        }
        return  loading;
    }

    public void disabledAllButton(){
        for (Component component : westPanel.getComponents()){
            component.setEnabled(false);
        }
    }

    public void enabledAllButton(){
        for (Component component : westPanel.getComponents()){
            component.setEnabled(true);
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        // draw fps
    }


    public void update() {
        int speed = 4*unitSize/16; // kecepatan pergerakan kamera
        KeyHandler keyHandler = mainPanel.keyH;
        if(keyHandler.rightPressed){
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel || component instanceof HighlightedPanel ||component instanceof SimLabel){
                    component.setBounds(component.getX() + speed, component.getY(), component.getWidth(), component.getHeight());
                }
                if (component instanceof PerabotanLabel pl){
                    pl.setBounds(pl.getX() + speed, pl.getY(), pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(speed, 0);
                    }
                }
            }
        }
        else if (keyHandler.leftPressed){
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel|| component instanceof HighlightedPanel||component instanceof SimLabel){
                    component.setBounds(component.getX()-speed, component.getY(), component.getWidth(), component.getHeight());
                }
                if (component instanceof PerabotanLabel pl){
                    pl.setBounds(pl.getX() - speed, pl.getY(), pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(-speed, 0);
                    }
                }
            }
        }
        else if (keyHandler.upPressed){
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel|| component instanceof HighlightedPanel||component instanceof SimLabel){
                    component.setBounds(component.getX(), component.getY()-speed, component.getWidth(), component.getHeight());

                }
                if (component instanceof PerabotanLabel pl){
                    pl.setBounds(pl.getX(), pl.getY()-speed, pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(0, -speed);
                    }
                }
            }
        }
        else if (keyHandler.downPressed){
            for (Component component : centerPanel.getComponents()){
                if (component instanceof RoomPanel|| component instanceof HighlightedPanel||component instanceof SimLabel){
                    component.setBounds(component.getX(), component.getY()+speed, component.getWidth(), component.getHeight());
                }
                if (component instanceof PerabotanLabel pl){
                    pl.setBounds(pl.getX(), pl.getY()+speed, pl.getWidth(), pl.getHeight());
                    if (pl.startDragPoint != null){
                        pl.startDragPoint.translate(0, speed);
                    }
                }
            }
        }
        if (statusSimPanel != null){
            statusSimPanel.update();
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

    private class UpgradeRumahPanel extends JPanel implements ActionListener{
        HousePanelButton OKButton;
        HousePanelButton cancelButton;
        ButtonContainer buttonContainer;

        UpgradeRumahPanel(){
            OKButton = new HousePanelButton("OK");
            OKButton.setPreferredSize(new Dimension(160, 80));
            cancelButton = new HousePanelButton("Batal");
            cancelButton.setPreferredSize(new Dimension(160, 80));
            OKButton.setFocusable(false);
            cancelButton.setFocusable(false);
            OKButton.setBounds(0,0, 10*unitSize/4, unitSize);
            cancelButton.setBounds(6*unitSize/2,0, 10*unitSize/4, unitSize);
            OKButton.setVisible(true);
            cancelButton.setVisible(true);
            OKButton.setOpaque(true);
            cancelButton.setOpaque(true);
            setLayout(null);
            setPreferredSize(new Dimension(8*unitSize, 6*unitSize));
            setSize(8*unitSize, 8*unitSize);
            setBounds(centerPanel.getWidth()/4, centerPanel.getHeight()/4, 8*unitSize, 6*unitSize);
            setBorder(new LineBorder(Color.BLACK, 5, true));
            setOpaque(true);
            setBackground(new Color(150, 178, 102, 255));
            setVisible(true);
            buttonContainer = new ButtonContainer();
            buttonContainer.setBounds((getWidth()-6*unitSize)/2
                    ,3*unitSize
                    , 6*unitSize, unitSize);
            repaint();
            buttonContainer.add(OKButton);
            buttonContainer.add(cancelButton);
            OKButton.addActionListener(this);
            cancelButton.addActionListener(this);
            add(buttonContainer);
            revalidate();

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == OKButton){
                if (1500 > rumah.getSim().getUang()){
                    JOptionPane.showMessageDialog(this, "Uang tidak cukup",
                            "Peningkatan Rumah Gagal", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                isUpgradeRumah = true;
                setVisible(false);
                rumah.busyUpgrading = true;
            }
            else if(e.getSource() == cancelButton){
                isUpgradeRumah = false;
                setVisible(false);
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(150, 178, 102, 255));
            g2d.fillRect(0,0, 8*unitSize, 8*unitSize);
            //tulis tulisan untuk message
            String text1 = "Biaya yang dibutuhkan = 1500";
            Font font1 = new Font("Comic Sans MS", Font.BOLD, 16);
            int fontHeight1 = getFontMetrics(font1).getHeight();
            int fontWidth1 = getFontMetrics(font1).stringWidth(text1);
            int drawStringX = (getWidth()-fontWidth1)/2;
            int drawStringY = 5+fontHeight1;
            g2d.setFont(font1);
            g2d.setColor(Color.black);
            g2d.drawString(text1, drawStringX,drawStringY);
            Font font2 = new Font("Comic Sans MS", Font.PLAIN, 16);
            String text2 = "Yakin untuk melanjutkan?";
            int fontHeight2 = getFontMetrics(font1).getHeight();
            int fontWidth2 = getFontMetrics(font1).stringWidth(text2);
            int drawStringX2 = (getWidth()-fontWidth2)/2;
            int drawStringY2 = 5+fontHeight1+fontHeight2;
            g2d.setFont(font2);
            g2d.drawString(text2, drawStringX2,drawStringY2);
        }

        private class ButtonContainer extends JPanel{
            ButtonContainer(){
//                super(new GridLayout(1,0, 10, 0));
                super(new GridLayout(1,0));
                setFocusable(false);
                setBackground(new Color(150,178,102));
                setOpaque(true);
                setPreferredSize(new Dimension(6*unitSize, unitSize));
                setBounds(10, unitSize+10, 6*unitSize, unitSize);
            }
        }
    }

    private class HighlightedPanel extends JPanel{
        HighlightedPanel(LayoutManager mgr){
            super(mgr);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }

    private class WarningMessage extends JPanel{
        WarningMessage(){
            super(new FlowLayout());
            setBackground(new Color(150, 178, 102));
        }
    }
}
