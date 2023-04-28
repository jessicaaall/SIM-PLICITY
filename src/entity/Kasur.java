package entity;

import java.util.*;

public class Kasur extends Perabotan implements BisaDiduduki {
    private String tipe;

    public Kasur(int id) {
        super(id);
        if (id == 5) {
            tipe = "single";
        } else if (id == 6) {
            tipe = "queen size";
        } else if (id == 7) {
            tipe = "king size";
        }
    }

    public Kasur(int id, Ruangan ruangan){
        this(id);
        this.setRuangan(ruangan);
    }

    public String getTipe() {
        return tipe;
    }
    
    public void tidur(int durasi, Sim sim) {
        /*
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            //System.out.print("Masukkan durasi tidur dalam satuan detik >> ");
            try {
                //int durasi = Integer.parseInt(sc.nextLine());
                if (durasi <= 0) {
                    throw new IllegalArgumentException("Durasi tidur harus lebih besar dari 0 detik.");
                }
                valid = true;
                long startTime = System.currentTimeMillis();
                long currentTime = startTime;
                long endTime = startTime + (durasi*1000);
                while (currentTime < endTime) {
                    if (currentTime - startTime >= 240000) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() + 30);
                        sim.setKesehatan(sim.getKesehatan() + 20);
                    }
                    currentTime = System.currentTimeMillis();
                    sim.setWaktuTidur(sim.getWaktuTidakTidur() + 1);
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai tidur.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        */

        try {
            if (durasi <= 0) {
                throw new IllegalArgumentException("Durasi tidur harus lebih besar dari 0 detik.");
            }
            long startTime = System.currentTimeMillis();
            long currentTime = startTime;
            long endTime = startTime + (durasi*1000);
            while (currentTime < endTime) {
                if (currentTime - startTime >= 240000) {
                    startTime = currentTime;
                    sim.setMood(sim.getMood() + 30);
                    sim.setKesehatan(sim.getKesehatan() + 20);
                }
                currentTime = System.currentTimeMillis();
                sim.setWaktuTidur(sim.getWaktuTidakTidur() + 1);
            }
            System.out.println("Sim " + sim.getNamaLengkap() + " selesai tidur.");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void membersihkanKasur(Sim sim) {
        Random rand = new Random();
        int durasi = rand.nextInt((90 - 20) + 1) + 20;
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        long endTime = startTime + (durasi*1000);
        while (currentTime < endTime) {
            if (currentTime - startTime >= 20000) {
                startTime = currentTime;
                sim.setMood(sim.getMood() - 2);
                sim.setKekenyangan(sim.getKekenyangan() - 4);
                sim.setKesehatan(sim.getKesehatan() + 2);
            }
            currentTime = System.currentTimeMillis();
        }
        System.out.println("Sim " + sim.getNamaLengkap() + " selesai membersihkan kasur.");
    }

    @Override
    public void duduk(Sim sim) {
        sim.setIsDuduk(true);
    }

    @Override
    public void berdiri(Sim sim) {
        sim.setIsDuduk(false);
    }
}
