import java.awt.*;

public class Papan extends ObjekPermainan {
    private int kecepatan;
    private final Color warna;

    public Papan(int x, int y, int lebar, int tinggi, int kecepatan, Color warna) {
        super(x, y, lebar, tinggi);
        this.kecepatan = kecepatan;
        this.warna = warna;
    }

    @Override
    public void gambar(Graphics g) {
        g.setColor(warna);
        g.fillRect(getX(), getY(), getLebar(), getTinggi());
    }

    @Override
    public void perbarui() {}

    public void gerakKiri() {
        setX(getX() - kecepatan);
    }

    public void gerakKanan() {
        setX(getX() + kecepatan);
    }

    public int getKecepatan() { return kecepatan; }
    public void setKecepatan(int kecepatan) { this.kecepatan = kecepatan; }
} 