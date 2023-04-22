package entity;

import java.util.Scanner;

public class BahanMakanan extends Objek implements BisaDimakan, BisaDibeli {
    private int harga;
    private int poinKekenyangan;

    public BahanMakanan(int id) {
        super(id);
        if (id == 11) {
            this.harga = 5;
            this.poinKekenyangan = 5;
        } else if (id == 12) {
            this.harga = 3;
            this.poinKekenyangan = 4;
        } else if (id == 13) {
            this.harga = 10;
            this.poinKekenyangan = 8;
        } else if (id == 14) {
            this.harga = 12;
            this.poinKekenyangan = 15;
        } else if (id == 15) {
            this.harga = 3;
            this.poinKekenyangan = 2;
        } else if (id == 16) {
            this.harga = 3;
            this.poinKekenyangan = 2;
        } else if (id == 17) {
            this.harga = 2;
            this.poinKekenyangan = 2;
        } else if (id == 18) {
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
        sim.setKekenyangan(sim.getKekenyangan() + poinKekenyangan);        
    }
    
}
