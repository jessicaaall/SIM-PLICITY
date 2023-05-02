package entity;

import java.util.*;

public class BakMandi extends Perabotan {
    public BakMandi() {
        super(35);
    }
    public BakMandi(Ruangan ruangan){
        super(35, ruangan);
    }

    public void mandi(Sim sim, int duration) {
//        Scanner sc = new Scanner(System.in);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                boolean valid = false;
                while (!valid) {
                    System.out.print("Masukkan durasi mandi dalam satuan detik >> ");
                    try {
//                int durasi = Integer.parseInt(sc.nextLine());
                        int durasi = duration;
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
//        sc.close();
            }
        });
        thread.start();

    }
}
