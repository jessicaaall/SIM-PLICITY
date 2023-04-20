package entity;

import java.util.Scanner;

public class BahanMakanan extends Objek implements BisaDimakan, BisaDibeli {
    private int harga;
    private int poinKekenyangan;

    public BahanMakanan(String nama) {
        super(nama);
        if (nama.equals("nasi")) {
            this.harga = 5;
            this.poinKekenyangan = 5;
        } else if (nama.equals("kentang")) {
            this.harga = 3;
            this.poinKekenyangan = 4;
        } else if (nama.equals("ayam")) {
            this.harga = 10;
            this.poinKekenyangan = 8;
        } else if (nama.equals("sapi")) {
            this.harga = 12;
            this.poinKekenyangan = 15;
        } else if (nama.equals("wortel")) {
            this.harga = 3;
            this.poinKekenyangan = 2;
        } else if (nama.equals("bayam")) {
            this.harga = 3;
            this.poinKekenyangan = 2;
        } else if (nama.equals("kacang")) {
            this.harga = 2;
            this.poinKekenyangan = 2;
        } else if (nama.equals("susu")) {
            this.harga = 2;
            this.poinKekenyangan = 1;
        }
    }

    public int getHarga() {
        return harga;
    }

    public int getPoinKekenyangan() {
        return poinKekenyangan;
    }

    @Override
    public void beli(Sim sim) {
        Scanner scanner = new Scanner(System.in);
        int kuantitas = Integer.parseInt(scanner.nextLine());
        int hargaTotal = kuantitas*this.getHarga();
        if (sim.getUang() < hargaTotal) {
            System.out.println("Uang tidak cukup");
        } else {
            sim.setUang(sim.getUang()-hargaTotal);
            sim.getInventory().addItem(this);            
        }
        scanner.close();
    }

    @Override
    public void dimakan(Sim sim) {
        if (sim.getInventory().getContainer().containsKey(this)) {
            sim.setKekenyangan(sim.getKekenyangan()+poinKekenyangan);
            sim.getInventory().removeItem(this);
        } else {
            System.out.println("Bahan tidak ada di inventory");
        }
        
    }
    
}
