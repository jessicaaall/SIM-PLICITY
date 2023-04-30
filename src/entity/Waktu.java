package entity;

import java.io.Serializable;

public class Waktu implements Runnable, Serializable {
    // Deklarasi variabel
    private World world;
    private int hariKe;
    private int sisaDetik;

    private transient Thread waktuThread;

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
        System.out.println("waktu started");
        while (waktuThread != null){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if (world.isActive || world.getThreadAksi() != null) {
                sisaDetik--;
                for (Sim sim : world.getDaftarSim()) {
                    sim.trackBuangAirSetelahMakan();
                }
            }
            if (sisaDetik < 0) {
                hariKe++;
                sisaDetik = 720;
            }
            for (Sim sim : world.getDaftarSim()) {
                sim.updateKondisiSim();
            }

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
        nilaiWaktu[0] = "WAKTU DUNIA SIM-PLICITY";
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
