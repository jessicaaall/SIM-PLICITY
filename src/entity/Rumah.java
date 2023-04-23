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
        if(daftarRuangan.size() <= 0){
            System.out.println("Tidak terdapat ruangan dalam rumah");
        }

        int i = 1;
        System.out.println("Berikut ruangakan yang terdapat pada rumah");
        for(Ruangan ruangan : daftarRuangan){
            System.out.println(i+". "+ruangan.getNama());
            System.out.println(ruangan.getDaftarSamping());
            i = i +1;
        }
    }

    public Ruangan getRuanganbyName(String namaRuang){
        Boolean cek = false;
        Ruangan ruang = null;
        
        for(Ruangan ruangs : daftarRuangan){
            if(namaRuang.equals(ruangs.getNama())){
                cek = true;
                ruang = ruangs;
            }
        }
         
        if(cek){
            return ruang;
        }
        else{
            return null;
        }
    }

    public Ruangan getRuanganbyPoint(Point posisi){
        Boolean cek = false;
        Ruangan ruang = null;
        
        for(Ruangan ruangs : daftarRuangan){
            if(posisi.equals(ruangs.getPosisi())){
                cek = true;
                ruang = ruangs;
            }
        }
         
        if(cek){
            return ruang;
        }
        else{
            return null;
        }
    }

 

    public void upgrade(Sim sim){
        Scanner input = new Scanner(System.in);
        if(daftarRuangan.size()<2){
            System.out.print("Masukan lokasi penambahan ruangan (Kanan/Kiri/Atas/Bawah): ");
            String locTambah = input.nextLine();
            Ruangan ruangacuan = daftarRuangan.get(0);
            if(ruangacuan.getSamping(locTambah) == null){
                System.out.print("Masukan nama ruangan baru: ");
                String ruangBaru = input.nextLine();
                if(locTambah.equals("Kanan")){
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(1, 0),ruangacuan,"Kiri"));
                    
                }
                else if(locTambah.equals("Kiri")){
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(-1, 0),ruangacuan,"Kanan"));
                }
                else if(locTambah.equals("Atas")){
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(0, 1),ruangacuan,"Bawah"));
                }
                else{
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(0, -1),ruangacuan,"Atas"));
                }
                Ruangan newRomm = daftarRuangan.get(1);
                ruangacuan.setSamping(locTambah,newRomm);
            }
            else{
                System.out.println("Ruangan pada "+locTambah+" ruangan acuan sudah ada");
            }
        }
        else{
            System.out.println("Pilih ruang acuan: ");
            this.showDaftarRuangan();
            String acuan1 = input.nextLine();
            Ruangan ruangacuan = getRuanganbyName(acuan1);
            System.out.print("Masukan lokasi penambahan ruangan (Kanan/Kiri/Atas/Bawah): ");
            String locTambah = input.nextLine();
            if(ruangacuan.getSamping(locTambah) == null){
                System.out.print("Masukan nama ruangan baru: ");
                String ruangBaru = input.nextLine();
                int x = (int) ruangacuan.getPosisi().getX();
                int y = (int) ruangacuan.getPosisi().getY();
                if(locTambah.equals("Kanan")){
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(x+1, y),ruangacuan,"Kiri"));
                    
                }
                else if(locTambah.equals("Kiri")){
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(x-1, y),ruangacuan,"Kanan"));
                }
                else if(locTambah.equals("Atas")){
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(x, y+1),ruangacuan,"Bawah"));
                }
                else{
                    daftarRuangan.add(new Ruangan(ruangBaru,this,new Point(x, y-1),ruangacuan,"Atas"));
                }

                Ruangan newRoom = daftarRuangan.get(daftarRuangan.size()-1);
                ruangacuan.setSamping(locTambah,newRoom);
                
                
                Point newRoomPoint = newRoom.getPosisi();
                int nx = (int) newRoomPoint.getX();
                int ny = (int) newRoomPoint.getY();

                Point sampingKanan = new Point(nx+1, ny);
                Point sampingKiri = new Point(nx-1, ny);
                Point sampingAtas = new Point(nx, ny+1);
                Point sampingBawah = new Point(nx, ny-1);

                Ruangan ruangKanan = getRuanganbyPoint(sampingKanan);
                Ruangan ruangKiri = getRuanganbyPoint(sampingKiri);
                Ruangan ruangAtas = getRuanganbyPoint(sampingAtas);
                Ruangan ruangBawah = getRuanganbyPoint(sampingBawah);

                if(ruangKanan != null){
                    ruangKanan.setSamping("Kiri",newRoom);
                    newRoom.setSamping("Kanan",ruangKanan);
                }

                if(ruangKiri != null){
                    ruangKiri.setSamping("Kanan",newRoom);
                    newRoom.setSamping("Kiri",ruangKiri);
                }

                if(ruangAtas != null){
                    ruangAtas.setSamping("Bawah",newRoom);
                    newRoom.setSamping("Atas",ruangAtas);
                }

                if(ruangBawah != null){
                    ruangBawah.setSamping("Atas",newRoom);
                    newRoom.setSamping("Bawah",ruangBawah);
                }

            }
            else{
                System.out.println("Ruangan pada "+locTambah+" ruangan acuan sudah ada");
            }            

        }

    }
}
