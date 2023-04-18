package entity;

import javax.swing.*;
import java.awt.*;

public class Rumah  {
    private Point lokasi = new Point();
    private Color color;
    public Rumah(int x, int y, Color color){
        lokasi.x = x;
        lokasi.y = y;
        this.color = color;
    }

    public Point getLokasi() {
        return lokasi;
    }

    public Color getColor() {
        return color;
    }
}
