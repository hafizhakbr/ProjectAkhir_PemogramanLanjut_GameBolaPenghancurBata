public abstract class ObjekPermainan {                              // kelas abstrak yang menjadi parent class untuk semua objek permainan
    private int x, y, lebar, tinggi;                                // deklarasi variabel instance untuk koordinat (x, y) dan dimensi (lebar, tinggi) objek

    public ObjekPermainan(int x, int y, int lebar, int tinggi) {    // konstruktor untuk menginisialisasi posisi dan dimensi objek
        this.x = x;                                                 // mengatur koordinat x
        this.y = y;                                                 // mengatur koordinat y
        this.lebar = lebar;                                         // mengatur lebar objek
        this.tinggi = tinggi;                                       // mengatur tinggi objek
    }

    public int getX() { return x; }                                 // getter untuk mendapatkan nilai koordinat x
    public int getY() { return y; }                                 // getter untuk mendapatkan nilai koordinat y
    public int getLebar() { return lebar; }                         // getter untuk mendapatkan nilai lebar
    public int getTinggi() { return tinggi; }                       // getter untuk mendapatkan nilai tinggi

    public void setX(int x) { this.x = x; }                         // setter untuk mengubah nilai koordinat x
    public void setY(int y) { this.y = y; }                         // setter untuk mengubah nilai koordinat y
    public void setLebar(int lebar) { this.lebar = lebar; }         // setter untuk mengubah nilai lebar
    public void setTinggi(int tinggi) { this.tinggi = tinggi; }     // setter untuk mengubah nilai tinggi

    public abstract void gambar(java.awt.Graphics g);               // metode abstrak untuk menggambar objek di layar
    public abstract void perbarui();                                // metode abstrak untuk memperbarui status objek
}