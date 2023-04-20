package entity;

import java.util.*;

public class Sim {
    // Deklarasi Variabel
    private String namaLengkap;
    private Pekerjaan pekerjaan;
    private int uang;
    private Inventory<Objek> inventory;
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
        this.inventory = new Inventory<>();


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
    public Inventory<Objek> getInventory() {
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
        if (mood > 100) {
            mood = 100;
        }
    }
    public void setKesehatan(int newKesehatan) {
        this.kesehatan = newKesehatan;
        if (kesehatan > 100) {
            kesehatan = 100;
        }
    }
    public void setKekenyangan(int newKekenyangan) {
        this.kekenyangan = newKekenyangan;
        if (kekenyangan > 100) {
            kekenyangan = 100;
        }
    }
    public void setUang(int uang) {
        this.uang = uang;
    }
    
    // Method : Terkait Sim (Nonaksi)

    // Method : Terkait Sim (Aksi)

}
