package entity;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.awt.*;

public class Sim implements Serializable {

    // Deklarasi Variabel
    private World theirWorld;
    private String namaLengkap;
    private Ruangan locRuang;
    private Point posisi;
    private int kekenyangan;
    private int mood= 80;
    private int kesehatan= 80;
    private int uang = 100;
    private Pekerjaan pekerjaan;
    private String status;
    private Inventory<Objek> inventory;
    private boolean isDuduk;
    private Rumah kepemilikanRumah;
    private boolean isSudahTidur;
    private int waktuKerja;
    private int waktuTidur;
    private int waktuTidakTidur;
    private boolean isSibuk;
    private int waktuSetelahGantiKerja;
    private boolean isPernahGantiKerja;
    private boolean isSudahBuangAir;
    private ArrayList<Integer> timerTerakhirMakan;

    /**
     * randomizer
     */
    private Random rand = new Random();

    /**
     * Konstruktor untuk Sim
     * @param namaLengkap nama sim tersebut
     * @param theirWorld mau ditaruh di dunia mana sim tersebut
     */
    public Sim(String namaLengkap, World theirWorld) {
        this.theirWorld = theirWorld;
        this.namaLengkap = namaLengkap;
        kekenyangan = 80;
        mood = 80;
        kesehatan = 80;
        uang = 100;
        rand = new Random();
        pekerjaan = new Pekerjaan(rand.nextInt(24,35));
        status = "";
        inventory = new Inventory<Objek>();
        isDuduk = false;
        isSudahTidur = true;
        waktuKerja = 0;
        waktuTidur = 0;
        waktuTidakTidur = 0;
        isSibuk = false;
        waktuSetelahGantiKerja = 0;
        isPernahGantiKerja = false;
        isSudahBuangAir = false;
        timerTerakhirMakan = new ArrayList<Integer>();
        randomSkin = new Random().nextInt(4)+1;
        if (theirWorld.developerMode){
            uang = (int)Math.pow(10, 6);
        }
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
    public Rumah getKepemilikanRumah() {
        return kepemilikanRumah;
    }
    public boolean getIsSudahTidur() { 
        return isSudahTidur;
    }
    public int getWaktuTidur() {
        return waktuTidur;
    }
    public int getWaktuTidakTidur() {
        return waktuTidakTidur;
    }
    public int getWaktuSetelahGantiKerja() {
        return waktuSetelahGantiKerja;
    }
    public boolean getIsSudahBuangAir() {
        return isSudahBuangAir;
    }
    public ArrayList<Integer> getTimerTerakhirMakan() {
        return timerTerakhirMakan;
    }

    public int getWaktuKerja(){
        return waktuKerja;
    }

    public int getRandomSkin() {
        return randomSkin;
    }

    /**
     *
     * Atribut untuk skin karakter sim. akan digenerate secara random
     */
    private int randomSkin;

    // Method : Setter
    public void setLocRuang(Ruangan newLocRuang) {
        if (locRuang != null){
            synchronized (this){
                locRuang.removeSim(this);
            }
        }
        locRuang = newLocRuang;
        synchronized (this) {
            newLocRuang.insertSim(this);
        }
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
    public void setWaktuTidur(int waktuTidur) {
        this.waktuTidur = waktuTidur;
    }
    public void setWaktuTidakTidur(int waktuTidakTidur) { this.waktuTidakTidur = waktuTidakTidur; }
    public void setKepemilikanRumah(Rumah rumah) { this.kepemilikanRumah = rumah; }
    public void setWaktuSetelahGantiKerja(int waktuSetelahGantiKerja) {
        this.waktuSetelahGantiKerja = waktuSetelahGantiKerja;
    }
    public void setKasur(Kasur kasur) {
    }
    public void setIsSudahBuangAir(boolean isSudahBuangAir) {
        this.isSudahBuangAir = isSudahBuangAir;
    }
    public void addTimerTerakhirMakan() {
        timerTerakhirMakan.add(0);
    }
    public void removeTimerTerakhirMakan(int index) {
        timerTerakhirMakan.remove(index);
    }
    public void incrementTimerTerakhirMakan(int index) {
        timerTerakhirMakan.set(index, timerTerakhirMakan.get(index)+1);
    }
    
    // Method lain

    /**
     * Memunculkan informasi sim ini
     */
    public void viewSimInfo() {
        System.out.println("SIM INFO");
        System.out.println("1. Nama : " + getNamaLengkap());
        System.out.println("2. Berada di {" + posisi.x + "," + posisi.y + "}" + " dalam ruangan " + locRuang.getNama());
        System.out.println("3. Kekenyangan : " + getKekenyangan());
        System.out.println("4. Mood : " + getMood());
        System.out.println("5. Kesehatan : " + getKesehatan());
        System.out.println("6. Uang : " + getUang());
        System.out.println("7. Pekerjaan : " + pekerjaan);
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
            System.out.println("12. Sudah tidur = yes");
        } else {
            System.out.println("12. Sudah tidur = no");
        }
        System.out.println("13. sudah ganti pekerjaan = " + isPernahGantiKerja);
        System.out.println("14. waktu bekerja = " + waktuKerja);
        System.out.println("15. Waktu tidur = " + waktuTidur);
        System.out.println("16. Waktu tidak tidur = " + waktuTidakTidur);
        System.out.println("17. Waktu setelah ganti kerja = "+waktuSetelahGantiKerja);
    }

    /**
     * melakukan kerja pada sim ini,
     * @param waktu: durasi kerja: harus kelipatan 120 (detik)
     */
    public void kerja(int waktu) throws Exception{
        // pekerjaan baru hanya bisa dilakuin sehari setelah pergantian pekerjaan
        // Asumsi ganti hari itu acuannya hari, bukan detik
        if (isPernahGantiKerja && waktuSetelahGantiKerja < 1) {
            throw new Exception("Belum bisa ganti kerja ngab");
        }
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isSibuk = true;
                try {
                    pekerjaan.lakukanKerja(waktu, Sim.this);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                isSibuk = false;
            }
        });
        thread.start();
    }

    /**
     * Meniduri Sim
     * @param durasi: waktu tidurnya
     * @param kasur: di kasur mana dia akan tidur
     */
    public void tidur(int durasi, Kasur kasur) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isSibuk = true;
                setKasur(kasur);
                kasur.tidur(durasi, Sim.this);
                isSibuk = false;
            }
        });
        thread.start();
        setWaktuTidur(getWaktuTidur() + durasi);
        if (waktuTidur >= 180) {
            setIsSudahTidur(true);
        }
    }

    /**
     * memberikan efek berjangka ketika Sim tidak tidur
     */
    public void efekTidakTidur() {
        int bagi;
        if (theirWorld.developerMode) {
            bagi = 20;
        } else {
            bagi = 600;
        }

        if (waktuTidakTidur == 0){
            return;
        }
        if (waktuTidakTidur % bagi == 0) {
            setKesehatan(getKesehatan() - 5);
            setMood(getMood() - 5);
        }
    }

    private Long startTime;

    /**
     * update kondisi ketidaktiduran sim
     */
    public void updateKondisiSim() {
//        int patokanTidakTidur;
//        if (theirWorld.developerMode) {
//            patokanTidakTidur = 20;
//        } else {
//            patokanTidakTidur = 600;
//        }
//
//        if (waktuTidakTidur >= patokanTidakTidur) {
//            setIsSudahTidur(false);
//        }

        if (!getIsSudahTidur()) {
            if (startTime == null) {
                startTime = System.currentTimeMillis();
            }
            Long currentTime = System.currentTimeMillis();
            if (currentTime - startTime >= 1000) {
                waktuTidakTidur++;
                startTime = currentTime;
            }
            efekTidakTidur();
        } else {
            startTime = null;
        }
    }

    public void resetKondisiSim() {
        setWaktuTidur(0);
        setWaktuTidakTidur(0);
    }

    public void olahraga(int waktu) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int siklus = 1;
                int periodeSiklus = 20;
                int sisaWaktu = waktu;
                isSibuk = true;
                while (sisaWaktu >= 0) {
                    sisaWaktu--;
                    if (sisaWaktu == (waktu - (periodeSiklus * siklus))) {
                        setKesehatan(getKesehatan() + 5);
                        setMood(getMood() + 10);
                        setKekenyangan(getKekenyangan() - 5);
                        siklus++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                isSibuk = false;
            }
        });
        thread.start();
    }

    /**
     *
     * @param sim sim yang akan dikunjungi
     */
    public void berkunjung(Sim sim) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int waktu = (int)Math.sqrt(Math.pow((sim.getKepemilikanRumah().getLokasi().getX() - Sim.this.getKepemilikanRumah().getLokasi().getX()), 2)
                        + Math.pow((sim.getKepemilikanRumah().getLokasi().getY() - Sim.this.getKepemilikanRumah().getLokasi().getY()), 2));
                int siklus = 1;
                int periodeSiklus = 30;
                int sisaWaktu = waktu;
                isSibuk = true;
                while (sisaWaktu >= 0) {
                    sisaWaktu--;
                    if (sisaWaktu == (waktu - (periodeSiklus * siklus))) {
                        setMood(getMood() + 10);
                        setKekenyangan(getKekenyangan() - 10);
                        siklus++;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                isSibuk = false;
            }
        });
        thread.start();
        setLocRuang(sim.getKepemilikanRumah().getRuanganAcuan());
    }

    /**
     *
     * @param sim sim yang rumahnya selesai dikunjungi
     */
    public void pulang(Sim sim) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int waktu = (int)Math.sqrt(Math.pow((sim.getKepemilikanRumah().getLokasi().getX() - Sim.this.getKepemilikanRumah().getLokasi().getX()), 2)
                        + Math.pow((sim.getKepemilikanRumah().getLokasi().getY() - Sim.this.getKepemilikanRumah().getLokasi().getY()), 2));
                int sisaWaktu = waktu;
                isSibuk = true;
                while (sisaWaktu >= 0) {
                    sisaWaktu--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                isSibuk = false;
            }
        });
        thread.start();
        setLocRuang(Sim.this.getKepemilikanRumah().getRuanganAcuan());
    }
    public void lihatInventory() {
        inventory.showItem();
    }
    public void masangBarang(Perabotan barang, Ruangan locRuang, Point posisi) {
        inventory.removeItem(barang);
    }
    public void gantiPekerjaan(Pekerjaan newPekerjaan) throws Exception{
        int timeRequired;
        if (theirWorld.developerMode){
            timeRequired = 120;
        }
        else {
            timeRequired = 720;
        }
        if (waktuKerja >= timeRequired) {
            uang -= (1/2) * newPekerjaan.getGaji(); // Bayar 1/2 dari gaji pekerjaan baru
            pekerjaan = newPekerjaan;
            setWaktuKerja(0);
            waktuSetelahGantiKerja = 0;
            isPernahGantiKerja = true;
        } else {
            throw  new Exception("Kamu belum bekerja minimal 12 menit ngab");
        }
    }

    public void trackBuangAirSetelahMakan() {
        if (timerTerakhirMakan.size() > 0) {
            if (isSudahBuangAir) {
                removeTimerTerakhirMakan(0);
                isSudahBuangAir = false;
            }
            if (timerTerakhirMakan.size() > 0) {
                for (int i = 0; i < timerTerakhirMakan.size(); i++) {
                    incrementTimerTerakhirMakan(i);
                    if (timerTerakhirMakan.get(i) >= 240) {
                        setKesehatan(getKesehatan() - 5);
                        setMood(getMood() - 5);
                        removeTimerTerakhirMakan(i);
                    }
                }
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        Sim sim = (Sim) obj;
        return namaLengkap.equals(sim.namaLengkap);
    }

    public void mati(){
        theirWorld.getDaftarRumah().removeIf(rumah -> rumah.equals(this.getKepemilikanRumah()));
        theirWorld.getDaftarSim().removeIf(sim -> sim.equals(this));
        System.out.println("dia menemui pacar 2D nya");
    }

}
