package entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    // Deklarasi Atribut
    private Map<Objek,Integer> container;

    // Konstruktor
    public Inventory() {
        container = new HashMap<>();
    }

    // Method
    public void addItem(Objek objek) {
        if (checkItem(objek)) {
            container.put(objek,container.get(objek)+1);
        } else {
            container.put(objek, 1);
        }
    }

    public void removeItem(Objek objek) {
        if (checkItem(objek)) {
            if (container.get(objek) > 1) {
                container.put(objek,container.get(objek)-1);
            } else {
                container.remove(objek);
            }
        } else {
            System.out.println("Item tidak ada");
        }
    }

    public boolean checkItem(Objek objek) {
        return container.containsKey(objek);
    }

    public Map<Objek,Integer> getContainer() {
        return container;
    }
}
