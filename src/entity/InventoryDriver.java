package entity;

public class InventoryDriver {
    public static void main (String[] args) {
        Inventory<Objek> inventory = new Inventory<>();
        Objek mejaKursi = new Objek(1);
        inventory.addItem(mejaKursi);
        inventory.showItem();
    }
}
