package entity;

public class Jam extends Perabotan {
    private Waktu waktu;

    public Jam(World world) {
        super(10);
        waktu = world.getWaktu();
    }

    public Waktu getWaktu() {
        return waktu;
    }

    public void tampilkanJam(Sim sim) {
        waktu.tampilkanWaktu();
    }
}
