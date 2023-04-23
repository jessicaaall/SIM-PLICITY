package data;

import java.io.*;

import game.WorldPanel;

public class SaveLoad {
    private WorldPanel wp;

    public SaveLoad(WorldPanel wp) {
        this.wp = wp;
    }

    public void save() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File("save.dat")));

            DataStorage ds = new DataStorage();

            ds.setWorld(wp.getWorld());

            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("Save Exception!");
        } 
    }

    public void laod() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File("save.dat")));

            DataStorage ds = (DataStorage)ois.readObject();

            wp.setWorld(ds.getWorlrd());
        } catch(Exception e) {
            System.out.println("Load Exception!");
        }
    }
}
