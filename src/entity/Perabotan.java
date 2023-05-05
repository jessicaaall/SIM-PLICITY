package entity;

import java.awt.*;

public class Perabotan extends Objek implements BisaDibeli {
    private Point kiriAtas;

    public Ruangan getRuangan() {
        return ruangan;
    }

    public void setRuangan(Ruangan ruangan) {
        this.ruangan = ruangan;
    }

    private Ruangan ruangan;

    public void setDimensi(Dimension dimensi) {
        this.dimensi = dimensi;
    }

    private Dimension dimensi;
    private boolean dipakai;


    public Perabotan(int id) {
        super(id);
        if (id == 5) {
            dimensi = new Dimension(4, 1);
            harga = 50;
        } else if (id == 6) {
            dimensi = new Dimension(4, 2);
            harga = 100;
        } else if (id == 7) {
            dimensi = new Dimension(5, 2);
            harga = 150;
        } else if (id == 4) {
            dimensi = new Dimension(1, 1);
            harga = 50;
        } else if (id == 2) {
            dimensi = new Dimension(2, 1);
            harga = 100;
        } else if (id == 3) {
            dimensi = new Dimension(1, 1);
            harga = 200;
        } else if (id == 1) {
            dimensi = new Dimension(3, 3);
            harga = 50;
        } else if (id == 10) {
            dimensi = new Dimension(1, 1);
            harga = 10;
        } else if (id == 8) {
            dimensi = new Dimension(3, 1);
            harga = 70;
        } else if (id == 9) {
            dimensi = new Dimension(2, 1);
            harga = 90;
        } else if (id == 35) {
            dimensi = new Dimension(2, 1);
            harga = 50;
        } else if (id == 36) {
            dimensi = new Dimension(1, 1);
            harga = 20;
        }
        kiriAtas = null;
        dipakai = false;
    }
    public Perabotan(int id, Ruangan ruangan){
        this(id);
        this.ruangan = ruangan;
    }

    public Point getKiriAtas() {
        return kiriAtas;
    }

    public Dimension getDimensi() {
        return dimensi;
    }

    public int getHarga() {
        return harga;
    }

    public boolean getDipakai() {
        return dipakai;
    }

    public void setKiriAtas(Point p) {
        kiriAtas = p;
    }

    public void setDipakai(boolean dipakai) {
        this.dipakai = dipakai;
    }


    @Override
    public void beli(Sim sim, int totalHarga) {
        sim.getInventory().addItem(this, totalHarga/harga);
    }


}