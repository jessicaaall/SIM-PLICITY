package entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public interface BisaDibeli {
    final Objek[] listObjek = {
            new MejaDanKursi(), new Kompor(2), new Kompor(3),
            new Toilet(), new Kasur(5), new Kasur(6),
            new Kasur(7), new TV(), new Komputer(),
            new Jam(null), new BahanMakanan(11), new BahanMakanan(12),
            new BahanMakanan(13), new BahanMakanan(14), new BahanMakanan(15),
            new BahanMakanan(16), new BahanMakanan(17), new BahanMakanan(18)
    };
    final Set<Objek> listBarang = new HashSet<>(Arrays.asList(listObjek));
    public void beli(Sim sim);
}
