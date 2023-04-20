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

    public Rumah(int x, int y, Sim sim,World world){
        this.world = world;
        if(x > world.getWidth() -1 || y > world.getHeight()-1){
            System.out.println("Titik diluar jangkauan");
        }
        else{
            this.sim = sim;
            lokasi.x = x;
            lokasi.y = y;
            daftarRuangan.add(new Ruangan("Ruangan 1",this,new Point(1,1)));
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
