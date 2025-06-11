import javax.swing.*;                                                   // mengimpor paket untuk komponen Swing                                                                                                                                                                                                                                                                                              engimpor paket untuk komponen Swing

public class Utama {                                                    // kelas utama untuk menjalankan aplikasi permainan
    public static void main(String[] args) {                            // metode utama sebagai titik masuk aplikasi
        SwingUtilities.invokeLater(() -> {                              // menjalankan kode UI di Event Dispatch Thread
            JFrame frame = new JFrame("Game Bola Penghancur Bata");    // membuat jendela dengan judul
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       // menutup aplikasi saat jendela ditutup
            frame.setResizable(false);                        // menonaktifkan perubahan ukuran jendela
            frame.add(new PanelPermainan());                            // menambahkan panel permainan ke jendela
            frame.pack();                                               // mengatur ukuran jendela sesuai konten
            frame.setLocationRelativeTo(null);                        // menempatkan jendela di tengah layar
            frame.setVisible(true);                                   // menampilkan jendela
        });
    }
}         