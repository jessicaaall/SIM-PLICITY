package entity;

public class World {
    private int width;
    private int height;
//    private ArrayList<Rumah> daftarRumah;
    private int jumlahSim;
//    private ArrayList<Sim> daftarSim;
//    private Waktu waktu;

    //pembuatan world menggunakan design pattern Singleton
    public World(){
        height = 64;
        width = 64;
//        daftarRumah = new ArrayList<Rumah>();
//        daftarSim = new ArrayList<Sim>();
//        waktu = new Waktu();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /*public ArrayList<Rumah> getDaftarRumah() {
        return daftarRumah;
    }*/

    public int getJumlahSim() {
        return jumlahSim;
    }

   /* public ArrayList<Sim> getDaftarSim() {
        return daftarSim;
    }*/
    /*public void tambahSim(Sim sim){
        daftarSim.add(sim);
        jumlahSim++;
    }*/
    /*
    public Waktu getWaktu() {
        return waktu;
    }*/
    /*
    public void increaseWaktu() throws InterruptedException {
        waktu.setDetik(waktu.getDetik+1);
        Thread.sleep(1000);
    }*/
}
