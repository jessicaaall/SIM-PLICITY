package entity;

import java.util.*;

public class Toilet extends Perabotan implements BisaDiduduki {
    public Toilet() {
        super(4);
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

    public void mandi(Sim sim) {
        System.out.print("Masukkan durasi mandi dalam satuan detik >> ");
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            try {
                int durasi = Integer.parseInt(sc.nextLine());
                if (durasi <= 0) {
                    throw new IllegalArgumentException("Durasi harus lebih besar dari 0 detik.");
                }
                valid = true;
                long startTime = System.currentTimeMillis();
                long currentTime = startTime;
                long endTime = startTime + (durasi*1000);
                while (currentTime < endTime) {
                    if (currentTime - startTime >= 15000) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() + 5);
                        sim.setKekenyangan(sim.getKekenyangan() - 1);
                        sim.setKesehatan(sim.getKesehatan() + 3);
                    }
                    currentTime = System.currentTimeMillis();
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai mandi.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
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


    @Override
    public void duduk(Sim sim) {
        sim.setIsDuduk(true);
    }

    @Override
    public void berdiri(Sim sim) {
        sim.setIsDuduk(false);
    }
}

