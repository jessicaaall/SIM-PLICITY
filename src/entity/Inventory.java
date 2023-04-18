package entity;

import java.util.HashMap;
import java.util.Map;

public class Inventory {
    private Map<Objek,Integer> container;

    public Inventory() {
        container  = new HashMap<>();
    }

    public void addItem(Objek objek) {
        if (container.containsKey(objek)) {
            container.put(objek,container.get(objek)+1);
        } else {
            container.put(objek, 1);
        }
    }

    public boolean checkItem(Objek objek) {
        return container.containsKey(objek);
    }

    public Map<Objek,Integer> getContainer() {
        return container;
    }

    public void removeItem(Objek objek) {
        if (container.containsKey(objek)) {
            if (container.get(objek) > 1) {
                container.put(objek,container.get(objek)-1);
            } else {
                container.remove(objek);
            }
        } else {
            System.out.println("Item tidak ada");
        }
    }
}
