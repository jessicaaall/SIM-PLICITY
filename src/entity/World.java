package entity;

import java.util.ArrayList;

public class World {
    private int width;
    private int height;

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

    public void setWaktu(Waktu waktu) {
        this.waktu = waktu;
    }

    private ArrayList<Rumah> daftarRumah;
    private int jumlahSim;
    private ArrayList<Sim> daftarSim;
    private Waktu waktu;

    //pembuatan world menggunakan design pattern Singleton
    public World(){
        height = 65; //64 + 1, karena koordinat dari x =0 hingga x = 64 -> 65 kemungkinan absis
        width = 65; //64 + 1, karena koordinat dari y =0 hingga x = 64 -> 65 kemungkinan ordinat
        daftarRumah = new ArrayList<Rumah>();
        daftarSim = new ArrayList<Sim>();
        waktu = Waktu.getInstanceWaktu();
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
    public void tambahSim(Sim sim){
        daftarSim.add(sim);
        jumlahSim++;
    }
    public Waktu getWaktu() {
        return waktu;
    }

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
}
