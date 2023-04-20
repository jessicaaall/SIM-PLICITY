package entity;

import tiles.Tile;
import tiles.TileManager;

import java.awt.*;
import java.util.*;

public class Ruangan {
    private Rumah infoRumah;
    private Point posisi;
    private ArrayList<Objek> daftarObjek;
    private Map<String, Boolean> samping;

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
