import java.awt.*;                                                                      // Mengimpor package java.awt untuk GUI (Graphics, Color, dll)

public class Papan extends ObjekPermainan {                                             // Class Papan mewarisi ObjekPermainan
    private int kecepatan;                                                              // Variabel private untuk menyimpan kecepatan papan
    private final Color warna;                                                          // Variabel final (tidak bisa diubah) untuk warna papan

    public Papan(int x, int y, int lebar, int tinggi, int kecepatan, Color warna) {
        super(x, y, lebar, tinggi);                                                     // Memanggil konstruktor parent class (ObjekPermainan)
        this.kecepatan = kecepatan;                                                     // Menginisialisasi kecepatan
        this.warna = warna;                                                             // Menginisialisasi warna
    }
                                                                       
    @Override
    public void gambar(Graphics g) {
        g.setColor(warna);                                                               // Mengatur warna gambar
        g.fillRect(getX(), getY(), getLebar(), getTinggi());                             // Menggambar papan (persegi panjang)
    }

    @Override
    public void perbarui() {}                                                           // Kosong karena papan tidak perlu update otomatis

    public void gerakKiri() {
        setX(getX() - kecepatan);                                                       // Mengurangi posisi X (bergerak ke kiri)
    }

    public void gerakKanan() {
        setX(getX() + kecepatan);                                                       // Menambah posisi X (bergerak ke kanan)
    }

    public int getKecepatan() { 
        return kecepatan;                                                               // Mengembalikan nilai kecepatan
    }

    public void setKecepatan(int kecepatan) { 
        this.kecepatan = kecepatan;                                                     // Mengubah nilai kecepatan
    }
}
