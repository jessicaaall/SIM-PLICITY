package entity;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Rumah  {
    private Point lokasi = new Point();
    private ArrayList<Ruangan> daftarRuangan;
    private Sim sim;
    World world;
    private Color color;

    public Rumah(int x, int y, Sim sim, Color color,World world){
        this.world = world;
        if(x > world.getWidth() -1 || y > world.getHeight()-1){
            System.out.println("Titik diluar jangkauan");
        }
        else{
            this.sim = sim;
            lokasi.x = x;
            lokasi.y = y;
            this.color = color;
            daftarRuangan.add(new Ruangan(this, new Point(0,0)));
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

    public void showDaftarRuangan(){
        for(Ruangan ruangan : daftarRuangan){
            System.out.println("nama ruangan");
        }
    }
}
