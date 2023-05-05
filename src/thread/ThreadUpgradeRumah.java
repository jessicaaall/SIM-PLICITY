package thread;

import entity.World;
import game.HousePanel;

import java.util.Iterator;

public class ThreadUpgradeRumah extends ThreadAksiPasif{

    public ThreadUpgradeRumah(String nama, int sisaWaktu, Object[] parameters, Object object, World world) {
        super(nama, sisaWaktu, parameters, object, world);
    }

    public ThreadUpgradeRumah(String nama, int sisaWaktu, Object object, World world) {
        super(nama, sisaWaktu, object, world);
    }

    @Override
    public void run() {
        System.out.println("start");

        while (!stopped) {
            while (sisaWaktu > 0 && world.isActive()){
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
            synchronized (world.getListThreadAksiPasif()) {
                world.getListThreadAksiPasif().removeIf(threadAksiPasif -> threadAksiPasif.equals(this));
            }
            System.out.println(this.getNama() + " deleted");
            ((HousePanel) object).upgradeRumah();
        }

    }
}
