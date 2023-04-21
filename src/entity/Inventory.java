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

    }
    
    public void removeItem(T item) {

    }

    public boolean checkItem(T item) {
        return container.containsKey(item);
    }

    public void showItem() {
        for (Map.Entry<T, Integer> pair : container.entrySet()) {
            System.out.println(" - " + pair.getKey() + " sejumlah " + pair.getValue() + "buah");
        }
    }
}
