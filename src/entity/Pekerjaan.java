package entity;


import java.io.Serializable;

public class Pekerjaan implements Serializable {
    private String namaPekerjaan;
    private int gaji;
    private int id;

    public Pekerjaan (int id) {
        if(id == 24){
            namaPekerjaan = "Badut Sulap";
            gaji = 15;
        }
        else if(id == 25){
            namaPekerjaan = "Koki";
            gaji = 30;
        }
        else if(id == 26){
            namaPekerjaan = "Polisi";
            gaji = 35;
        }
        else if(id == 27){
            namaPekerjaan = "Programmer";
            gaji = 45;
        }
        else if(id == 28){
            namaPekerjaan = "Dokter";
            gaji = 50;
        }
        else if(id == 29){
            namaPekerjaan = "Direktur Bank";
            gaji = 100;
        }
        else if(id == 30){
            namaPekerjaan = "Rektor";
            gaji = 65;
        }
        else if(id == 31){
            namaPekerjaan = "Cleaning Service";
            gaji = 25;
        }
        else if(id == 32){
            namaPekerjaan = "Akuntan";
            gaji = 35;
        }
        else if(id == 33){
            namaPekerjaan = "Selebgram";
            gaji = 40;
        }
        else if(id == 34){
            namaPekerjaan = "Aktor";
            gaji = 40;
        }
    }

    public String getNamaPekerjaan() {
        return namaPekerjaan;
    }
    public int getGaji() {
        return gaji;
    }

    public int getId(){
        return id;
    }

    public void lakukanKerja(int waktu, Sim sim)  throws Exception{
        try{
      
            if(waktu % 120 != 0){
                throw new Exception("Lama pekerjaan lakukan harus kelipatan 120!");
            }
                
            long begin = System.currentTimeMillis();
            long now = begin;
            long finish = begin + (waktu*1000);
            long begin1 = begin;

            while(now <= finish){
                long now1 = now;
                

                if(now1 - begin1 >= 30000){
                    begin1 = now1;
                    sim.setKekenyangan(sim.getKekenyangan()-10);
                    sim.setMood(sim.getMood()-10);
                }

                if(now - begin >= 240000){
                    begin = now;
                    sim.setUang(sim.getUang()+sim.getPekerjaan().getGaji());
                }
                now = System.currentTimeMillis();
                
                    
            }
            System.out.println("sim "+sim.getNamaLengkap()+" selesai bekerja!");
            sim.setWaktuKerja(sim.getWaktuKerja() + waktu);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}
