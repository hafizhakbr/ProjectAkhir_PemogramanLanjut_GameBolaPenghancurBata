import java.awt.*;                                                                              // Mengimpor paket untuk grafis dan warna
import java.awt.event.*;                                                                        // Mengimpor paket untuk penanganan event
import javax.swing.*;                                                                           // Mengimpor paket untuk komponen Swing

public class PanelPermainan extends JPanel implements ActionListener, KeyListener {             // Kelas panel utama untuk menjalankan logika dan tampilan permainan
    private Bola bola;                                                                          // Objek bola dalam permainan
    private Papan papan;                                                                        // Objek papan pemain
    private ManajerBata<Bata> manajerBata;                                                      // Pengelola daftar bata
    private final Timer timer;                                                                  // Timer untuk memperbarui permainan secara periodik
    private boolean kiri, kanan;                                                                // Status tombol kiri dan kanan ditekan
    private int skor;                                                                           // Skor pemain
    private int nyawa = 3;                                                                      // Jumlah nyawa pemain, diinisialisasi 3
    private boolean bolaTahan = true;                                                           // Status apakah bola masih menempel di papan
    private final int BARIS = 5, KOLOM = 8;                                                     // Jumlah baris dan kolom bata
    private final int LEBAR = 600, TINGGI = 400;                                                // Ukuran panel permainan

    public PanelPermainan() {                                                                   // Konstruktor untuk inisialisasi panel permainan
        setPreferredSize(new Dimension(LEBAR, TINGGI));                                         // Mengatur ukuran panel
        setBackground(Color.WHITE);                                                             // Mengatur warna latar belakang panel
        setFocusable(true);                                                           // Mengizinkan panel menerima input fokus
        requestFocusInWindow();                                                                 // Meminta fokus saat jendela dibuka
        requestFocus();                                                                         // Memastikan panel mendapatkan fokus
        inisialisasiPermainan();                                                                // Menginisialisasi objek permainan
        timer = new Timer(10, this);                                                      // Membuat timer yang memanggil actionPerformed setiap 10ms
        timer.start();                                                                          // Memulai timer
    }

    @Override
    public void addNotify() {                                                                   // Menambahkan KeyListener saat panel ditambahkan ke jendela
        super.addNotify();                                                                      // Memanggil metode induk
        addKeyListener(this);                                                                   // Menambahkan panel sebagai pendengar event keyboard
    }

    private void inisialisasiPermainan() {                                                      // Menginisialisasi objek permainan seperti bola, papan, dan bata
        bola = new Bola(LEBAR/2-10, TINGGI-60, 20, 2, -2, Color.RED);               // Membuat bola baru
        papan = new Papan(LEBAR/2-40, TINGGI-30, 80, 15, 5, Color.BLUE); // Membuat papan baru
        manajerBata = new ManajerBata<>();                                                      // Membuat pengelola bata baru
        skor = 0;                                                                               // Mengatur skor awal menjadi 0
        buatBata();                                                                             // Membuat susunan bata
    }

