package entity;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.*;

public class Rumah implements Serializable{
    private Point lokasi = new Point();
    private ArrayList<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    private Sim sim;

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    private World world;
    private Color color;
    public boolean busyUpgrading = false;
    public Ruangan getRuanganAcuan() {
        return ruanganAcuan;
    }

    Ruangan ruanganAcuan;

    private int jumlahRuangan;

    public Rumah(int x, int y, Sim sim, Color color, World world){
        this.world = world;
        this.sim = sim;
        this.color = color;
        lokasi.x = x;
        lokasi.y = y;
        Ruangan ruangan1 = new Ruangan("Ruangan 1",this,new Point(0,0));
        ruanganAcuan = ruangan1;
        daftarRuangan.add(ruangan1);
//            daftarRuangan.add(new Ruangan("Ruangan 2", this, new Point( 0, 6)));
        Perabotan mejakursi = new MejaDanKursi(); //cuma sample buat uji coba, nanti bakal dihilangin
        Perabotan kasur = new Kasur(5);
        Perabotan jam = new Jam(world);
        Perabotan toilet = new Toilet();
        Perabotan komporgas = new Kompor(2);
//        toilet.setKiriAtas(new Point(0, 5));
//        jam.setKiriAtas(new Point(0, 4));
//        kasur.setKiriAtas(new Point(2, 5));
//        mejakursi.setKiriAtas(new Point(0,0));//cuma sample buat uji coba, nanti bakal dihilangin
//        komporgas.setKiriAtas(new Point(4, 0));
//
//        daftarRuangan.get(0).getDaftarObjek().add(mejakursi); //cuma sample buat uji coba, nanti bakal dihilangin
//        daftarRuangan.get(0).getDaftarObjek().add(kasur);
//        daftarRuangan.get(0).getDaftarObjek().add(jam);
//        daftarRuangan.get(0).getDaftarObjek().add(toilet);
//        daftarRuangan.get(0).getDaftarObjek().add(komporgas);
        sim.getInventory().addItem(mejakursi);
        sim.getInventory().addItem(kasur);
        sim.getInventory().addItem(jam);
        sim.getInventory().addItem(toilet);
        sim.getInventory().addItem(komporgas);
        if (world.developerMode){
            sim.getInventory().addItem(new Kasur(6));
            sim.getInventory().addItem(new Kasur(7));
            sim.getInventory().addItem(new TV());
            sim.getInventory().addItem(new Komputer());
            sim.getInventory().addItem(new BahanMakanan(11), 100);
            sim.getInventory().addItem(new BahanMakanan(12), 100);
            sim.getInventory().addItem(new BahanMakanan(13), 100);
            sim.getInventory().addItem(new BahanMakanan(14), 100);
            sim.getInventory().addItem(new BahanMakanan(15), 100);
            sim.getInventory().addItem(new BahanMakanan(16), 100);
            sim.getInventory().addItem(new BahanMakanan(17), 100);
            sim.getInventory().addItem(new BahanMakanan(18), 100);
            sim.getInventory().addItem(new Makanan(19), 10);
            sim.getInventory().addItem(new Makanan(20), 10);
            sim.getInventory().addItem(new Makanan(21), 10);
            sim.getInventory().addItem(new Makanan(22), 10);
            sim.getInventory().addItem(new Makanan(23), 10);
            sim.getInventory().addItem(new BakMandi());
            sim.getInventory().addItem(new Wastafel());
        }
        jumlahRuangan = 1;
        sim.setPosisi(new Point(3,3));
    }

    public Point getLokasi(){
        return lokasi;
    }

    public  ArrayList<Ruangan> getDaftarRuangan(){
        return daftarRuangan;
    }

    public Sim getSim(){
        return sim;
    }

    public Color getColor(){
        return color;
    }

    public void addRuangan(Ruangan ruangan){
        daftarRuangan.add(ruangan);
    }

    public void removeRuangan(Ruangan ruangan){
        Boolean sama = false;
        for(Ruangan ruanganRumah: daftarRuangan){
            if(ruanganRumah == ruangan){
                sama = true;
            }
        }
        if(sama){
            daftarRuangan.remove(ruangan);
        }
        
    }

    public void showDaftarRuangan(){
        if(daftarRuangan.size() == 0){
            System.out.println("Tidak terdapat ruangan dalam rumah");
        }

        int i = 1;
        System.out.println("Berikut ruangakan yang terdapat pada rumah");
        for(Ruangan ruangan : daftarRuangan){
            System.out.println(i+". "+ruangan.getNama());
            System.out.println(ruangan.getDaftarSamping());
            i = i +1;
        }
    }

    public Ruangan getRuanganbyName(String namaRuang){
        Boolean cek = false;
        Ruangan ruang = null;
        
        for(Ruangan ruangs : daftarRuangan){
            if(namaRuang.equals(ruangs.getNama())){
                cek = true;
                ruang = ruangs;
            }
        }
         
        if(cek){
            return ruang;
        }
        else{
            return null;
        }
    }

    public Ruangan getRuanganbyPoint(Point posisi){
        Boolean cek = false;
        Ruangan ruang = null;
        
        for(Ruangan ruangs : daftarRuangan){
            if(posisi.equals(ruangs.getPosisi())){
                cek = true;
                ruang = ruangs;
            }
        }
         
        if(cek){
            return ruang;
        }
        else{
            return null;
        }
    }

 

    public void upgrade(Ruangan ruangan){
        daftarRuangan.add(ruangan);
        jumlahRuangan++;
        sim.setUang(sim.getUang()-1500);
    }

    /**
     * harusnya bisa
     * @return
     */
    public int getJumlahPerabot(){
        int jumlah = 0;
        for (Ruangan ruangan : getDaftarRuangan()){
            jumlah += ruangan.getDaftarObjek().size();
        }
        return jumlah;
    }

    public void showAllFurniture(){
        System.out.println("**====== DAFTAR OBJEK YANG ADA DI RUMAH INI ======**");
        for (Ruangan ruangan : getDaftarRuangan()){
            for (Perabotan perabotan: ruangan.getDaftarObjek()){
                System.out.printf("%s, (%d, %d), %s (%d, %d)\n", perabotan.getNama(), perabotan.getKiriAtas().x, perabotan.getKiriAtas().y, perabotan.getRuangan().getNama(), ruangan.getPosisi().x, ruangan.getPosisi().y);
            }
        }
        System.out.println("**================================================**");
    }

    @Override
    public String toString() {
        return String.format("Rumah Warna RGB(%d, %d, %d) dengan posisi di (%d, %d)", color.getRed(), color.getGreen(), color.getBlue(), getLokasi().x, getLokasi().y);
    }

    @Override
    public boolean equals(Object obj) {
        Rumah rumah = (Rumah) obj;
        return lokasi.equals(rumah.lokasi);
    }

    public void showAllSim() {
        System.out.println("===== Daftar Sim di Rumah Ini =====");
        for (Ruangan ruangan : daftarRuangan) {
            for (Sim sim : ruangan.getDaftarSim()) {
                System.out.println(sim.getNamaLengkap() + " posisinya di " + sim.getPosisi());
            }
        }
    }
}
