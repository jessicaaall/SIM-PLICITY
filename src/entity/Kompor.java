package entity;

import java.util.*;

public class Kompor extends Perabotan {
    private String tipe;

    public Kompor (int id) {
        super(id);
        if (id == 2) {
            tipe = "gas";
        } else if (id == 3) {
            tipe = "listrik";
        }
    }

    public String getTipe() {
        return tipe;
    }

    public void masak(Sim sim) {
        System.out.println("===== M E N U =====");
        System.out.println("1. Nasi Ayam");
        System.out.println("2. Nasi Kari");
        System.out.println("3. Susu Kacang");
        System.out.println("4. Tumis Sayur");
        System.out.println("5. Bistik");
        System.out.println("===================");
        System.out.println("Masukkan nomor menu makanan yang ingin dimasak.");
        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        while (!valid) {
            try {
                int noMenu = Integer.parseInt(sc.nextLine());
                if (noMenu < 1 || noMenu > 5) {
                    throw new IllegalArgumentException("Nomor menu harus merupakan angka 1 - 5.");
                }
                valid = true;
                Makanan makanan = new Makanan(noMenu + 18);
                boolean hasAll = true;
                for (BahanMakanan bahan : makanan.getResep()) {
                    if (!sim.getInventory().checkItem(bahan)) {
                        hasAll = false;
                        break;
                    }
                }
                if (hasAll) {
                    for (BahanMakanan bahan : makanan.getResep()) {
                        sim.getInventory().removeItem(bahan);
                    }
                    int waktuMasak = makanan.getPoinKekenyangan() * 1500;
                    try {
                        Thread.sleep(waktuMasak);
                        sim.setMood(sim.getMood() + 10);
                        sim.getInventory().addItem(makanan);
                        System.out.println("Makanan " + makanan.getNama() + " selesai dimasak.");
                    } catch (InterruptedException e) {
                        System.out.println("Aksi masak terinterupsi.");
                    }
                } else {
                    System.out.println("Masak " + makanan.getNama() + " gagal. Ada bahan makanan yang tidak dimiliki.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        sc.close();
    }
}