    private void buatBata() {                                                                   // Membuat susunan bata dalam grid
        int lebarBata = 60, tinggiBata = 20, padding = 10, offsetX = 35, offsetY = 40;          // Dimensi dan jarak bata
        Color[] colors = {Color.ORANGE, Color.GREEN, Color.CYAN, Color.PINK, Color.MAGENTA};    // Warna untuk setiap baris
        for (int baris = 0; baris < BARIS; baris++) {                                           // Iterasi untuk setiap baris
            for (int kolom = 0; kolom < KOLOM; kolom++) {                                       // Iterasi untuk setiap kolom
                Bata bata = new Bata(                                                           // Membuat bata baru
                    offsetX + kolom * (lebarBata + padding),                                    // Posisi x berdasarkan kolom
                    offsetY + baris * (tinggiBata + padding),                                   // Posisi y berdasarkan baris
                    lebarBata, tinggiBata,                                                      // Ukuran bata
                    colors[baris % colors.length]                                               // Warna berdasarkan baris
                );
                manajerBata.tambahBata(bata);                                                   // Menambahkan bata ke pengelola
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {                                                  // Menggambar semua komponen permainan di panel
        super.paintComponent(g);                                                                 // Memanggil metode induk untuk menggambar latar belakang
        bola.gambar(g);                                                                          // Menggambar bola
        papan.gambar(g);                                                                         // Menggambar papan
        manajerBata.gambarSemua(g);                                                              // Menggambar semua bata
        g.setColor(Color.BLACK);                                                                 // Mengatur warna teks menjadi hitam
        g.drawString("Skor: " + skor, 10, 20);                                               // Menampilkan skor di sudut kiri atas
        g.drawString("Nyawa: " + nyawa, 100, 20);                                            // Menampilkan nyawa di samping skor
    }

    @Override
    public void actionPerformed(ActionEvent e) {                                                 // Dipanggil oleh timer untuk memperbarui dan menggambar ulang permainan
        perbaruiPermainan();                                                                     // Memperbarui logika permainan
        repaint();                                                                               // Menggambar ulang panel
    }

    private void perbaruiPermainan() {                                                           // Memperbarui posisi objek dan memeriksa logika permainan
        if (bolaTahan) {                                                                         // Jika bola masih menempel di papan
            bola.setX(papan.getX() + papan.getLebar()/2 - bola.getLebar()/2);                    // Menempatkan bola di tengah papan
            bola.setY(papan.getY() - bola.getTinggi());                                          // Menempatkan bola tepat di atas papan
        } else {                                                                                 // Jika bola sudah dilepaskan
            bola.perbarui();                                                                     // Memperbarui posisi bola
        }
        if (kiri) papan.gerakKiri();                                                             // Menggerakkan papan ke kiri jika tombol kiri ditekan
        if (kanan) papan.gerakKanan();                                                           // Menggerakkan papan ke kanan jika tombol kanan ditekan
        papan.setX(Utilitas.batasi(papan.getX(), 0, LEBAR - papan.getLebar()));              // Membatasi papan di dalam layar
        periksaTabrakan();                                                                       // Memeriksa tabrakan antara bola, papan, dan bata
    }

    private void periksaTabrakan() {                                                             // Memeriksa tabrakan dan mengatur logika permainan
        if (bola.getX() <= 0 || bola.getX() + bola.getLebar() >= LEBAR) bola.balikX();           // Membalik arah x jika bola menyentuh dinding kiri/kanan
        if (bola.getY() <= 0) bola.balikY();                                                     // Membalik arah y jika bola menyentuh dinding atas
        if (bola.getDy() > 0 &&                                                                  // Jika bola bergerak ke bawah dan
            bola.getY() + bola.getTinggi() >= papan.getY() &&                                    // Bola mencapai ketinggian papan
            bola.getY() + bola.getTinggi() <= papan.getY() + papan.getTinggi()/2 &&              // Bola masih di atas papan
            bola.getX() + bola.getLebar() >= papan.getX() &&                                     // Bola berada di sisi kanan papan
            bola.getX() <= papan.getX() + papan.getLebar()) {                                    // Bola berada di sisi kiri papan
            bola.balikY();                                                                       // Membalik arah vertikal bola
            bola.setY(papan.getY() - bola.getTinggi());                                          // Menempatkan bola tepat di atas papan
        }
        for (Bata bata : manajerBata.getBata()) {                                                // Iterasi melalui setiap bata
            if (!bata.isHancur() &&                                                              // Jika bata belum hancur dan
                bola.getX() + bola.getLebar() >= bata.getX() &&                                  // Bola berada di sisi kanan bata
                bola.getX() <= bata.getX() + bata.getLebar() &&                                  // Bola berada di sisi kiri bata
                bola.getY() + bola.getTinggi() >= bata.getY() &&                                 // Bola berada di sisi bawah bata
                bola.getY() <= bata.getY() + bata.getTinggi()) {                                 // Bola berada di sisi atas bata
                bata.setHancur(true);                                                     // Menandai bata sebagai hancur
                bola.balikY();                                                                   // Membalik arah vertikal bola
                skor += 10;                                                                      // Menambah skor sebesar 10
                break;                                                                           // Keluar dari loop setelah tabrakan
            }
        }
        if (bola.getY() > TINGGI) {                                                              // Jika bola jatuh di bawah layar
            nyawa--;                                                                             // Mengurangi nyawa
            if (nyawa > 0) {                                                                     // Jika masih ada nyawa
                respawnBolaDanPapan();                                                           // Mengatur ulang bola dan papan
            } else {                                                                             // Jika nyawa habis
                try {                                                                            // Blok try untuk melempar pengecualian
                    throw new PengecualianKhusus("Permainan Selesai!");                    // Melempar pengecualian khusus
                } catch (PengecualianKhusus ex) {                                                // Menangkap pengecualian
                    JOptionPane.showMessageDialog(this, ex.getMessage() + "\nSkor: " + skor);    // Menampilkan pesan game over
                    resetPermainan();                                                            // Mengatur ulang permainan
                }
            }
        }
        boolean semuaHancur = true;                                                              // Flag untuk memeriksa apakah semua bata hancur
        for (Bata bata : manajerBata.getBata()) {                                                // Iterasi melalui setiap bata
            if (!bata.isHancur()) {                                                              // Jika ada bata yang belum hancur
                semuaHancur = false;                                                             // Menandai bahwa tidak semua bata hancur
                break;                                                                           // Keluar dari loop
            }
        }
        if (semuaHancur) {                                                                        // Jika semua bata hancur
            manajerBata.reset();                                                                  // Mengatur ulang bata
            respawnBolaDanPapan();                                                                // Mengatur ulang bola dan papan
        }
    }

    private void respawnBolaDanPapan() {                                                          // Mengatur ulang posisi bola dan papan setelah kehilangan nyawa
        bola = new Bola(LEBAR/2-10, TINGGI-60, 20, 2, -2, Color.RED);                 // Membuat bola baru
        papan = new Papan(LEBAR/2-40, TINGGI-30, 80, 15, 5, Color.BLUE);   // Membuat papan baru
        bolaTahan = true;                                                                         // Menandai bola masih menempel di papan
    }

    private void resetPermainan() {                                                               // Mengatur ulang seluruh permainan saat game over
        nyawa = 3;                                                                                // Mengatur ulang nyawa menjadi 3
        skor = 0;                                                                                 // Mengatur ulang skor menjadi 0
        manajerBata.reset();                                                                      // Mengatur ulang status bata
        respawnBolaDanPapan();                                                                    // Mengatur ulang bola dan papan
    }

    @Override
    public void keyPressed(KeyEvent e) {                                                           // Menangani event saat tombol ditekan
        if (e.getKeyCode() == KeyEvent.VK_LEFT) kiri = true;                                       // Menandai tombol kiri ditekan
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) kanan = true;                                     // Menandai tombol kanan ditekan
        if (e.getKeyCode() == KeyEvent.VK_UP ) {                                                   // Jika tombol atas ditekan
            if (bolaTahan) bolaTahan = false;                                                      // Melepaskan bola dari papan
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {                                                           // Menangani event saat tombol dilepaskan
        if (e.getKeyCode() == KeyEvent.VK_LEFT) kiri = false;                                       // Menandai tombol kiri dilepaskan
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) kanan = false;                                     // Menandai tombol kanan dilepaskan
    }

    @Override
    public void keyTyped(KeyEvent e) {}                                                             // Metode kosong untuk menangani event ketik (tidak digunakan)
}