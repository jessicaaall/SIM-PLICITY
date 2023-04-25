package entity;

import java.util.Timer;
import java.util.TimerTask;

public class Waktu {
    // Deklarasi variabel
    private World world;
    private int hariKe;
    private int sisaDetik;

    // Konstruktor
    public Waktu(World world) {
        this.hariKe = 1;
        this.sisaDetik = 10;
        this.world = world;
    }

    // Method
    public void jalankanWaktu(int lama) {
        System.out.printf("Waktu berjalan");
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = sisaDetik;
            @Override
            public void run() {

                if (counter > (sisaDetik - lama)) {
                    System.out.printf(".");
                    counter--;
                } else {
                    if (world.)
                    timer.cancel();
                    if (sisaDetik < 0) {
                        hariKe = (Math.abs(sisaDetik) / 720) + 1;
                    }
                    setWaktu(getHariKe(), sisaDetik-lama);
                }
            }
        };  
        timer.scheduleAtFixedRate(task, 0, 1000);
    }


    // Method
    public int getHariKe() {
        return hariKe;
    }
    public int getSisaDetik() {
        return sisaDetik;
    }
    public void setWaktu(int newHariKe, int newSisaDetik) {
        hariKe = newHariKe;
        sisaDetik = newSisaDetik;
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

    public static void main(String[] args) {
        Waktu waktu = new Waktu();
        waktu.jalankanWaktu(5);
        // String[] dummy = waktu.tampilkanWaktu();
        // System.out.println(dummy[0]);
        // System.out.println(dummy[1]);
        // System.out.println(dummy[2]);
    }
}
