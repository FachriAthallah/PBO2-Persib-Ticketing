package form;

import koneksi.KoneksiDB;
import javax.swing.*;
import java.sql.*;

public class FormPengumuman extends JFrame {
    private final JTextField txtCari;
    private final JTextArea txtHasil;

    public FormPengumuman() {
        setTitle("Form Pengumuman");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblCari = new JLabel("NISN / Nama:");
        lblCari.setBounds(20, 20, 100, 25);
        add(lblCari);

        txtCari = new JTextField();
        txtCari.setBounds(120, 20, 200, 25);
        add(txtCari);

        JButton btnCari = new JButton("Cari");
        btnCari.setBounds(150, 60, 80, 25);
        add(btnCari);
        btnCari.addActionListener(e -> cariData());

        txtHasil = new JTextArea();
        txtHasil.setBounds(20, 100, 340, 120);
        add(txtHasil);
    }

    private void cariData() {
        String keyword = txtCari.getText();
        try (Connection conn = KoneksiDB.getConnection()) {
            String sql = "SELECT c.nama_lengkap, c.nisn, v.status FROM tb_datacalonsiswa c " +
                         "JOIN tb_validasi v ON v.id_calon = c.id_calon " +
                         "WHERE c.nisn = ? OR c.nama_lengkap LIKE ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, keyword);
            pst.setString(2, "%" + keyword + "%");
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String hasil = "Nama: " + rs.getString("nama_lengkap") + "\n" +
                               "NISN: " + rs.getString("nisn") + "\n" +
                               "Status: " + rs.getString("status");
                txtHasil.setText(hasil);
            } else {
                txtHasil.setText("Data tidak ditemukan.");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal cari: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPengumuman().setVisible(true));
    }
}
