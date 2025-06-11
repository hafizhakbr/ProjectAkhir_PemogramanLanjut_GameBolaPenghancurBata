import java.awt.Graphics;                       // mengimpor kelas Graphics untuk menggambar
import java.util.ArrayList;                     // mengimpor ArrayList untuk menyimpan daftar bata
import java.util.List;                          // mengimpor List sebagai tipe koleksi

public class ManajerBata<T extends Bata> {       // kelas generik untuk mengelola daftar bata dalam permainan
    private final List<T> daftarBata;            // daftar untuk menyimpan objek bata

    public ManajerBata() {                       // konstruktor untuk inisialisasi daftar bata kosong
        daftarBata = new ArrayList<>();          // membuat ArrayList baru untuk daftar bata
    }
    public void tambahBata(T bata) {             // menambahkan bata ke daftar
        daftarBata.add(bata);                    // menambahkan objek bata ke ArrayList
    }
    public List<T> getBata() {                   // getter untuk mendapatkan daftar bata
        return daftarBata;                       // mengembalikan referensi ke daftar bata
    }
    public void gambarSemua(Graphics g) {        // menggambar semua bata yang ada di daftar
        for (T bata : daftarBata) {              // iterasi melalui setiap bata di daftar
            bata.gambar(g);                      // memanggil metode gambar untuk setiap bata
        }
    }
    public void reset() {                        // mengatur ulang status semua bata menjadi tidak hancur
        for (T bata : daftarBata) {              // iterasi melalui setiap bata di daftar
            bata.setHancur(false);               // mengatur status hancur menjadi false
        }
    }
}