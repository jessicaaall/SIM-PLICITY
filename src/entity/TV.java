package entity;



public class TV extends Perabotan {
    public TV() {
        super(8);
    }
    public TV(Ruangan ruangan){
        super(8, ruangan);
    }

    public void nontonTV(Sim sim, int durasi) {
        /*
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            System.out.print("Masukkan durasi menonton TV dalam satuan detik >> ");
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
                    if (currentTime - startTime >= 30000) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() + 5);
                        sim.setKekenyangan(sim.getKekenyangan() - 2);
                    }
                    currentTime = System.currentTimeMillis();
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai menonton TV.");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
        */

        long startTime = System.currentTimeMillis();
        long currentTime = startTime;
        long endTime = startTime + (durasi*1000);
        while (currentTime < endTime) {
            if (currentTime - startTime >= 30000) {
                startTime = currentTime;
                sim.setMood(sim.getMood() + 5);
                sim.setKekenyangan(sim.getKekenyangan() - 2);
            }
            currentTime = System.currentTimeMillis();
        }
        System.out.println("Sim " + sim.getNamaLengkap() + " selesai menonton TV.");
    }
}
