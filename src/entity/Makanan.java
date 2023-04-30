package entity;

import java.util.ArrayList;

public class Makanan extends Objek implements BisaDimakan {
    private ArrayList<BahanMakanan> resep;
    private int poinKekenyangan;

    public final static Makanan[] daftarMakanan = {
            new Makanan(19),
        new Makanan(20),
        new Makanan(21),
        new Makanan(22),
            new Makanan(23)
    };
    
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
        sim.setKekenyangan(sim.getKekenyangan() + poinKekenyangan);
    }

    public String toStringResep(){
        String res = "";
        for (int i = 0; i < resep.size()-1; i++){
            res += resep.get(i).getNama();
            res += ", ";
        }
        res += resep.get(resep.size()-1);
        return  res;
    }
}
 