package entity;

import java.util.*;

public class Sim {
    // Deklarasi Variabel
    private World theirWorld;

    public World getTheirWorld() {
        return theirWorld;
    }

    private String namaLengkap;
    private Ruangan locRuang;
    private Perabotan locPerabot;
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
    
    // Objek random untuk random pekerjaan dan perabotan
    private Random randPekerjaanID = new Random();
    
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
        pekerjaan = new Pekerjaan(randPekerjaanID.nextInt(10));
        status = "";
        inventory = new Inventory<Objek>();
        isDuduk = false;
        kepemilikanRumah = new ArrayList<Rumah>();
        isSudahTidur = false;
    }


    // Method : Getter
    public String getNamaLengkap() {
        return namaLengkap;
    }
    public Ruangan getLocRuang() {
        return locRuang;
    }
    public Perabotan getLocPerabot() {
        return locPerabot;
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
    public void setLocPerabot(Perabotan newLocPerabot) {
        locPerabot = newLocPerabot;
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
    
    // Method lain
    public void viewLokasi() {
        System.out.println(namaLengkap + " sedang berada di ruangan " + locRuang.getNama());
    }
    public void viewSimInfo() {
        System.out.println("SIM INFO");
        System.out.println("1. Nama : " + getNamaLengkap());
        System.out.println("2. Berada di " + locPerabot.getNama() + " dalam ruangan " + locRuang.getNama());
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
}
