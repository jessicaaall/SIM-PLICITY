package entity;

import java.io.Serializable;
import java.util.ArrayList;

import thread.ThreadAksi;
import thread.ThreadAksiPasif;

public class World  implements Runnable, Serializable {
    /**
     * menambahkan objek-objek apa saja yang ada di dunia
     * @return array dari Objek-objek tersebut
     */
    public Objek[] getListObjek() {
        return listObjek;
    }

    private Objek[] listObjek = {
            new MejaDanKursi(), new Kompor(2), new Kompor(3),
            new Toilet(), new Kasur(5), new Kasur(6),
            new Kasur(7), new TV(), new Komputer(),
            new Jam(this), new BahanMakanan(11), new BahanMakanan(12),
            new BahanMakanan(13), new BahanMakanan(14), new BahanMakanan(15),
            new BahanMakanan(16), new BahanMakanan(17), new BahanMakanan(18),
            new Makanan(19), new Makanan(20), new Makanan(21), new Makanan(22),
            new Makanan(23), new BakMandi(), new Wastafel()
    };
    private int width;
    private int height;
    private Sim chosenSim;

    private boolean isActive = false;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDaftarRumah(ArrayList<Rumah> daftarRumah) {
        this.daftarRumah = daftarRumah;
    }

    public void setJumlahSim(int jumlahSim) {
        this.jumlahSim = jumlahSim;
    }

    public void setDaftarSim(ArrayList<Sim> daftarSim) {
        this.daftarSim = daftarSim;
    }

    public Sim getChosenSim() {
        return chosenSim;
    }
    public void setChosenSim(Sim sim) {
        chosenSim = sim;
    }

    public void setWaktu(Waktu waktu) {
        this.waktu = waktu;
    }

    private ArrayList<Rumah> daftarRumah;
    private int jumlahSim;
    private ArrayList<Sim> daftarSim;

    int harike;
    private Waktu waktu;
    private int dailySimCreation;
    private transient Thread worldThread;
    private ThreadAksi threadAksi;

    public synchronized ArrayList<ThreadAksiPasif> getListThreadAksiPasif() {
        return listThreadAksiPasif;
    }

    public void setListThreadAksiPasif(ArrayList<ThreadAksiPasif> listThreadAksiPasif) {
        this.listThreadAksiPasif = listThreadAksiPasif;
    }

    private ArrayList<ThreadAksiPasif> listThreadAksiPasif;
    public boolean developerMode = false;


    /** Konstuktor
     * Konstruktor tipe World dengan default world size 65 x 65
     *
     */
    public World(){
        dailySimCreation = 1;
        height = 65; //64 + 1, karena koordinat dari x =0 hingga x = 64 -> 65 kemungkinan absis
        width = 65; //64 + 1, karena koordinat dari y =0 hingga x = 64 -> 65 kemungkinan ordinat
        daftarRumah = new ArrayList<Rumah>();
        daftarSim = new ArrayList<Sim>();
        waktu = new Waktu(this);
        harike = waktu.getHariKe();
        threadAksi = null;
        listThreadAksiPasif = new ArrayList<>();
        chosenSim = null;
    }

    public World(boolean developerMode){
        this.developerMode = developerMode;
        dailySimCreation = 1;
        height = 65; //64 + 1, karena koordinat dari x =0 hingga x = 64 -> 65 kemungkinan absis
        width = 65; //64 + 1, karena koordinat dari y =0 hingga x = 64 -> 65 kemungkinan ordinat
        daftarRumah = new ArrayList<Rumah>();
        daftarSim = new ArrayList<Sim>();
        waktu = new Waktu(this);
        harike = waktu.getHariKe();
        threadAksi = null;
        listThreadAksiPasif = new ArrayList<>();
        chosenSim = null;
    }
    public ThreadAksi getThreadAksi() {
        return threadAksi;
    }

    public void setThreadAksi(ThreadAksi threadAksi){
        this.threadAksi = threadAksi;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Rumah> getDaftarRumah() {
        return daftarRumah;
    }

    public int getJumlahSim() {
        return jumlahSim;
    }

    public ArrayList<Sim> getDaftarSim() {
        return daftarSim;
    }

    /**
     * menambahkan Sim pada dunia, sekali membuat
     * sim maka dailySimCreation akan berkurang
     * @param sim Sim yang ingin ditambahkan
     */
    public void tambahSim(Sim sim){
        daftarSim.add(sim);
        jumlahSim++;
        dailySimCreation--;
    }
    public Waktu getWaktu() {
        return waktu;
    }

    /**
     * menambahkan rumah pada dunia, juga mengecek apakah rumah tersebut sudah berada di dunia atau tidak
     * kalau sudah ada, maka penambahan tidak akan dilakukan.
     * @param rumah rumah yang ingin ditambahkan
     */
    public void tambahRumah(Rumah rumah){
        boolean ada = false;
        for (Rumah r: daftarRumah){
            if ((r.getLokasi().x == rumah.getLokasi().x) && (r.getLokasi().y == rumah.getLokasi().y)){
                ada = true;
                break;
            };
        }
        if (ada){
            System.out.println("Rumah dengan posisi "+ rumah.getLokasi().toString() + " sudah ada");
        }
        else {
            daftarRumah.add(rumah);
        }
    }

    /**
     * Mengecek ketersediaan jatah untuk penambahan Sim
     * @return apakah sudah mencapai batas atau tidak
     */
    public boolean isLimitSimCreation(){
        return dailySimCreation < 1;
    }

    @Override
    public void run() {
        while(worldThread != null){
            cekWaktu();
        }
    }

    /**
     * memanggil method ini akan menjalankan thread untuk Objek ini
     */
    public void startThread(){
        worldThread = new Thread(this);
        worldThread.start();
    }

    /**
     * memperbaharui jumlah pembuatan sim dengan mengecek keadaan waktunya.
     * jika waktu sudah berganti hari, maka dailySimCreation direset kembali
     * menjadi 1
     */
    void cekWaktu(){
        if (waktu.getHariKe() > harike){
            harike = waktu.getHariKe();
            dailySimCreation = 1;
            for (Sim sim : getDaftarSim()) {
                synchronized (waktu){
                    sim.setWaktuSetelahGantiKerja(sim.getWaktuSetelahGantiKerja() + 1);
                    if (sim.getWaktuTidur() < 180) {
                        sim.setIsSudahTidur(false);
                    }
                    sim.setWaktuTidur(0);
                }
            }
        }
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
