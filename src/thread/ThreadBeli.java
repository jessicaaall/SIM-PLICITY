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
            if (!stopped){
                sisaWaktu--;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("thread interrupted");
            }
//            System.out.println("sisa waktu = " + sisaWaktu);
        }

        //delete thread dari daftar thread
        if (sisaWaktu <= 0){
            Iterator<ThreadAksiPasif> it = world.getListThreadAksiPasif().iterator();
            while (it.hasNext()){
                ThreadAksiPasif threadAksiPasif = it.next();
                if (threadAksiPasif.equals(this)){
                    it.remove();
                }
            }
            started = false;
            System.out.println(this.getNama() + " deleted");
            bisaDibeli.beli((Sim)parameters[0], (int) parameters[1]);
        }

    }
}
