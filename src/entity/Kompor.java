package entity;



public class Kompor extends Perabotan {
    private String tipe;

    public Kompor (int id) {
        super(id);
        if (id == 2) {
            tipe = "gas";
        } else if (id == 3) {
            tipe = "listrik";
        }
    }
    public Kompor(int id, Ruangan ruangan){
        this(id);
        this.setRuangan(ruangan);
    }

    public String getTipe() {
        return tipe;
    }

    public void masak(Sim sim, Makanan makanan) throws BahanKurangException {
        boolean hasAll = true;
        for (BahanMakanan bahan : makanan.getResep()) {
            if (!sim.getInventory().checkItem(bahan)) {
                hasAll = false;
                break;
            }
        }
        if (hasAll) {
            Thread thread = new Thread(() -> {
                for (BahanMakanan bahan : makanan.getResep()) {
                    sim.getInventory().removeItem(bahan);
                }
                int waktuMasak = makanan.getPoinKekenyangan() * 1500;
                long endTime = System.currentTimeMillis() + waktuMasak;
                while (System.currentTimeMillis() <= endTime+500) {

                }
                sim.setMood(sim.getMood() + 10);
                sim.getInventory().addItem(makanan);
                System.out.println("Makanan " + makanan.getNama() + " selesai dimasak oleh " + sim.getNamaLengkap() + ".");
            });
            thread.start();
        } else {
            throw new BahanKurangException(makanan);
        }
    }

    public class BahanKurangException extends Exception{
        BahanKurangException(Makanan makanan){
            super("Masak " + makanan.getNama() + " gagal. Ada bahan makanan yang tidak dimiliki.");
        }
    }
}
