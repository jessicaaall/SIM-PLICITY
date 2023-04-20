package entity;

public class Objek {
    private int id;
    private String nama;
    private String jenis;

    public Objek(int id, String nama, String jenis) {
        this.id = id;
        this.nama = nama;
        this.jenis = jenis;
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
    
    public boolean equals(Object obj) {
        Objek objek = (Objek) obj;
        return this.id == objek.getId();
    }
}
