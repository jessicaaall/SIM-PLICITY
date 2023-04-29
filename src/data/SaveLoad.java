package data;

import java.io.*;

import entity.World;

public class SaveLoad {

    public SaveLoad() {
        
    }

    public void save(String namaFile, World world) {
        ObjectOutputStream oos;
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(namaFile)));
            DataStorage ds = new DataStorage();
            ds.setWorld(world);
            oos.writeObject(ds);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        
    }

    public World load(String namaFile) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(namaFile)));

            DataStorage ds = (DataStorage)ois.readObject();

            return ds.getWorld();
        } catch(Exception e) {
            System.out.println("Load Exception!");
            System.out.println("MASUK SINI MAS");
            return null;
        }
        
    }
}
