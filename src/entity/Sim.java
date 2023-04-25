package entity;

import java.util.*;
import java.awt.*;

public class Sim {
    // Deklarasi Variabel
    private World theirWorld;
    private String namaLengkap;
    private Ruangan locRuang;
    private Point posisi;
    private int kekenyangan;
    private int mood;
    private int kesehatan;
    private int uang;
    private Pekerjaan pekerjaan;
    private String status;
    private Inventory<Objek> inventory;
    private boolean isDuduk;
    private ArrayList<Rumah> kepemilikanRumah;
    private boolean isSudahTidur;
    private int waktuKerja;
    
    // Objek random untuk random apapun yang dirandom wkwkwk
    private Random rand = new Random();
    
    // Konstruktor
    public Sim(String namaLengkap, World theirWorld) {
        this.theirWorld = theirWorld;
        for (Sim sim: theirWorld.getDaftarSim()){
            if (sim.getNamaLengkap().equals(namaLengkap)){
                System.out.println("Nama sudah dipakai");
                return;
            }
        }
        this.namaLengkap = namaLengkap;
        kekenyangan = 80;
        mood = 80;
        kesehatan = 80;
        uang = 100;
        pekerjaan = new Pekerjaan(rand.nextInt(10));
        status = "";
        inventory = new Inventory<Objek>();
        isDuduk = false;
        kepemilikanRumah = new ArrayList<Rumah>();
        isSudahTidur = false;
        waktuKerja = 0;
    }
    
    
    // Method : Getter
    public World getTheirWorld() {
        return theirWorld;
    }
    public String getNamaLengkap() {
        return namaLengkap;
    }
    public Ruangan getLocRuang() {
        return locRuang;
    }
    public Point getPosisi() {
        return posisi;
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
    public String getStatus() {
        return status;
    }
    public Inventory<Objek> getInventory() {
        return inventory;
    }
    public boolean getIsDuduk() {
        return isDuduk;
    }
    public ArrayList<Rumah> getKepemilikanRumah() {
        return kepemilikanRumah;
    }
    public boolean getIsSudahTidur() { 
        return isSudahTidur;
    }

    // Method : Setter
    public void setLocRuang(Ruangan newLocRuang) {
        locRuang = newLocRuang;
    }
    public void setPosisi(Point newPosisi) {
        posisi = newPosisi;
    }
    public void setKekenyangan(int newKekenyangan) {
        if (newKekenyangan > 100) {
            newKekenyangan = 100;
        } else if (newKekenyangan < 0) {
            newKekenyangan = 0;
        }
        kekenyangan = newKekenyangan;
    }
    public void setMood(int newMood) {
        if (newMood > 100) {
            newMood = 100;
        } else if (newMood < 0) {
            newMood = 0;
        }
        mood = newMood;
    }
    public void setKesehatan(int newKesehatan) {
        if (newKesehatan > 100) {
            newKesehatan = 100;
        } else if (newKesehatan < 0) {
            newKesehatan = 0;
        }
        kesehatan = newKesehatan;
    }
    public void setUang(int newUang) {
        uang = newUang;
    }
    public void setIsDuduk(boolean newIsDuduk) {
        isDuduk = newIsDuduk;
    }
    public void setIsSudahTidur(boolean newIsSudahTidur) {
        isSudahTidur = newIsSudahTidur;
    }
    public void setWaktuKerja(int newWaktuKerja) {
        waktuKerja = newWaktuKerja;
    }
    
    // Method lain
    public void viewSimInfo() {
        System.out.println("SIM INFO");
        System.out.println("1. Nama : " + getNamaLengkap());
        System.out.println("2. Berada di {" + posisi.getX() + "," + posisi.getY() + "}" + " dalam ruangan " + locRuang.getNama());
        System.out.println("3. Kekenyangan : " + getKekenyangan());
        System.out.println("4. Mood : " + getMood());
        System.out.println("5. Kesehatan : " + getKesehatan());
        System.out.println("6. Uang : " + getUang());
        System.out.println("7. Pekerjaan : " + pekerjaan.getNamaPekerjaan());
        System.out.println("8. Status : " + getStatus());
        System.out.println("9. Inventory : ");
        inventory.showItem();
        if (getIsDuduk()) {
            System.out.println("10. Kondisi : duduk");
        } else {
            System.out.println("10. Kondisi : berdiri");
        }
        System.out.println("11. Kepemilikan rumah : " + getKepemilikanRumah());
        if (getIsSudahTidur()) {
            System.out.println("12. Selama 10 menit sudah tidur");
        } else {
            System.out.println("12. Selama 10 menit belum tidur");
        }
    }
    public void kerja(int lama) {
        // pekerjaan baru hanya bisa dilakuin sehari setelah pergantian pekerjaan
    }
    public void tidur(int lama) {
        
    }
    public void olahraga(int lama) {

    }
    public void berkunjung() {

    }
    public void upgradeRuman(Ruangan ruangan) {

    }
    public void lihatInventory() {
        inventory.showItem();
    }
    public void masangBarang(Perabotan barang, Ruangan locRuang, Point posisi) {
        inventory.removeItem(barang);
    }
    public void gantiPekerjaan(Pekerjaan newPekerjaan) {
        if (waktuKerja > 720) {
            uang -= (1/2) * newPekerjaan.getGaji(); // Bayar 1/2 dari gaji pekerjaan baru
            pekerjaan = newPekerjaan;
            setWaktuKerja(0);
        } else {
            System.out.println("Kamu belum bekerja minimal 12 menit ngab");
        }
    }
}
