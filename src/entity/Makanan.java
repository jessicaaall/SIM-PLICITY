package entity;

import java.util.ArrayList;

public class Makanan extends Objek implements BisaDimakan {
    private ArrayList<BahanMakanan> resep;
    private int poinKekenyangan;
    
    public Makanan(String nama) {
        super(nama);
        resep = new ArrayList<BahanMakanan>();
        if (nama.equals("nasi ayam")) {
            resep.add(new BahanMakanan("nasi"));
            resep.add(new BahanMakanan("ayam"));
            this.poinKekenyangan = 16;
        } else if (nama.equals("nasi kari")) {
            resep.add(new BahanMakanan("nasi"));
            resep.add(new BahanMakanan("kentang"));
            resep.add(new BahanMakanan("wortel"));
            resep.add(new BahanMakanan("sapi"));
            this.poinKekenyangan = 30;
        } else if (nama.equals("susu kacang")) {
            resep.add(new BahanMakanan("susu"));
            resep.add(new BahanMakanan("kacang"));
            this.poinKekenyangan = 5;
        } else if (nama.equals("tumis sayur")) {
            resep.add(new BahanMakanan("wortel"));
            resep.add(new BahanMakanan("bayam"));
            this.poinKekenyangan = 5;
        } else if (nama.equals("bistik")) {
            resep.add(new BahanMakanan("kentang"));
            resep.add(new BahanMakanan("sapi"));
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
 