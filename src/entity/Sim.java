package entity;

import java.util.*;

public class Sim {
    // Deklarasi Variabel
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory inventory;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private String status;
    private Ruangan lokasi;
    private boolean isDuduk;
    private ArrayList<Rumah> kepemilikanRumah;
    private int waktuTidur;

    // Konstruktor
    public Sim(String namaLengkap) {
        // Berdasarkan parameter
        this.namaLengkap = namaLengkap;
        
        // Tidak berdasarkan parameter
        this.kekenyangan = 80;
        this.mood = 80;
        this.kesehatan = 80;
        this.uang = 100;
        //this.pekerjaan = ;
        this.status = "";
        //this.inventory = ;


    }


    // Method : Getter
    public String getNamaLengkap() {
        return namaLengkap;
    }
    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }
    public int getUang() {
        return uang;
    }
    public Inventory getInventory() {
        return inventory;
    }
    public int getKekenyangan() {
        return kekenyangan;
    }
    public int getMood() {
        return mood;
    }
    public int getKesehatan() {
        return kesehatan;
    }
    public String getStatus() {
        return status;
    }
    public Ruangan getLokasi() {
        return lokasi;
    }
    public boolean getIsDuduk() {
        return isDuduk;
    }
    public ArrayList<Rumah> getKepemilikanRumah() {
        return kepemilikanRumah;
    }
    public int getWaktuTidur() {
        return waktuTidur;
    }

    // Method : Setter
    public void setMood(int newMood) {
        this.mood = newMood;
    }
    public void setKesehatan(int newKesehatan) {
        this.kesehatan = newKesehatan;
    }
    public void setKekenyangan(int newKekenyangan) {
        this.kekenyangan = newKekenyangan;
    }
    
    // Method : Terkait Sim (Nonaksi)

    // Method : Terkait Sim (Aksi)

}
