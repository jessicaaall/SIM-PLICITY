package entity;

import java.util.Timer;
import java.util.TimerTask;

public class Waktu {
    // Deklarasi variabel
    private static Waktu instanceWaktu;
    private int hariKe;
    private int sisaDetik;

    // Konstruktor (memakai pattern singleton)
    private Waktu() {
        this.hariKe = 1;
        this.sisaDetik = 720;
    }
    public static Waktu getInstanceWaktu() {
        if (instanceWaktu == null) {
            synchronized (Waktu.class) {
                if (instanceWaktu == null) {
                    instanceWaktu = new Waktu();
                }
            }
        }
        return instanceWaktu;
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
                    timer.cancel();
                    setWaktu(getHariKe(), sisaDetik-lama);
                    System.out.println("");
                    String[] dummy = tampilkanWaktu();
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
//        System.out.println("Waktu dunia Sim-Plicity :");
//        System.out.println("- Hari ke    : " + hariKe);
//        System.out.println("- Sisa detik : " + sisaDetik);
        String[] nilaiWaktu = new String[3];
        nilaiWaktu[0] = "Waktu dunia Sim-Plicity :";
        nilaiWaktu[1] = "- Hari ke    : " + hariKe;
        nilaiWaktu[2] = "- Sisa detik : " + sisaDetik;
        return nilaiWaktu;
    }

    public static void main(String[] args) {
        Waktu waktu = Waktu.getInstanceWaktu();
        waktu.jalankanWaktu(10);
        // String[] dummy = waktu.tampilkanWaktu();
        // System.out.println(dummy[0]);
        // System.out.println(dummy[1]);
        // System.out.println(dummy[2]);
    }
}
