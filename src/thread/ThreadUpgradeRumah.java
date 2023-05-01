package thread;

import entity.Ruangan;
import entity.Rumah;
import entity.Sim;
import entity.World;

import java.util.Iterator;

public class ThreadUpgradeRumah extends ThreadAksiPasif{

    Rumah rumah;
    public ThreadUpgradeRumah(String nama, int sisaWaktu, Object[] parameters, Object object, World world) {
        super(nama, sisaWaktu, parameters, object, world);
        rumah = (Rumah) object;
    }

    public ThreadUpgradeRumah(String nama, int sisaWaktu, Object object, World world) {
        super(nama, sisaWaktu, object, world);
        rumah = (Rumah) object;
    }

    @Override
    public void run() {
        System.out.println("start");

        while (!stopped) {
            while (sisaWaktu > 0 && world.isActive){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {

                }
    //            System.out.print(sisaWaktu + " ");
                sisaWaktu--;
            }
            stopThread();
        }
        if (sisaWaktu <= 0){
            //delete thread dari daftar thread
            Iterator<ThreadAksiPasif> it = world.getListThreadAksiPasif().iterator();
            while (it.hasNext()){
                ThreadAksiPasif threadAksiPasif = it.next();
                if (threadAksiPasif.equals(this)){
                    it.remove();
                }
            }
            System.out.println(this.getNama() + " deleted");
            rumah.upgrade((Ruangan) parameters[0]);
        }

    }
}
