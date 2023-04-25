package entity;

import java.util.*;

public class Toilet extends Perabotan implements BisaDiduduki {
    public Toilet() {
        super(4);
    }
    public Toilet(Ruangan ruangan){
        super(4, ruangan);
    }
    public void buangAir(Sim sim) {
        duduk(sim);
        long startTime = System.currentTimeMillis();
        long endTime = startTime + (10*1000);
        while (System.currentTimeMillis() < endTime) {

        }
        sim.setKekenyangan(sim.getKekenyangan() - 20);
        sim.setMood(sim.getMood() + 10);
        System.out.println("Sim " + sim.getNamaLengkap() + " selesai buang air.");
        berdiri(sim);
    }

    public void siramToilet(Sim sim) {
        long endTime = System.currentTimeMillis() + (3*1000);
        while (System.currentTimeMillis() < endTime) {

        }
        sim.setMood(sim.getMood() + 1);
        sim.setKesehatan(sim.getKesehatan() + 1);
        System.out.println("Sim " + sim.getNamaLengkap() + " telah menyiram toilet.");
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

