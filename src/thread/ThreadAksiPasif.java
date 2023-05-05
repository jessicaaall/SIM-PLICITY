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
    protected boolean stopped;

    public boolean isStarted() {
        return started;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }

    protected boolean started = false;


    // Konstruktor
    public ThreadAksiPasif(String nama, int sisaWaktu, Object[] parameters, Object object, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.world = world;
        this.object = object;
        this.parameters = parameters;
        stopped = false;
    }
    public ThreadAksiPasif(String nama, int sisaWaktu, Object object, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.world = world;
        this.object = object;
        this.parameters = new Object[0];
        stopped = false;
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
    }

    public void startThread(){
        if (!started){
            started = true;
            System.out.println("started");
            start();
            started = true;
            if (stopped){
                stopped = false;
                System.out.println("start again");
                stopped = false;
                start();
            }
            return;
        }
        if (stopped){
            stopped = false;
            System.out.println("start again");
            stopped = false;
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

    public void killThread(){
        stopped = true;
        savedSisaWaktu = sisaWaktu;
        interrupt();
    }

    public int getSavedSisaWaktu() {
        return savedSisaWaktu;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }

    public void setSavedSisaWaktu(int savedSisaWaktu) {
        this.savedSisaWaktu = savedSisaWaktu;
    }

    public boolean isStopped() {
        return stopped;
    }

    public void setStopped(boolean stopped) {
        this.stopped = stopped;
    }

    public Object[] getParameters() {
        return parameters;
    }
}
