package entity;

import tiles.Tile;

import java.awt.*;
import java.util.*;

public class Ruangan {
    private Rumah infoRumah;
    private Point posisi;
    private ArrayList<Objek> daftarObjek;
    private Tile tileRuangan;
    private Map<String, Boolean> samping;
    public Tile getTileRuangan() {
        return tileRuangan;
    }

    public void setTileRuangan(Tile tileRuangan) {
        this.tileRuangan = tileRuangan;
    }



    private Dimension dimensi;
    public Ruangan(Rumah infoRumah, Point posisi){
        this.infoRumah = infoRumah;
        this.posisi = posisi;
        daftarObjek = new ArrayList<>();
        dimensi = new Dimension(6,6);
        samping = new HashMap<String, Boolean>();

    }
    public Rumah getRumah(){
        return infoRumah;
    }

    public Point getPosisi() {
        return posisi;
    }

    public ArrayList<Objek> getDaftarObjek() {
        return daftarObjek;
    }
    public Dimension getDimensi() {
        return dimensi;
    }
}
