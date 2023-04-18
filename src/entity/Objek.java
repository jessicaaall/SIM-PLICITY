package entity;

public class Objek {
    private int id;
    private String name;
    private String jenis;
    private int harga;

    public Objek(int id, String name, String jenis, int harga) {
        this.id = id;
        this.name = name;
        this.jenis = jenis;
        this.harga = harga;   
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJenis() {
        return jenis;
    }

    public int getHarga() {
        return harga;
    }

    @Override
    public boolean equals(Object obj) {
        Objek objek = (Objek)obj;
        return this.id == objek.getId();
    }

    
}