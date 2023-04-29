package thread;

import java.io.Serializable;
import java.lang.reflect.*;
import entity.World;

public class ThreadAksi extends Thread implements Serializable {
    // Deklarasi atribut
    private String nama;
    private int sisaWaktu;
    private Method method;
    private Object object;
    private World world;
    private Object[] parameters;
    public Thread actionThread;
    
    // Konstruktor
    public ThreadAksi(String nama, int sisaWaktu, Method method, Object[] parameters, Object object, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.method = method;
        this.world = world;
        this.object = object;
        this.parameters = parameters;
    }
    public ThreadAksi(String nama, int sisaWaktu, Method method, Object object, World world) {
        this.nama = nama;
        this.sisaWaktu = sisaWaktu;
        this.method = method;
        this.world = world;
        this.object = object;
        this.parameters = new Object[0];
    }

    // Method
    @Override
    public void run() {
        System.out.println("start");

        try {
            invoke();
        } catch (InvocationTargetException e) {
            System.out.println(e.getMessage());

        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        while (sisaWaktu > 0){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
//            System.out.print(sisaWaktu + " ");
            sisaWaktu--;
        }
        //delete thread dari daftar thread
        world.getListThreadAksi().remove(this);
        System.out.println(this.getNama() + " deleted");
    }
    public Object invoke() throws InvocationTargetException, IllegalAccessException {
        return method.invoke(object, parameters);
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
    
}
