package entity;


public class Toilet extends Perabotan implements BisaDiduduki {
    public Toilet() {
        super(4);
    }
    public Toilet(Ruangan ruangan){
        super(4, ruangan);
    }
    public void buangAir(Sim sim) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                duduk(sim);
                long startTime = System.currentTimeMillis();
                long endTime = startTime + (10*1000);
                sim.setIsSudahBuangAir(true);
                while (System.currentTimeMillis() <= endTime) {

                }
                sim.setKekenyangan(sim.getKekenyangan() - 20);
                sim.setMood(sim.getMood() + 10);
                System.out.println("Sim " + sim.getNamaLengkap() + " selesai buang air.");
                berdiri(sim);
            }
        });
        thread.start();

    }

    public void siramToilet(Sim sim) {
        Thread thread = new Thread (new Runnable() {
            @Override
            public void run() {
                long endTime = System.currentTimeMillis() + (3*1000);
                while (System.currentTimeMillis() <= endTime) {

                }
                sim.setMood(sim.getMood() + 1);
                sim.setKesehatan(sim.getKesehatan() + 1);
                System.out.println("Sim " + sim.getNamaLengkap() + " telah menyiram toilet.");
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

