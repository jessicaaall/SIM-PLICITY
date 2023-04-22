package entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Rumah  {
    private Point lokasi = new Point();
    private ArrayList<Ruangan> daftarRuangan = new ArrayList<Ruangan>();
    private Sim sim;
    World world;
    private Color color;

    public Rumah(int x, int y, Sim sim, Color color, World world){
        this.world = world;
        if(x > world.getWidth() -1 || y > world.getHeight()-1){
            System.out.println("Titik diluar jangkauan");
        }
        else{
            this.sim = sim;
            this.color = color;
            lokasi.x = x;
            lokasi.y = y;
            daftarRuangan.add(new Ruangan("Ruangan 1",this,new Point(0,0)));
//            daftarRuangan.add(new Ruangan("Ruangan 2", this, new Point( 0, 6)));
            Perabotan mejakursi = new Perabotan(1); //cuma sample buat uji coba, nanti bakal dihilangin
            mejakursi.setKiriAtas(new Point(0,0));//cuma sample buat uji coba, nanti bakal dihilangin
            Perabotan kasur = new Perabotan(5);
            kasur.setKiriAtas(new Point(0, 3));
            Perabotan jam = new Perabotan(10);
            jam.setKiriAtas(new Point(0, 4));
            Perabotan toilet = new Perabotan(4);
            toilet.setKiriAtas(new Point(0, 5));
            Perabotan komporgas = new Perabotan(2);
            komporgas.setKiriAtas(new Point(4, 0));

            daftarRuangan.get(0).getDaftarObjek().add(mejakursi); //cuma sample buat uji coba, nanti bakal dihilangin
            daftarRuangan.get(0).getDaftarObjek().add(kasur);
            daftarRuangan.get(0).getDaftarObjek().add(jam);
            daftarRuangan.get(0).getDaftarObjek().add(toilet);
            daftarRuangan.get(0).getDaftarObjek().add(komporgas);

        }
    }

    public Point getLokasi(){
        return lokasi;
    }

    public  ArrayList<Ruangan> getDaftarRuangan(){
        return daftarRuangan;
    }

    public Sim getSim(){
        return sim;
    }

    public Color getColor(){
        return color;
    }

    public void addRuangan(Ruangan ruangan){
        daftarRuangan.add(ruangan);
    }

    public void removeRuangan(Ruangan ruangan){
        Boolean sama = false;
        for(Ruangan ruanganRumah: daftarRuangan){
            if(ruanganRumah == ruangan){
                sama = true;
            }
        }
        if(sama){
            daftarRuangan.remove(ruangan);
        }
        
    }

    public void showDaftarRuangan(){
        for(Ruangan ruangan : daftarRuangan){
            System.out.println(ruangan.getNama());
        }
    }
}
