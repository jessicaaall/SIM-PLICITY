package thread;

import entity.BisaDibeli;
import entity.Sim;
import entity.World;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ThreadBeli extends ThreadAksiPasif{

    BisaDibeli bisaDibeli;
    public ThreadBeli(String nama, int sisaWaktu, Object[] parameters, Object object, World world) {
        super(nama, sisaWaktu, parameters, object, world);
        bisaDibeli = (BisaDibeli) object;
    }

    public ThreadBeli(String nama, int sisaWaktu, Object object, World world) {
        super(nama, sisaWaktu, object, world);
        bisaDibeli = (BisaDibeli) object;
    }

    @Override
    public void run() {
        System.out.println("start");
        while (sisaWaktu > 0) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread interrupted");
            }

            if (!stopped) {
                sisaWaktu--;
            }
//            System.out.println("sisa waktu = " + sisaWaktu);
        }

        //delete thread dari daftar thread
        synchronized (world.getListThreadAksiPasif()){
            world.getListThreadAksiPasif().removeIf(threadAksiPasif -> threadAksiPasif.equals(this));
        }
        started = false;
        System.out.println(this.getNama() + " deleted");
        bisaDibeli.beli((Sim)parameters[0], (int) parameters[1]);

    }
}
