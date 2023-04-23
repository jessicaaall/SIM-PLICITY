package entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T> {
    // Deklarasi Atribut
    private Map<T, Integer> container;

    // Konstruktor
    public Inventory() {
        container = new HashMap<>();
    }

    // Method
    public Map<T, Integer> getContainer() {
        return container;
    }

    public void addItem(T item) {
        if (checkItem(item)) {
            container.put(item, container.get(item) + 1);
        } else {
            container.put(item, 1);
        }

    }
    
    public void removeItem(T item) {
        if (checkItem(item)) {
            if (container.get(item) > 1) {
                container.put(item, container.get(item) - 1);
            } else {
                container.remove(item);
            }
            container.remove(item);
        } else {
            System.out.println("Tidak ada item yang dimaksud");
        }
    }

    public boolean checkItem(T item) {
        return container.containsKey(item);
    }

    public void showItem() {
        for (Map.Entry<T, Integer> pair : container.entrySet()) {
            System.out.println(" - " + pair.getKey() + " sejumlah " + pair.getValue() + "buah");
        }
    }

    public static void  main (String[] args) {
        Inventory<Objek> inventory = new Inventory<>();

        Objek mejaKursi = new Objek("meja kursi");
        Objek komporGas = new Objek("kompor gas");

        inventory.addItem(mejaKursi);
        inventory.addItem(komporGas);

        inventory.showItem();
    }
}
