package game;

import entity.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HousePanel extends JPanel implements ActionListener, Runnable {
    public WorldPanel worldPanel;
    public Rumah rumah;
    public int unitSize = 40;
    public MainMenuPanel mainMenuPanel;
    public MainPanel mainPanel;
    public int cameraWidth;
    public int cameraHeight;
    private Thread thread;
    private int FPS = 60;
    private int currentFPS;
    JButton backToMainMenuButton = new JButton("To Main Menu");
    JButton backToWorldButton = new JButton("Keluar rumah");
    HousePanelButton beliItemButton = new HousePanelButton("Beli Item");
    JPanel eastPanel;
    JPanel westPanel;
    JPanel centerPanel;

    // information Label
    JLabel currentFPSLabel;
    JLabel saldoSimLabel;

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

        centerPanel = new JPanel(null);
        centerPanel.setPreferredSize(new Dimension(3*mainPanel.width/5, mainPanel.height));
        centerPanel.setBackground(Color.black);
        for (Ruangan ruangan : rumah.getDaftarRuangan()){
            RoomPanel rp = new RoomPanel(ruangan, this.rumah, this);
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
            mainPanel.add(worldPanel.wop);
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
            String[] StringItem = new String[BisaDibeli.listObjek.length];
            JLabel ChooseItemLabel = new JLabel("Pilih item");
            JComboBox<String> itemChooser = new JComboBox<>(StringItem);
            JLabel amountLabel = new JLabel("Jumlah:");



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

            }
            if (timer >= Math.pow(10, 9)){
                currentFPS = drawCount;
                drawCount = 0;
                timer = 0;
                currentFPSLabel.setText("FPS = " + currentFPS);
                saldoSimLabel.setText("<html>Total uang " + rumah.getSim().getNamaLengkap()
                        + " :<br>" + rumah.getSim().getUang() + "</html>");

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
        int speed = 3*unitSize/16; // kecepatan pergerakan kamera
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
            }

        }
/*        try {
            Thread.sleep(1000/60);
        } catch (InterruptedException e){

        }*/
        repaint();
    }

}
