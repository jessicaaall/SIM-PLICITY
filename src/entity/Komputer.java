package entity;

import java.util.*;

public class Komputer extends Perabotan {
    public Komputer() {
        super(9);
    }
    public Komputer(Ruangan ruangan){
        super(9, ruangan);
    }

    public void mainGame(Sim sim, int durasi) {
        /*
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.print("Masukkan durasi bermain game dalam satuan detik >> ");
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
                    if (currentTime - startTime >= 20000) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() + 5);
                        sim.setKekenyangan(sim.getKekenyangan() - 2);
                    }
                    currentTime = System.currentTimeMillis();
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai bermain game.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
         */

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long currentTime = startTime;
                long endTime = startTime + (durasi*1000);
                while (currentTime <= endTime+500) {
                    if (currentTime - startTime >= 20000) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() + 5);
                        sim.setKekenyangan(sim.getKekenyangan() - 2);
                    }
                    currentTime = System.currentTimeMillis();
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai bermain game.");
            }
        });
        thread.start();

    }

    public void ngerjainTubes(Sim sim, int durasi) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // Random rand = new Random();
                // int durasi = rand.nextInt((5*60 - 20) + 1) + 20;
                long startTime = System.currentTimeMillis();
                long currentTime = startTime;
                long endTime = startTime + (durasi*1000);
                while (currentTime <= endTime+500) {
                    if (currentTime - startTime >= 20000) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() - 6);
                        sim.setKekenyangan(sim.getKekenyangan() - 6);
                        sim.setKesehatan(sim.getKesehatan() - 2);
                    }
                    currentTime = System.currentTimeMillis();
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai mengerjakan tubes.");
            }
        });
        thread.start();
    }
}
