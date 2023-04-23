package entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory<T extends Objek> {
    public Map<T, Integer> getContainer() {
        return container;
    }

    // Deklarasi Atribut
    private Map<T, Integer> container;

    // Konstruktor
    public Inventory() {
        container = new HashMap<T, Integer>();
    }

    // Method
    public int getContainerCapacity() {
        return container.size();
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

    public String[] showItem() {
        String[] inventoryPrinter = new String[getContainerCapacity()];
        int i = 0;
        for (Map.Entry<T, Integer> pair : container.entrySet()) {
            inventoryPrinter[i] = pair.getKey().getNama() + " sejumlah " + pair.getValue() + " buah";
            i++;
        }
        return inventoryPrinter;
    }
}
