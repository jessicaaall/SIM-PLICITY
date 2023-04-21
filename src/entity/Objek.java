package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Objek {
    private int id;
    private String nama;
    private String jenis;

    public BufferedImage getImage() {
        return image;
    }

    private BufferedImage image;

    public Objek(String nama) {
        this.nama = nama;
        if (nama.equals("meja kursi")) {
            this.id = 1;
        } else if (nama.equals("kompor gas")) {
            this.id = 2;
        } else if (nama.equals("kompor listrik")) {
            this.id = 3;
        } else if (nama.equals("toilet")) {
            this.id = 4;
        } else if (nama.equals("single bed")) {
            this.id = 5;
        } else if (nama.equals("queen size bed")) {
            this.id = 6;
        } else if (nama.equals("king size bed")) {
            this.id = 7;
        } else if (nama.equals("tv")) {
            this.id = 8;
        } else if (nama.equals("komputer")) {
            this.id = 9;
        } else if (nama.equals("jam")) {
            this.id = 10;
        } else if (nama.equals("nasi")) {
            this.id = 11;
        } else if (nama.equals("kentang")) {
            this.id = 12;
        } else if (nama.equals("ayam")) {
            this.id = 13;
        } else if (nama.equals("sapi")) {
            this.id = 14;
        } else if (nama.equals("wortel")) {
            this.id = 15;
        } else if (nama.equals("bayam")) {
            this.id = 16;
        } else if (nama.equals("kacang")) {
            this.id = 17;
        } else if (nama.equals("susu")) {
            this.id = 18;
        } else if (nama.equals("nasi ayam")) {
            this.id = 19;
        } else if (nama.equals("nasi kari")) {
            this.id = 20;
        } else if (nama.equals("susu kacang")) {
            this.id = 21;
        } else if (nama.equals("tumis sayur")) {
            this.id = 22;
        } else if (nama.equals("bistik")) {
            this.id = 23;
        }
        this.jenis = tipeJenis(this.id);
        image = generateImage(nama);
    }

    public Objek(int id) {
        this.id = id;
        this.jenis = tipeJenis(this.id);
        if (id == 1) {
            this.nama = "meja kursi";
        } else if (id == 2) {
            this.nama = "kompor gas";
        } else if (id == 3) {
            this.nama = "kompor listrik";
        } else if (id == 4) {
            this.nama = "toilet";
        } else if (id == 5) {
            this.nama = "single bed";
        } else if (id == 6) {
            this.nama = "queen size bed";
        } else if (id == 7) {
            this.nama = "king size bed";
        } else if (id == 8) {
            this.nama = "tv";
        } else if (id == 9) {
            this.nama = "komputer";
        } else if (id == 10) {
            this.nama = "jam";
        } else if (id == 11) {
            this.nama = "nasi";
        } else if (id == 12) {
            this.nama = "kentang";
        } else if (id == 13) {
            this.nama = "ayam";
        } else if (id == 14) {
            this.nama = "sapi";
        } else if (id == 15) {
            this.nama = "wortel";
        } else if (id == 16) {
            this.nama = "bayam";
        } else if (id == 17) {
            this.nama = "kacang";
        } else if (id == 18) {
            this.nama = "susu";
        } else if (id == 19) {
            this.nama = "nasi ayam";
        } else if (id == 20) {
            this.nama = "nasi kari";
        } else if (id == 21) {
            this.nama = "susu kacang";
        } else if (id == 22) {
            this.nama = "tumis sayur";
        } else if (id == 23) {
            this.nama = "bistik";
        }
        image = generateImage(this.nama);
    }

    public Objek(int id, String nama, String jenis) {
        this.id = id;
        this.nama = nama;
        this.jenis = tipeJenis(id);
    }

    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getJenis() {
        return jenis;
    }

    public String tipeJenis(int id) {
        if (id >= 1 && id <= 10) {
            return "perabotan";
        } else if (id >= 11 && id <= 18) {
            return "bahan makanan";
        } else if (id >= 19 && id <= 23) {
            return "makanan";
        } else {
            return "unknown";
        }
    } 
    
    public boolean equals(Object obj) {
        Objek objek = (Objek) obj;
        return this.id == objek.getId();
    }

    private BufferedImage generateImage(String name){
        try {
            return ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/res/" + name + ".png")));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
