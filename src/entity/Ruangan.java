package entity;

import tiles.Tile;
import tiles.TileManager;

import java.util.*;
import java.awt.*;
import java.io.Serializable;

public class Ruangan implements Serializable {
    private String nama;
    private ArrayList<Perabotan> daftarObjek;
    private ArrayList<Sim> daftarSim;
    private Rumah infoRumah;
    private Map<String, Ruangan> samping;
    private Dimension dimensi;
    private Point posisi = new Point();

    public Ruangan(String nama,Rumah rumah, Point posisi){
        this.nama = nama;
        infoRumah = rumah;
        this.posisi = posisi;
        daftarSim = new ArrayList<Sim>();
        daftarObjek = new ArrayList<Perabotan>();
        samping = new HashMap<String,Ruangan>();
        dimensi = new Dimension(6, 6);
        samping.put("Kanan",null);
        samping.put("Kiri",null);
        samping.put("Atas",null);
        samping.put("Bawah",null);
    }

    public Ruangan(String nama,Rumah rumah, Point posisi, Ruangan sampingRuang, String posisiSamping){
        this.nama = nama;
        infoRumah = rumah;
        this.posisi = posisi;
        daftarSim = new ArrayList<Sim>();
        daftarObjek = new ArrayList<Perabotan>();
        samping = new HashMap<String,Ruangan>();
        dimensi = new Dimension(6, 6);
        samping.put("Kanan",null);
        samping.put("Kiri",null);
        samping.put("Atas",null);
        samping.put("Bawah",null);
        setSamping(posisiSamping, sampingRuang);
    }

    
    public String getNama(){
        return nama;
    }

    public Dimension getDimensi(){
        return dimensi;
    }

    public Rumah getInfoRumah(){
        return infoRumah;
    }

    public Point getPosisi(){
        return posisi;
    }

    public ArrayList<Perabotan> getDaftarObjek(){
        return daftarObjek;
    }

    public ArrayList<Sim> getDaftarSim(){
        return daftarSim;
    }

    public Ruangan getSamping(String sampingRuang){
        return samping.get(sampingRuang);
    }

    public void setSamping(String sampingRuang,Ruangan ruang){
        samping.put(sampingRuang, ruang);
    }

    public Map getDaftarSamping(){
        return samping;
    }

    public Objek getObjek(int id){
        Boolean found = false;
        Objek objek = null;

        for(Objek objeks : daftarObjek){
            if(id == objeks.getId()){
                found = true;
                objek = objeks;
            }
        }

        if(found){
            return objek;
        }
        else{
            return null;
        }
    }

    public void insertSim (Sim sim){
        daftarSim.add(sim);
    }

    public void removeSim(Sim sim){
        for(Sim sims : daftarSim){
            if(sim.equals(sims)){
                daftarSim.remove(sim);
            }
        }
    }

    public int luas(){
        int widthRuang = (int)this.getDimensi().getWidth();
        int heightRuang = (int)this.getDimensi().getHeight();
        int luas = widthRuang * heightRuang;

        for(Perabotan barang : daftarObjek){
            int widthbarang = (int)barang.getDimensi().getWidth();
            int heightbarang = (int)barang.getDimensi().getHeight();
            int luasBarang = widthbarang * heightbarang;
            luas = luas - luasBarang;

        }

        return luas;
    }

    public void taruh(Perabotan barang){
        int luasruang = luas();
        int widthbarang = (int)barang.getDimensi().getWidth();
        int heightbarang = (int)barang.getDimensi().getHeight();
        int luasBarang = heightbarang * widthbarang;

        if(luasruang >= luasBarang){
            daftarObjek.add(barang);
        }
        else{
            System.out.println("Ruangan sudah penuh diisi perabotan");
        }
    }

    /**
    * Menghilangkan barang dari dalam ruangan ini.
     * catatan: method ini menghapus barang saja dan tidak menyimpannya ke dalam inventory
     * @param barang : barang yang ingin dihilangkan
    * */
    public void hilangkan(Perabotan barang){
        Iterator<Perabotan> it= daftarObjek.iterator();
        while (it.hasNext()){
            Perabotan perabotan = it.next();
            if(perabotan.equals(barang)){
                it.remove();
            }
        }
    }
}
