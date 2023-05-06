package entity;



public class MejaDanKursi extends Perabotan implements BisaDiduduki {
    public MejaDanKursi() {
        super(1);
    }
    public MejaDanKursi(Ruangan ruangan){
        super(1, ruangan);
    }

    public void makan(Sim sim, BisaDimakan makanan) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                duduk(sim);
                Objek food = (Objek) makanan;
                if (sim.getInventory().checkItem(food)) {
                    sim.getInventory().removeItem(food);
                    long endTime = System.currentTimeMillis() + (30 * 1000);
                    while (System.currentTimeMillis() <= endTime+500) {

                    }
                    makanan.dimakan(sim);
                    sim.addTimerTerakhirMakan();
                    sim.setIsSudahBuangAir(false);
                    System.out.println("Sim " + sim.getNamaLengkap() + " selesai makan " + food.getNama() + ".");
                } else {
                    System.out.println("Makanan " + food.getNama() + " tidak ada pada Inventory Sim " + sim.getNamaLengkap() + ".");
                }
                berdiri(sim);
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
