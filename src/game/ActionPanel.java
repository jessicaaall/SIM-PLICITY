package game;

import entity.*;
import thread.ThreadAksi;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.*;

public class ActionPanel extends JPanel implements MouseListener, MouseMotionListener {
    MouseEvent pressed;
    Point location;

    HousePanel hp;

    ActionPanel(HousePanel hp){
        super(new BorderLayout());
        this.hp = hp;
        setLocation(new Point(0,0));
        setBackground(new Color(150, 178, 102));
        setOpaque(true);
        setBorder(new LineBorder(Color.BLACK, 3, true));

        JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 320, 30));
        mainPanel.setBorder(new LineBorder(Color.lightGray, 1, false));
        mainPanel.setBackground(new Color(150, 178, 102));
        JPanel aksiPanel1 = new JPanel();
        aksiPanel1.setBackground(new Color(150, 178, 102));
        GridLayout layout1 = new GridLayout(6, 3);
        layout1.setHgap(20);
        layout1.setVgap(20);
        aksiPanel1.setLayout(layout1);

        AksiButton kerjaButton = new AksiButton("kerja");
        AksiButton olahragaButton = new AksiButton("olahraga");
        AksiButton tidurButton = new AksiButton("tidur");
        AksiButton membersihkanKasurButton = new AksiButton("membersihkan kasur");
        AksiButton makanButton = new AksiButton("makan");
        AksiButton memasakButton = new AksiButton("memasak");
        AksiButton berkunjungButton = new AksiButton("berkunjung");
        AksiButton buangAirButton = new AksiButton("buang air");
        AksiButton siramToiletButton = new AksiButton("siram toilet");
        AksiButton mandiButton = new AksiButton("mandi");
        AksiButton mainGameButton = new AksiButton("main game");
        AksiButton kerjainTubesButton = new AksiButton("mengerjakan tubes");
        AksiButton nontonTVButton = new AksiButton("nonton TV");
        AksiButton sikatGigiButton = new AksiButton("sikat gigi");
        AksiButton cuciTanganButton = new AksiButton("cuci tangan");
        AksiButton melihatWaktuButton = new AksiButton("melihat waktu");
        AksiButton pulangButton = new AksiButton("pulang");
        AksiButton gantiPekerjaanButton = new AksiButton("ganti pekerjaan");

        aksiPanel1.add(kerjaButton);
        aksiPanel1.add(olahragaButton);
        aksiPanel1.add(tidurButton);
        aksiPanel1.add(membersihkanKasurButton);
        aksiPanel1.add(makanButton);
        aksiPanel1.add(memasakButton);
        aksiPanel1.add(berkunjungButton);
        aksiPanel1.add(buangAirButton);
        aksiPanel1.add(siramToiletButton);
        aksiPanel1.add(mandiButton);
        aksiPanel1.add(mainGameButton);
        aksiPanel1.add(kerjainTubesButton);
        aksiPanel1.add(nontonTVButton);
        aksiPanel1.add(sikatGigiButton);
        aksiPanel1.add(cuciTanganButton);
        aksiPanel1.add(melihatWaktuButton);
        aksiPanel1.add(pulangButton);
        aksiPanel1.add(gantiPekerjaanButton);

        mainPanel.add(aksiPanel1);

        JButton closeButton = new JButton("Close");

        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        mainPanel.add(closeButton);
        add(mainPanel, BorderLayout.CENTER);
        closeButton.setPreferredSize(new Dimension(160, 40));
        closeButton.setFocusable(false);
        setFocusable(false);
        mainPanel.setFocusable(false);
        setBounds(0,0, 570, 420);
        addMouseListener(this);
        addMouseMotionListener(this);

        // semua action listener button
        tidurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("aksi tidur");
                //cek apakah ada kasur di sekitar sim
                ItemChecker<Kasur> itemChecker = new ItemChecker<>(Kasur.class);
                Kasur kasur = itemChecker.checkItem();
                if (kasur == null){
                    JOptionPane.showMessageDialog(null, "Tidak ada kasur di sekitar");
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, kasur, "tidur");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
                System.out.println("tidur");
            }
        });
        buangAirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Toilet> itemChecker = new ItemChecker<Toilet>(Toilet.class);
                Toilet toilet = itemChecker.checkItem();
                if (toilet ==  null){
                    JOptionPane.showMessageDialog(null, "Tidak ada WC di sekitar");
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, toilet, "ngising");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        makanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<MejaDanKursi> itemChecker = new ItemChecker<>(MejaDanKursi.class);
                MejaDanKursi mejaDanKursi = itemChecker.checkItem();
                if (mejaDanKursi == null){
                    JOptionPane.showMessageDialog(null, "Tidak ada Meja-Kursi di sekitar");
                    return;
                }
                MakanPanel makanPanel = new MakanPanel(hp, mejaDanKursi);
                makanPanel.setBounds(160, 80, 320, 320);
                hp.centerPanel.add(makanPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });

        kerjaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, "kerja");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        olahragaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, "olahraga");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });

        melihatWaktuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Jam> itemChecker = new ItemChecker<>(Jam.class);
                Jam jam = itemChecker.checkItem();
                if (jam == null){
                    JOptionPane.showMessageDialog(null, "Tidak ada jam di sekitar");
                    return;
                }
                WaktuPanel waktuPanel = new WaktuPanel(jam.getWaktu(), hp);
                waktuPanel.setBounds(160, 80, 320, 480);
                hp.centerPanel.add(waktuPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();

            }
        });
        memasakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Kompor> itemChecker = new ItemChecker<>(Kompor.class);
                Kompor kompor = itemChecker.checkItem();
                if (kompor == null){
                    JOptionPane.showMessageDialog(null, "Tidak ada kompor di sekitar");
                    return;
                }
                MasakPanel masakPanel = new MasakPanel(hp, kompor);
                masakPanel.setBounds(0, 0, hp.centerPanel.getWidth(), hp.centerPanel.getHeight()*3/4);
                hp.centerPanel.add(masakPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();

            }
        });
        mandiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<BakMandi> itemChecker = new ItemChecker<>(BakMandi.class);
                BakMandi bakMandi = itemChecker.checkItem();
                if (bakMandi == null){
                    itemChecker.showNonExistent();
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, bakMandi, "mandi");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();

            }
        });
        cuciTanganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Wastafel> itemChecker = new ItemChecker<>(Wastafel.class);
                Wastafel wastafel = itemChecker.checkItem();
                if (wastafel == null){
                    itemChecker.showNonExistent();
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, wastafel, "cuci tangan");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        sikatGigiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Wastafel> itemChecker = new ItemChecker<>(Wastafel.class);
                Wastafel wastafel = itemChecker.checkItem();
                if (wastafel == null){
                    itemChecker.showNonExistent();
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, wastafel, "sikat gigi");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });

        nontonTVButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<TV> itemChecker = new ItemChecker<>(TV.class);
                TV tv = itemChecker.checkItem();
                if (tv == null){
                    itemChecker.showNonExistent();
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, tv, "nonton tv");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        membersihkanKasurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //cek apakah ada kasur di sekitar sim
                ItemChecker<Kasur> itemChecker = new ItemChecker<>(Kasur.class);
                Kasur kasur = itemChecker.checkItem();
                if (kasur == null){
                    JOptionPane.showMessageDialog(null, "Tidak ada kasur di sekitar");
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, kasur,"bersih kasur");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        berkunjungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BerkunjungPanel berkunjungPanel = new BerkunjungPanel(hp);
                hp.centerPanel.add(berkunjungPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        kerjainTubesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Komputer> itemChecker = new ItemChecker<>(Komputer.class);
                Komputer komputer = itemChecker.checkItem();
                if (komputer == null){
                    itemChecker.showNonExistent();
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, komputer,"kerjain tubes");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        mainGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Komputer> itemChecker = new ItemChecker<>(Komputer.class);
                Komputer komputer = itemChecker.checkItem();
                if (komputer == null){
                    itemChecker.showNonExistent();
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, komputer,"bermain game");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        siramToiletButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ItemChecker<Toilet> itemChecker = new ItemChecker<Toilet>(Toilet.class);
                Toilet toilet = itemChecker.checkItem();
                if (toilet ==  null){
                    JOptionPane.showMessageDialog(null, "Tidak ada WC di sekitar");
                    return;
                }
                AksiAktifPanel aksiAktifPanel = new AksiAktifPanel(hp, toilet, "siram WC");
                hp.centerPanel.add(aksiAktifPanel, 0);
                hp.centerPanel.remove(ActionPanel.this);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
        pulangButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Yakin ingin pulang ke rumah?"
                        , "Pulang", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (result == JOptionPane.OK_OPTION){
                    //cek apakah dia udah di rumahnya sendiri
                    try {
                        if (hp.selectedSim.sim.getKepemilikanRumah().equals(hp.rumah)){
                            JOptionPane.showMessageDialog(null, "ngapain pulang, kan udah di rumah"
                                    , "what's wrong with you", JOptionPane.PLAIN_MESSAGE);
                            hp.centerPanel.remove(ActionPanel.this);
                            return;
                        }
                        int waktu = (int)Math.sqrt(Math.pow((hp.selectedSim.sim.getKepemilikanRumah().getLokasi().getX() - hp.rumah.getLokasi().getX()), 2)
                                + Math.pow((hp.selectedSim.sim.getKepemilikanRumah().getLokasi().getY() - hp.rumah.getLokasi().getY()), 2));
                        ThreadAksi threadAksi = new ThreadAksi("pulang", waktu, hp.rumah.world);
                        TimerAksiPanel timerAksiPanel = new TimerAksiPanel(hp, "Pulang", threadAksi);
                        hp.centerPanel.add(timerAksiPanel, 0);
                        hp.selectedSim.sim.pulang(hp.rumah.getSim());
                        hp.rumah.world.setThreadAksi(threadAksi);
                        timerAksiPanel.startThread();
                        threadAksi.startThread();
                        hp.centerPanel.remove(ActionPanel.this);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        gantiPekerjaanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GantiKerjaPanel gantiKerjaPanel = new GantiKerjaPanel(hp.selectedSim.sim);
                hp.centerPanel.add(gantiKerjaPanel, 0);
                hp.centerPanel.revalidate();
                hp.centerPanel.repaint();
            }
        });
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
    private class ItemChecker<T extends Perabotan>{
        private final Class<T> tClass;
        ItemChecker(Class<T> tClass){
            if (tClass == null){
                throw new NullPointerException();
            }
            this.tClass = tClass;
        }

        public T checkItem(){
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= 1; j++){
                    Point thisPoint = new Point(hp.rumah.getSim().getPosisi().x +i, hp.rumah.getSim().getPosisi().y + j);
                    for (Perabotan perabotan : hp.rumah.getSim().getLocRuang().getDaftarObjek()){
                        T t;
                        try {
                            t = tClass.cast(perabotan);
                        }catch (RuntimeException e){
                            continue;
                        }
                        Point testPoint = new Point(thisPoint);
                        testPoint.translate(-perabotan.getKiriAtas().x, -perabotan.getKiriAtas().y);
                        if ((testPoint.getX() >= 0 && testPoint.getX() <= perabotan.getDimensi().width) &&
                                (testPoint.getY() >= 0 && testPoint.getY() <= perabotan.getDimensi().height)){
                            return t;
                        }
                    }
                }
            }
            return null;
        }

        public void showNonExistent(Perabotan perabotan){
            JOptionPane.showMessageDialog(null, "Tidak ada "+perabotan.getNama() + " di sekitar");
        }

        /**
         * memunculkan error message yaitu perabotan tidak ada di sekitar sim yang ditunjuk
         */
        public void showNonExistent(){
            JOptionPane.showMessageDialog(null, "Tidak ada "+tClass.getSimpleName() + " di sekitar");
        }
    }

    public class DurasiPanel extends JPanel{
        DurasiPanel(){
          setLayout(new GridLayout(0, 1));
          setBounds((hp.centerPanel.getWidth()-320)/2, (hp.centerPanel.getHeight()-160)/2, 320, 160);
          setPreferredSize(new Dimension(320, 160));
          setBackground(new Color(150, 178, 102));
          setOpaque(true);
          JTextField timeInput = new JTextField();
          IntegerFilter _if = new IntegerFilter();
          ((AbstractDocument) timeInput.getDocument()).setDocumentFilter(_if);
          JLabel label = new JLabel("Masukkan durasi aksi: ");
          add(label);
          add(timeInput);
          JButton button = new JButton("OK");
          add(button);
        }
    }
}

