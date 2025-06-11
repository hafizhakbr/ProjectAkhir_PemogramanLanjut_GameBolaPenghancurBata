import java.awt.*;                                                          // import library untuk Graphics

public class Bata extends ObjekPermainan {                                  // kelas Bata yang mewarisi dari ObjekPermainan
    private boolean hancur;                                                 // variabel untuk menandai apakah bata sudah hancur atau belum
    private final Color warna;                                              // variabel final untuk menyimpan warna bata

    public Bata(int x, int y, int lebar, int tinggi, Color warna) {         // constructor untuk membuat objek bata
        super(x, y, lebar, tinggi);                                         // memanggil constructor parent class
        this.warna = warna;                                                 // set warna bata
        this.hancur = false;                                                // inisialisasi bata dalam keadaan tidak hancur
    }

    @Override                                                               // override method gambar untuk menggambar bata
    public void gambar(Graphics g) {                                        // hanya gambar bata jika belum hancur
        if (!hancur) {
            g.setColor(warna);                                              // set warna untuk mengisi bata
            g.fillRect(getX(), getY(), getLebar(), getTinggi());            // gambar persegi panjang terisi pada posisi bata
            g.setColor(Color.BLACK);                                        // set warna hitam untuk border
            g.drawRect(getX(), getY(), getLebar(), getTinggi());            // gambar garis tepi persegi panjang
        }
    }

    @Override                                                               //override method perbarui (kosong karena bata tidak bergerak)
    public void perbarui() {}

    public boolean isHancur() { return hancur; }                            // getter untuk mengecek apakah bata sudah hancur
    public void setHancur(boolean hancur) { this.hancur = hancur; }         // setter untuk mengubah status hancur bata
}