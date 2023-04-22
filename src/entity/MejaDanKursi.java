package entity;

import java.util.*;

public class MejaDanKursi extends Perabotan implements BisaDiduduki {
    public MejaDanKursi() {
        super(1);
    }

    public void makan(Sim sim) {
        duduk(sim);

        List<Objek> inventoryMakanan = new ArrayList<Objek>();
        for (Objek key : sim.getInventory().getContainer().keySet()) {
            if (key instanceof BisaDimakan) {
                inventoryMakanan.add(key);
            }
        }
        if (inventoryMakanan.size() == 0) {
            System.out.println("Tidak ada makanan pada Inventory.");
        } else {
            System.out.println("Makanan yang ada di Inventory :");
            for (int i = 1; i <= inventoryMakanan.size(); i++) {
                System.out.println(String.valueOf(i) + ". " + inventoryMakanan.get(i-1).getNama());
            }
            System.out.println("Masukkan nomor makanan yang ingin dimakan.");
            Scanner sc = new Scanner(System.in);
            boolean valid = false;
            while (!valid) {
                try {
                    int noMakanan = Integer.parseInt(sc.nextLine());
                    if (noMakanan < 1 || noMakanan > inventoryMakanan.size()) {
                        throw new IllegalArgumentException("Nomor makanan harus merupakan angka 1 - " + String.valueOf(inventoryMakanan.size()) + ".");                       
                    }
                    valid = true;
                    sim.getInventory().removeItem(inventoryMakanan.get(noMakanan - 1));
                    long endTime = System.currentTimeMillis() + (30 * 1000);
                    while (System.currentTimeMillis() < endTime) {

                    }
                    if (inventoryMakanan.get(noMakanan - 1) instanceof Makanan) {
                        Makanan makanan = (Makanan) (inventoryMakanan.get(noMakanan-1));
                        makanan.dimakan(sim);
                    } else {
                        BahanMakanan bahanMakanan = (BahanMakanan) (inventoryMakanan.get(noMakanan - 1));
                        bahanMakanan.dimakan(sim);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Input tidak valid.");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            sc.close();
        }

        berdiri(sim);
    }

    @Override
    public void duduk(Sim sim) {
        sim.setIsDuduk(true);
    }

    public void berdiri(Sim sim) {
        sim.setIsDuduk(false);
    }
}
