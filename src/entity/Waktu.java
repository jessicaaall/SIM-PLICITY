package entity;

public class Waktu implements Runnable {
    // Deklarasi variabel
    private World world;
    private int hariKe;
    private int sisaDetik;

    private Thread waktuThread;

    // Konstruktor
    public Waktu(World world) {
        this.hariKe = 1;
        this.sisaDetik = 720;
        this.world = world;
    }

    public void startThread(){
        waktuThread = new Thread(this);
        waktuThread.start();
    }

    // Method
    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (world.getListThreadAksi().size() != 0) {
            sisaDetik--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                
            }
        } 
        if (sisaDetik < 0) {
            hariKe++;
            sisaDetik = 720;
        }
    }

    // Method
    public int getHariKe() {
        return hariKe;
    }
    public int getSisaDetik() {
        return sisaDetik;
    }
    public String[] tampilkanWaktu() {
        String[] nilaiWaktu = new String[3];
        nilaiWaktu[0] = "Waktu dunia Sim-Plicity :";
        nilaiWaktu[1] = "- Hari ke    : " + hariKe;
        nilaiWaktu[2] = "- Sisa detik : " + sisaDetik;
        return nilaiWaktu;
    }
    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }
}
