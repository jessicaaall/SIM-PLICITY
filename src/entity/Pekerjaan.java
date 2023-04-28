package entity;


public class Pekerjaan {
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
        /*
        Scanner input = new Scanner(System.in);
        System.out.print("Masukan durasi tidur (satuan detik): ");
        Boolean found = false;

        while(!found){
            try{
                //int time = Integer.parseInt(input.nextLine());
                if(waktu % 120 != 0){
                    throw new Exception("Lama pekerjaan lakukan harus kelipatan 120!");
                }
                found = true;
                long begin = System.currentTimeMillis();
                long now = begin;
                long finish = begin + (waktu*1000);

                while(now < finish){
                    long runtime = now - begin;
                    if(runtime % 30000 == 0){
                        sim.setMood(sim.getMood()-10);
                        sim.setKekenyangan(sim.getKekenyangan()-10);
                    }
                    if(runtime % 240000 == 0){
                        sim.setUang(sim.getUang()+this.getGaji());
                    }
                    now = System.currentTimeMillis();
                    sim.setWaktuKerja(sim.getWaktuKerja() + 1);
                    
                }
                System.out.println("sim "+sim.getNamaLengkap()+" selesai bekerja!");
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        input.close();
        */

        try{
            if(waktu % 120 != 0){
                throw new IllegalArgumentException("Lama pekerjaan lakukan harus kelipatan 120!");
            }
            long begin = System.currentTimeMillis();
            long now = begin;
            long finish = begin + (waktu*1000);

            while(now < finish){
                long runtime = now - begin;
                if(runtime % 30000 == 0){
                    sim.setMood(sim.getMood()-10);
                    sim.setKekenyangan(sim.getKekenyangan()-10);
                }
                if(runtime % 240000 == 0){
                    sim.setUang(sim.getUang()+this.getGaji());
                }
                now = System.currentTimeMillis();
                sim.setWaktuKerja(sim.getWaktuKerja() + 1);
                
            }
            System.out.println("sim "+sim.getNamaLengkap()+" selesai bekerja!");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
