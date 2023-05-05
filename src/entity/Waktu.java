package entity;

import thread.ThreadAksiPasif;

import java.io.Serializable;
import java.util.Iterator;

public class Waktu implements Runnable, Serializable {
    // Deklarasi variabel
    private World world;
    private int hariKe;
    private int sisaDetik;

    private final int developerModeTime = 60;
    private transient Thread waktuThread;

    // Konstruktor
    public Waktu(World world) {
        this.hariKe = 1;
        this.world = world;
        if (world.developerMode){
            this.sisaDetik = developerModeTime;
        }
        else{
            this.sisaDetik = 720;
        }
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
            if (world.isActive()){
                sisaDetik--;
                synchronized (this) {
                    for (ThreadAksiPasif aksiPasif : world.getListThreadAksiPasif()){
                        synchronized (this){
                            if (aksiPasif != null){
                                aksiPasif.startThread();
                            }
                        }
                    }
                    for (Sim sim : world.getDaftarSim()) {
                        if (sim != null){
                            sim.trackBuangAirSetelahMakan();
                        }
                    }
                }
                for (Sim sim : world.getDaftarSim()) {
                    synchronized (this){
                        if (sim !=  null){
                            sim.updateKondisiSim();
                        }
                    }
                }
            }
            else{
                synchronized (this) {
                    for (ThreadAksiPasif aksiPasif : world.getListThreadAksiPasif()){
                        if (aksiPasif.isStarted()){
                            synchronized (this){
                                aksiPasif.stopThread();
                            }
                        }
                    }
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if (sisaDetik <= 0) {
                hariKe++;
                System.out.println("hari berganti ke hari "+ hariKe);
                if (world.developerMode){
                    sisaDetik = developerModeTime;
                }
                else{
                    sisaDetik = 720;
                }
                //cek efek tidak tidur
                Iterator<Sim> simIterator = world.getDaftarSim().iterator();
                while (simIterator.hasNext()){
                    Sim sim = simIterator.next();
                    int patokanWaktuTidur;
                    if (world.developerMode) {
                        patokanWaktuTidur = 20;
                    } else {
                        patokanWaktuTidur = 180;
                    }
                    if (sim.getWaktuTidur() < patokanWaktuTidur){
                        sim.setIsSudahTidur(false);
                        sim.resetKondisiSim();
                    }
                }
            }
            world.cekWaktu();

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
