package entity;



public class TV extends Perabotan {
    public TV() {
        super(8);
    }
    public TV(Ruangan ruangan){
        super(8, ruangan);
    }

    public void nontonTV(Sim sim, int durasi) {
        Thread thread = new Thread (new Runnable() {
            @Override
            public void run() {
                long startTime = System.currentTimeMillis();
                long currentTime = startTime;
                long endTime = startTime + (durasi*1000);
                while (currentTime <= endTime) {
                    if (currentTime - startTime >= 30000+200) {
                        startTime = currentTime;
                        sim.setMood(sim.getMood() + 5);
                        sim.setKekenyangan(sim.getKekenyangan() - 2);
                    }
                    currentTime = System.currentTimeMillis();
                }
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai menonton TV.");
            }
        });
        thread.start();
    }
}
