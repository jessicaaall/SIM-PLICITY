package entity;

import javax.swing.*;
import java.awt.*;

public class Rumah  {
    World world;
    private Point lokasi = new Point();
    private Color color;
    public Rumah(int x, int y, Color color, World world){
        this.world = world;
        if (x > world.getWidth() - 1 || y > world.getHeight()-1){
            System.out.println("Out of boundary");
        }
        else{
            lokasi.x = x;
            lokasi.y = y;
            this.color = color;
        }
    }

    public Point getLokasi() {
        return lokasi;
    }

    public Color getColor() {
        return color;
    }
}
