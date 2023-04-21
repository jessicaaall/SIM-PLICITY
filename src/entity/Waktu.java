package entity;

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

    // Method lain
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
}
