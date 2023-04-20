package entity;

import java.util.ArrayList;

public class Makanan extends Objek implements BisaDimakan {
    private ArrayList<BahanMakanan> resep;
    private int poinKekenyangan;
    
    public Makanan(int id) {
        super(id);
        resep = new ArrayList<BahanMakanan>();
        if (id == 19) {
            resep.add(new BahanMakanan(11));
            resep.add(new BahanMakanan(13));
            this.poinKekenyangan = 16;
        } else if (id == 20) {
            resep.add(new BahanMakanan(11));
            resep.add(new BahanMakanan(12));
            resep.add(new BahanMakanan(15));
            resep.add(new BahanMakanan(14));
            this.poinKekenyangan = 30;
        } else if (id == 21) {
            resep.add(new BahanMakanan(18));
            resep.add(new BahanMakanan(17));
            this.poinKekenyangan = 5;
        } else if (id == 22) {
            resep.add(new BahanMakanan(15));
            resep.add(new BahanMakanan(16));
            this.poinKekenyangan = 5;
        } else if (id == 23) {
            resep.add(new BahanMakanan(12));
            resep.add(new BahanMakanan(14));
            this.poinKekenyangan = 22;
        }
    }

    public ArrayList<BahanMakanan> getResep() {
        return resep;
    }

    public int getPoinKekenyangan() {
        return poinKekenyangan;
    }

    @Override
    public void dimakan(Sim sim) {
        if (sim.getInventory().checkItem(this)) {
            sim.setKekenyangan(sim.getKekenyangan() + poinKekenyangan);
            sim.getInventory().removeItem(this);
        } else {
            System.out.println("Makanan belum dibuat oleh Sim sehingga tidak ditemukan pada Inventory");
        }
    }
}
 