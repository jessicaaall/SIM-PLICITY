package entity;

import java.util.Scanner;

public class BahanMakanan extends Objek implements BisaDimakan, BisaDibeli {
    private int harga;
    private int poinKekenyangan;

    public BahanMakanan(String nama, int harga, int poinKekenyangan) {
        super(nama);
        this.harga = harga;
        this.poinKekenyangan = poinKekenyangan;
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
