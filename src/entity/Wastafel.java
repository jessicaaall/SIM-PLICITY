package entity;

import java.util.*;

public class Wastafel extends Perabotan {
    public Wastafel() {
        super(36);
    }

    public void sikatGigi(Sim sim) {
        Random rand = new Random();
        int durasi = rand.nextInt((60 - 10) + 1) + 10;
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        long endTime = startTime + (durasi*1000);
        while (currentTime < endTime) {
            if (currentTime - startTime >= 10000) {
                startTime = currentTime;
                sim.setMood(sim.getMood() + 3);
                sim.setKesehatan(sim.getKesehatan() + 4);
            }
            currentTime = System.currentTimeMillis();
        }
        System.out.println("Sim " + sim.getNamaLengkap() + " selesai menggosok gigi.");
    }

    public void cuciTangan(Sim sim) {
        Random rand = new Random();
        int durasi = rand.nextInt((20 - 5) + 1) + 5;
        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        long endTime = startTime + (durasi*1000);
        while (currentTime < endTime) {
            if (currentTime - startTime >= 5000) {
                startTime = currentTime;
                sim.setMood(sim.getMood() + 2);
                sim.setKesehatan(sim.getKesehatan() + 3);
            }
            currentTime = System.currentTimeMillis();
        }
        System.out.println("Sim " + sim.getNamaLengkap() + " selesai mencuci tangan.");
    }
}
