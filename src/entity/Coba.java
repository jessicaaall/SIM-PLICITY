package entity;

public class Coba {
    public static void main(String[] args){
        World world = new World();
        Sim sim = new Sim("Ken Azizan", world);
        Rumah rumah = new Rumah(0, 0, sim, null, world);
        rumah.showDaftarRuangan();
        rumah.upgrade(sim);
        rumah.showDaftarRuangan();
        rumah.upgrade(sim);
        rumah.showDaftarRuangan();
    }
}
