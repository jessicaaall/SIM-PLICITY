package entity;



public class MejaDanKursi extends Perabotan implements BisaDiduduki {
    public MejaDanKursi() {
        super(1);
    }
    public MejaDanKursi(Ruangan ruangan){
        super(1, ruangan);
    }

    public void makan(Sim sim, BisaDimakan makanan) {
        /*
        List<Objek> inventoryMakanan = new ArrayList<Objek>();
        for (Objek key : sim.getInventory().getContainer().keySet()) {
            if (key instanceof BisaDimakan) {
                inventoryMakanan.add(key);
            }
        }
        if (inventoryMakanan.size() == 0) {
            System.out.println("Tidak ada makanan pada Inventory.");
        } else {
            System.out.println("Makanan yang ada di Inventory :");
            for (int i = 1; i <= inventoryMakanan.size(); i++) {
                System.out.println(String.valueOf(i) + ". " + inventoryMakanan.get(i-1).getNama());
            }
            Scanner sc = new Scanner(System.in);
            boolean valid = false;
            while (!valid) {
                System.out.print("Masukkan nomor makanan yang ingin dimakan >> ");
                try {
                    int noMakanan = Integer.parseInt(sc.nextLine());
                    if (noMakanan < 1 || noMakanan > inventoryMakanan.size()) {
                        throw new IllegalArgumentException("Nomor makanan harus merupakan angka 1 - " + String.valueOf(inventoryMakanan.size()) + ".");                       
                    }
                    valid = true;
                    sim.getInventory().removeItem(inventoryMakanan.get(noMakanan - 1));
                    long endTime = System.currentTimeMillis() + (30 * 1000);
                    while (System.currentTimeMillis() < endTime) {

                    }
                    BisaDimakan makanan = (BisaDimakan) (inventoryMakanan.get(noMakanan-1));
                    makanan.dimakan(sim);
                    System.out.println("Sim " + sim.getNamaLengkap() + " selesai makan " + inventoryMakanan.get(noMakanan - 1).getNama() + ".");
                    sim.setWaktuTerakhirMakan(sim.getTheirWorld().getWaktu());
                    sim.setIsSudahBuangAir(false);
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            sc.close();
        }
        */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                duduk(sim);
                Objek food = (Objek) makanan;
                if (sim.getInventory().checkItem(food)) {
                    sim.getInventory().removeItem(food);
                    long endTime = System.currentTimeMillis() + (30 * 1000);
                    while (System.currentTimeMillis() < endTime) {

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
