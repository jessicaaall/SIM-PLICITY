package entity;

import java.util.ArrayList;

import thread.ThreadAksi;

public class World  implements Runnable{
    public Objek[] getListObjek() {
        return listObjek;
    }

    private Objek[] listObjek = {
            new MejaDanKursi(), new Kompor(2), new Kompor(3),
            new Toilet(), new Kasur(5), new Kasur(6),
            new Kasur(7), new TV(), new Komputer(),
            new Jam(this), new BahanMakanan(11), new BahanMakanan(12),
            new BahanMakanan(13), new BahanMakanan(14), new BahanMakanan(15),
            new BahanMakanan(16), new BahanMakanan(17), new BahanMakanan(18),
            new Makanan(19), new Makanan(20), new Makanan(21), new Makanan(22),
            new Makanan(23)
    };
    private int width;
    private int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setDaftarRumah(ArrayList<Rumah> daftarRumah) {
        this.daftarRumah = daftarRumah;
    }

    public void setJumlahSim(int jumlahSim) {
        this.jumlahSim = jumlahSim;
    }

    public void setDaftarSim(ArrayList<Sim> daftarSim) {
        this.daftarSim = daftarSim;
    }

    public void setWaktu(Waktu waktu) {
        this.waktu = waktu;
    }

    private ArrayList<Rumah> daftarRumah;
    private int jumlahSim;
    private ArrayList<Sim> daftarSim;

    int harike;
    private Waktu waktu;
    private int dailySimCreation;
    private Thread worldThread;
    private ArrayList<ThreadAksi> listThreadAksi;

    
    //pembuatan world menggunakan design pattern Singleton
    public World(){
        dailySimCreation = 1;
        height = 65; //64 + 1, karena koordinat dari x =0 hingga x = 64 -> 65 kemungkinan absis
        width = 65; //64 + 1, karena koordinat dari y =0 hingga x = 64 -> 65 kemungkinan ordinat
        daftarRumah = new ArrayList<Rumah>();
        daftarSim = new ArrayList<Sim>();
        waktu = new Waktu(this);
        harike = waktu.getHariKe();
        listThreadAksi = new ArrayList<>();
        waktu.startThread();
    }
    
    public ArrayList<ThreadAksi> getListThreadAksi() {
        return listThreadAksi;
    }

    public void setListThreadAksi(ArrayList<ThreadAksi> listThreadAksi) {
        this.listThreadAksi = listThreadAksi;
    }
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<Rumah> getDaftarRumah() {
        return daftarRumah;
    }

    public int getJumlahSim() {
        return jumlahSim;
    }

    public ArrayList<Sim> getDaftarSim() {
        return daftarSim;
    }
    public void tambahSim(Sim sim){
        daftarSim.add(sim);
        jumlahSim++;
        dailySimCreation--;

    }
    public Waktu getWaktu() {
        return waktu;
    }

    public void tambahRumah(Rumah rumah){
        boolean ada = false;
        for (Rumah r: daftarRumah){
            if ((r.getLokasi().x == rumah.getLokasi().x) && (r.getLokasi().y == rumah.getLokasi().y)){
                ada = true;
                break;
            };
        }
        if (ada){
            System.out.println("Rumah dengan posisi "+ rumah.getLokasi().toString() + " sudah ada");
        }
        else {
            daftarRumah.add(rumah);
        }
    }

    public boolean isLimitSimCreation(){
        return dailySimCreation < 1;
    }

    @Override
    public void run() {
        while(worldThread != null){
            cekWaktu();
        }
    }

    public void startThread(){
        worldThread = new Thread(this);
        worldThread.start();
    }

    private void cekWaktu(){
        if (waktu.getHariKe() > harike){
            harike = waktu.getHariKe();
            dailySimCreation = 1;
        }
    }
}
