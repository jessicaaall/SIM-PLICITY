package entity;

import tiles.Tile;
import tiles.TileManager;

import java.util.*;
import java.awt.*;

public class Ruangan {
    private String nama;
    private ArrayList<Objek> daftarObjek;
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
        daftarObjek = new ArrayList<Objek>();
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
        daftarObjek = new ArrayList<Objek>();
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

    public ArrayList<Objek> getDaftarObjek(){
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


}
