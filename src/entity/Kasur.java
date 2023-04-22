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

    public String getTipe() {
        return tipe;
    }
    
    public void tidur(Sim sim, int lama) {
        
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

    public void berdiri(Sim sim) {
        sim.setIsDuduk(false);
    }
}
