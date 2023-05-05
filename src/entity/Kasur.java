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
        int cap;
        if (sim.getTheirWorld().developerMode){
            cap = 20;
        }
        else {
            cap = 180;
        }
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        long endTime = startTime + (durasi*1000);
        while (currentTime <= endTime) {
            if (currentTime - startTime >= 240000) {
                startTime = currentTime;
                sim.setMood(sim.getMood() + 30);
                sim.setKesehatan(sim.getKesehatan() + 20);
            }
            currentTime = System.currentTimeMillis();
            sim.setWaktuTidur(sim.getWaktuTidur() + 1);
            if (sim.getWaktuTidur() >= cap) {
                sim.setIsSudahTidur(true);
            }
        }
        System.out.println("Sim " + sim.getNamaLengkap() + " selesai tidur.");
    }

    public void membersihkanKasur(Sim sim, int durasi) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long currentTime = startTime;
                long endTime = startTime + (durasi*1000);
                while (currentTime <= endTime) {
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
        });
        thread.start();
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
