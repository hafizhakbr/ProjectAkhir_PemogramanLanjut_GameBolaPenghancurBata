public class Utilitas {                                             // kelas utilitas untuk fungsi-fungsi bantuan
    public static int batasi(int nilai, int min, int max) {         // metode statis untuk membatasi nilai dalam rentang min dan max
        if (nilai < min) return min;                                // mengembalikan min jika nilai lebih kecil dari min
        if (nilai > max) return max;                                // mengembalikan max jika nilai lebih besar dari max
        return nilai;                                               // mengembalikan nilai jika berada dalam rentang
    }
}