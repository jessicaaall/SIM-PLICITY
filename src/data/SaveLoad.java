package data;

import java.io.*;

import entity.World;

public class SaveLoad {

    public SaveLoad() {
        
    }

    public void save(String namaFile, World world) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(namaFile)));

            DataStorage ds = new DataStorage();

            ds.setWorld(world);

            oos.writeObject(ds);
        } catch (Exception e) {
            System.out.println("Save Exception!");
        } 
    }

    public void load(String namaFile, World world) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(namaFile)));

            DataStorage ds = (DataStorage)ois.readObject();

            world = ds.getWorld();
        } catch(Exception e) {
            System.out.println("Load Exception!");
        }
        
    }
}
