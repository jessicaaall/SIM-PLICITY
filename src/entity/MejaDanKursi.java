package entity;

public class MejaDanKursi extends Perabotan implements BisaDiduduki {
    public MejaDanKursi() {
        super(1);
    }

    public void makan(Sim sim) {

    }

    @Override
    public void duduk(Sim sim) {
        sim.setIsDuduk(true);
    }

    public void berdiri(Sim sim) {
        sim.setIsDuduk(false);
    }
}
