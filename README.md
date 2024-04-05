# SIM-PLICITY
Pada tahun 2022, BNMO milik Indra dan Doni telah berhasil diperbaiki sehingga memiliki beberapa fitur dan permainan. Namun, Indra dan Doni merasa sudah bosan dengan game-game pada robot video game tersebut. Oleh karena itu, BNMO diprogram lebih lanjut menjadi permainan Sim-Plicity, yaitu permainan yang menyerupai The Sims, yang dibuat dengan bahasa pemrograman Java. Sim-Plicity dapat dimainkan oleh pemain sebanyak jumlah sim yang dibuat dalam permainan. Tujuan dari permainan ini adalah menjaga kesejahteraan sim yang telah dibuat agar tidak depresi dan mati. Untuk mewujudkan hal tersebut, ada beberapa aksi yang dapat dilakukan oleh sim.

## Memulai Permainan
Untuk memulai permainan, berikut langkah-langkah yang perlu dilakukan.
- Tekan tombol Start pada menu awal
- Tekan tombol New World apabila ingin memulai permainan baru dan masukkan nama sim yang ingin dibuat
- Tekan tombol Load World apabila ingin melanjutkan permainan dari hasil save terdahulu dan masukkan nama file yang ingin di-load
- Tekan rumah sim
- Selamat bermain!

## Aturan Permainan
Berikut merupakan aturan bermain dalam game Sim-Plicity.
- Permainan akan dimulai dengan penciptaan sebuah sim dalam sebuah rumah pada world yang memiliki kesejahteraan dan segala aspek pendukung kehidupan sim
- Permainan memiliki waktu yang mecatat hari dan sisa waktu tiap harinya (waktu di dunia Sim-Plicity dalam 1 hari hanya selama 720 detik dan hanya akan berjalan jika dan hanya jika ada aksi yang membutuhkan waktu sedang berjalan)
- Pemain dapat menambahkan sim ke dalam world dengan ketentuan hanya bisa menambahkan sim maksimal 1 kali sehari yang dimulai dari hari ke-2
- Pemain dapat melakukan aksi apapun yang tersedia untuk sim yang mana saja dengan konsekuensi adanya perubahan kesejahteraan (mood, kekenyangan, dan kesehatan) pada sim yang melakukan aksi tersebut
- Apabila pemain ingin melakukan aksi yang berhubungan dengan objek, pemain harus memindahkan sim terlebih dahulu ke dekat objek yang ingin diinteraksi oleh sim tersebut
- Permainan akan berakhir saat semua sim yang dimiliki pemain mati, yaitu saat salah satu kesejahteraan sim (mood, kekenyangan, kesehatan) berada pada angka 0
- Pemain dapat menyimpan state terakhirnya dalam permainan dan melanjutkan permainannya kembali di lain waktu dengan fitur save dan load

## Implementasi Permainan
Sim-Plicity dikembangkan dengan menerapkan konsep-konsep dalam Object-Oriented Programming sebagai berikut.
- Inheritance
- Interface
- Polymorphism
- Generics
- Exceptions
- Conccurency
- Design Pattern, yaitu Factory Pattern dan Command Pattern

## Tahapan Kompilasi
Pertama-tama, lakukan clone repository pada terminal menggunakan command berikut.
```
git clone https://github.com/jessicaaall/SIM-PLICITY.git
```
Selanjutnya, terdapat dua alternatif cara yang dapat dilakukan untuk menjalankan program.
### Alternatif 1
- Buka command prompt atau terminal, lalu masuk ke directory clone
  ```
  cd {lokasi directory}/SIM-PLICITY
  ```
- Jalankan program dengan command berikut
  ```
  java -cp ./out/production/SIM-PLICITY/game.Main
  ```

### Alternatif 2
- Masuk ke directory clone menggunakan VSCode
- Jalankan program dengan pilih file Main.java, lalu klik "Run"

## Anggota Kelompok

| Nama |
| ------ |
| Jessica |  
| Nazhif Haidar Putra Wibowo |  
| Muhammad Aliefnaufal Permana |  
| Ken Azizan |  
| Reyhan Putra Ananda |  
