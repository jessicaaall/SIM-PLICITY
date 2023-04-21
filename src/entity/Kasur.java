package entity;

public class Kasur extends Perabotan implements BisaDiduduki {
    private String tipe;

    public Kasur(int id) {
        super(id);
        if (id == 5) {
            tipe = "single";
        } else if (id == 6) {
            tipe = "queen size";
        } else if (id == 7) {
            tipe = "king size";
        }
    }

    public String getTipe() {
        return tipe;
    }
    
    public void tidur(Sim sim, int lama) {
        
    }

    @Override
    public void duduk(Sim sim) {
        
    }

    public void berdiri(Sim sim) {

    }
}
