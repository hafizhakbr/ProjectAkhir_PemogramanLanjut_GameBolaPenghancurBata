import java.awt.*;                                                              // mengimpor paket untuk kelas grafis dan warna

public class Bola extends ObjekPermainan {                                      // kelas Bola mewarisi ObjekPermainan untuk merepresentasikan bola dalam permainan
    private int dx, dy;                                                         // kecepatan pergerakan bola pada sumbu x (dx) dan y (dy)
    private final Color warna;                                                  // warna bola, final karena tidak berubah setelah inisialisasi

    public Bola(int x, int y, int diameter, int dx, int dy, Color warna) {      // konstruktor untuk inisialisasi posisi, ukuran, kecepatan, dan warna bola
        super(x, y, diameter, diameter);                                        // memanggil konstruktor kelas induk dengan diameter sebagai lebar dan tinggi
        this.dx = dx;                                                           // menetapkan kecepatan horizontal
        this.dy = dy;                                                           // menetapkan kecepatan vertikal
        this.warna = warna;                                                     // menetapkan warna bola
    }

    @Override
    public void gambar(Graphics g) {                                            // menggambar bola di layar menggunakan Graphics
        g.setColor(warna);                                                      // mengatur warna saat ini untuk Graphics
        g.fillOval(getX(), getY(), getLebar(), getTinggi());                    // menggambar lingkaran terisi pada posisi dan ukuran bola
    }

    @Override
    public void perbarui() {                                                    // memperbarui posisi bola berdasarkan kecepatan
        try {                                                                   // blok try untuk menangkap potensi pengecualian
            setX(getX() + dx);                                                  // memperbarui posisi x berdasarkan kecepatan dx
            setY(getY() + dy);                                                  // memperbarui posisi y berdasarkan kecepatan dy
        } catch (Exception e) {                                                 // menangkap pengecualian jika terjadi
            System.out.println("Error memperbarui bola: " + e.getMessage());    // mencetak pesan kesalahan
        }
    }

    public void balikX() { dx = -dx; }                                          // membalik arah pergerakan horizontal bola
    public void balikY() { dy = -dy; }                                          // membalik arah pergerakan vertikal bola
    public int getDx() { return dx; }                                           // getter untuk kecepatan horizontal
    public int getDy() { return dy; }                                           // getter untuk kecepatan vertikal
    public void setDx(int dx) { this.dx = dx; }                                 // setter untuk mengubah kecepatan horizontal
    public void setDy(int dy) { this.dy = dy; }                                 // setter untuk mengubah kecepatan vertikal
}