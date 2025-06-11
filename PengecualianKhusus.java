public class PengecualianKhusus extends Exception {             // jelas pengecualian kustom untuk menangani kondisi khusus dalam permainan
    public PengecualianKhusus(String pesan) {                   // honstruktor untuk membuat pengecualian dengan pesan
        super(pesan);                                           // wemanggil konstruktor kelas induk Exception dengan pesan
    }
}