package data;

import java.io.*;
import java.util.Collections;

import entity.World;
import thread.ThreadAksiPasif;

public class SaveLoad {

    public SaveLoad() {
        
    }

    public void save(String namaFile, World world) {
        ObjectOutputStream oos;
        String directoryPath = "savedata";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();  // Membuat direktori jika belum ada
        }
        String filePath = directoryPath + File.separator + namaFile + ".dat";
        try {
            oos = new ObjectOutputStream(new FileOutputStream(new File(filePath)));
            DataStorage ds = new DataStorage();
            for (ThreadAksiPasif thread : world.getListThreadAksiPasif()) {
                synchronized (thread) {
                    thread.killThread();
                }
            }
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
        String directoryPath = "savedata";
        String filePath = directoryPath + File.separator + namaFile + ".dat";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(filePath)));

            DataStorage ds = (DataStorage)ois.readObject();

            return ds.getWorld();
        } catch(Exception e) {
            System.out.println("Load Exception!");
            return null;
        }
        
    }
}
