package entity;

import java.util.Timer;
import java.util.TimerTask;

public class Waktu {
    // Deklarasi variabel
    private static Waktu instanceWaktu;
    private int hariKe;
    private int sisaDetik;

    // Konstruktor (memakai pattern singeton)
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
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            int counter = sisaDetik;
            @Override
            public void run() {
                if (counter > lama) {
                    if (counter > 0) {
                        System.out.println(counter + "Detik");
                        counter--;
                    } else {
                        System.out.println("Kelar");
                        timer.cancel();
                    }
                    setWaktu(getHariKe(), counter-lama);
                } else {

                }
            }
        };  
        timer.schedule(task, 0);
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
    public void tampilkanWaktu() {
        System.out.println("Waktu dunia Sim-Plicity :");
        System.out.println("- Hari ke    : " + hariKe);
        System.out.println("- Sisa detik : " + sisaDetik);
    }

    public static void main(String args[]) {
        Waktu.getInstanceWaktu();
        jalankanWaktu(10);
    }
}
