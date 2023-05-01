package thread;

import java.io.Serializable;
import java.lang.reflect.*;
import entity.World;

/**
 * Tipe Object untuk Thread aksi tidak aktif, ketika thread dijalankan, maka tidak akan mengurangi waktu
 */
public abstract class ThreadAksiPasif extends Thread implements Serializable {
    // Deklarasi atribut
    protected String nama;
    protected int sisaWaktu;
    protected Object object;
    protected World world;
    protected Object[] parameters;
    protected int savedSisaWaktu = 0;
    protected boolean stopped = false;


    // Konstruktor
    public ThreadAksiPasif(String nama, int sisaWaktu, Object[] parameters, Object object, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.world = world;
        this.object = object;
        this.parameters = parameters;
    }
    public ThreadAksiPasif(String nama, int sisaWaktu, Object object, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.world = world;
        this.object = object;
        this.parameters = new Object[0];
    }

    // Method


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

    public void stopThread(){
        stopped = true;
        savedSisaWaktu = sisaWaktu;
        interrupt();

    }

    public void startThread(){
        if (stopped){
            sisaWaktu = savedSisaWaktu;
            stopped = false;
            start();
        }
        else{
            if (!isAlive()){
                start();
            }
        }
    }

    @Override
    public String toString() {
        return nama + " (" + sisaWaktu + "s)";
    }

    @Override
    public boolean equals(Object obj) {
        return nama.equals(((ThreadAksiPasif)obj).nama);
    }
}
