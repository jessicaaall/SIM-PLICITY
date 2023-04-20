package entity;

import java.util.*;

public class Sim {
    // Deklarasi Variabel
    private String namaLengkap;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private int uang;
    private Pekerjaan pekerjaan;
    private StringBuffer status;
    private Inventory<Objek> inventory;
    private Ruangan lokasi;
    private boolean isDuduk;
    private ArrayList<Rumah> kepemilikanRumah;
    private int waktuTidur;

    // Objek untuk Konstruktor
    Random randPekerjaanID = new Random();
    
    // Konstruktor
    public Sim(String namaLengkap) {
        this.namaLengkap = namaLengkap;
        kekenyangan = 80;
        mood = 80;
        kesehatan = 80;
        uang = 100;
        pekerjaan = new Pekerjaan(randPekerjaanID.nextInt(10));
        status = new StringBuffer(50);
        inventory = new Inventory<Objek>();
        //lokasi = 
        isDuduk = false;
        //kepemilikanRumah = new ArrayList<Rumah>();
        //inisiasi kepemilikan rumah
        waktuTidur = 0;
    }


    // Method : Getter
    public String getNamaLengkap() {
        return namaLengkap;
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
    public int getUang() {
        return uang;
    }
    public Pekerjaan getPekerjaan() {
        return pekerjaan;
    }
    public StringBuffer getStatus() {
        return status;
    }
    public Inventory<Objek> getInventory() {
        return inventory;
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
        if (newMood > 100) {
            newMood = 100;
        } else if (newMood < 0) {
            newMood = 0;
        }
        mood = newMood;
        if (mood == 0) {
            mati();
        }
    }
    public void setKesehatan(int newKesehatan) {
        if (newKesehatan > 100) {
            newKesehatan = 100;
        } else if (newKesehatan < 0) {
            newKesehatan = 0;
        }
        kesehatan = newKesehatan;
        if (kesehatan == 0) {
            mati();
        }
    }
    public void setKekenyangan(int newKekenyangan) {
        if (newKekenyangan > 100) {
            newKekenyangan = 100;
        } else if (newKekenyangan < 0) {
            newKekenyangan = 0;
        }
        kekenyangan = newKekenyangan;
        if (kekenyangan == 0) {
            mati();
        }
    }
    public void setUang(int newUang) {
        uang = newUang;
    }
    
    // Method : Terkait Sim (Nonaksi)
    public void mati() {

    }

    // Method : Terkait Sim (Aksi)

}
