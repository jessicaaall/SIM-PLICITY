package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private float uang;
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
    private Kasur kasur;
    private boolean isSudahBuangAir;
    private Waktu waktuTerakhirMakan;

    public BufferedImage getSimImage() {
        return simImage;
    }

    private BufferedImage simImage;
    
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
        isSudahTidur = false;
        waktuKerja = 0;
        waktuTidur = 0;
        waktuTidakTidur = 0;
        isSibuk = false;
        waktuSetelahGantiKerja = 0;
        isPernahGantiKerja = false;
        kasur = null;
        isSudahBuangAir = false;
        waktuTerakhirMakan = null;
        try {
            simImage = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/sim"+(new Random().nextInt(4)+1)+".png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    public float getUang() {
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
    public Waktu getWaktuTerakhirMakan() {
        return waktuTerakhirMakan;
    }

    public int getWaktuKerja(){
        return waktuKerja;
    }

    // Method : Setter
    public void setLocRuang(Ruangan newLocRuang) {
        locRuang = newLocRuang;
        newLocRuang.getDaftarSim().add(this);
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
    public void setUang(float newUang) {
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
        this.kasur = kasur;
    }
    public void setIsSudahBuangAir(boolean isSudahBuangAir) {
        this.isSudahBuangAir = isSudahBuangAir;
    }
    public void setWaktuTerakhirMakan(Waktu waktuTerakhirMakan) {
        this.waktuTerakhirMakan = waktuTerakhirMakan;
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
    public void kerja(int waktu) {
        // pekerjaan baru hanya bisa dilakuin sehari setelah pergantian pekerjaan
        // Asumsi ganti hari itu acuannya hari, bukan detik
        if (isPernahGantiKerja && waktuSetelahGantiKerja < 1) {
            System.out.println("Belum bisa ganti kerja ngab");
            return;
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
//                int siklus = 1;
//                int periodeSiklus = 30;
//                int sisaWaktu = waktu;
//                while (sisaWaktu >= 0) {
//                    sisaWaktu--;
//                    waktuKerja++;
//                    if (sisaWaktu == (waktu - (periodeSiklus * siklus))) {
//                        kekenyangan -= 10;
//                        mood -= 10;
//                        siklus++;
//                    }
//                    if (waktuKerja == 240) {
//                        uang += pekerjaan.getGaji();
//                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
                isSibuk = false;
            }
        });
        thread.start();
    }
    public void tidur(int durasi, Kasur kasur) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                isSibuk = true;
                setKasur(kasur);
                kasur.tidur(durasi, Sim.this);
//                int siklus = 1;
//                int periodeSiklus = 240;
//                int sisaWaktu = waktu;
//                while (sisaWaktu >= 0) {
//                    sisaWaktu--;
//                    waktuTidur++;
//                    if (sisaWaktu == (waktu - (periodeSiklus * siklus))) {
//                        kesehatan += 20;
//                        mood += 30;
//                        siklus++;
//                    }
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//                }
                isSibuk = false;
            }
        });
        thread.start();
    }

    public void efekTidakTidur() {
        if (waktuTidakTidur % 600 == 0) {
            kesehatan -= 5;
            mood -= 5;
        }
    }

    private Long startTime;
    public void updateKondisiSim() {
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
                        kesehatan += 5;
                        mood += 10;
                        kekenyangan -= 5;
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
    public void berkunjung(Sim sim, int waktu) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                setLocRuang(sim.getKepemilikanRumah().getRuanganAcuan());
                int siklus = 1;
                int periodeSiklus = 30;
                int sisaWaktu = waktu;
                isSibuk = true;
                while (sisaWaktu >= 0) {
                    sisaWaktu--;
                    if (sisaWaktu == (waktu - (periodeSiklus * siklus))) {
                        mood += 10;
                        kekenyangan -= 10;
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
            waktuSetelahGantiKerja = 0;
            isPernahGantiKerja = true;
        } else {
            System.out.println("Kamu belum bekerja minimal 12 menit ngab");
        }
    }

    public void trackBuangAirSetelahMakan() {
        if (waktuTerakhirMakan != null) {
            while ((selisihWaktu(theirWorld.getWaktu(), waktuTerakhirMakan) < 240) && (!isSudahBuangAir)) {

            }
            if (!isSudahBuangAir) {
                kesehatan -= 5;
                mood -= 5;
            }
            waktuTerakhirMakan = null;
            isSudahBuangAir = false;
        }
    }

    public int selisihWaktu(Waktu waktu1, Waktu waktu2) {
        int detik1 = waktu1.getHariKe() * 720 + waktu1.getSisaDetik();
        int detik2 = waktu2.getHariKe() * 720 + waktu2.getSisaDetik();
        return (Math.abs(detik1 - detik2));
    }
}
