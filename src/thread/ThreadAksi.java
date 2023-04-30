package thread;

import java.io.Serializable;
import java.lang.reflect.*;
import entity.World;

public class ThreadAksi extends Thread implements Serializable {
    // Deklarasi atribut
    private String nama;
    private int sisaWaktu;
    private World world;

    public boolean stopped;
    public int savedSisaWaktu;
    
    // Konstruktor
    public ThreadAksi(String nama, int sisaWaktu, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.world = world;
    }

    public void startThread(){
        world.setThreadAksi(this);
        if (stopped){
            sisaWaktu = savedSisaWaktu;
            stopped = false;
            start();
        }
    }

    public void stopThread(){
        stopped = true;
        savedSisaWaktu = sisaWaktu;
        interrupt();
    }


    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public int getSisaWaktu() {
        return sisaWaktu;
    }
    
    public void setSisaWaktu(int sisaWaktu) {
        this.sisaWaktu = sisaWaktu;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            while (sisaWaktu > 0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
                //            System.out.print(sisaWaktu + " ");
                sisaWaktu--;
            }
            stopThread();
            //delete thread dari daftar thread
            world.setThreadAksi(null);
        }
    }
}
