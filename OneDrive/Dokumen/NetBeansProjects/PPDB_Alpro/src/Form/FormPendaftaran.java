package form;

import com.toedter.calendar.JDateChooser;
import java.awt.HeadlessException;
import javax.swing.*;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import koneksi.KoneksiDB;

public class FormPendaftaran extends JFrame {
    private JTextField txtNama, txtNISN, txtTTL, txtEmail, txtHP, txtAsal;
    private JDateChooser dateLahir;
    private JTextArea txtAlamat;
    private JLabel lblPath;

    public FormPendaftaran() {
        setTitle("Form Pendaftaran PPDB");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        initComponents(); 
    }

    private void initComponents() {
        JLabel lblNama = new JLabel("Nama Lengkap:");
        lblNama.setBounds(20, 20, 120, 25);
        add(lblNama);
        txtNama = new JTextField();
        txtNama.setBounds(150, 20, 200, 25);
        add(txtNama);

        JLabel lblNISN = new JLabel("NISN:");
        lblNISN.setBounds(20, 60, 120, 25);
        add(lblNISN);
        txtNISN = new JTextField();
        txtNISN.setBounds(150, 60, 200, 25);
        add(txtNISN);

        JLabel lblTTL = new JLabel("Tempat Lahir:");
        lblTTL.setBounds(20, 100, 120, 25);
        add(lblTTL);
        txtTTL = new JTextField();
        txtTTL.setBounds(150, 100, 200, 25);
        add(txtTTL);

        JLabel lblTanggal = new JLabel("Tanggal Lahir:");
        lblTanggal.setBounds(20, 140, 120, 25);
        add(lblTanggal);
        dateLahir = new JDateChooser();
        dateLahir.setBounds(150, 140, 200, 25);
        add(dateLahir);

        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setBounds(20, 180, 120, 25);
        add(lblAlamat);
        txtAlamat = new JTextArea();
        txtAlamat.setBounds(150, 180, 200, 50);
        add(txtAlamat);

        JLabel lblHP = new JLabel("No HP:");
        lblHP.setBounds(20, 240, 120, 25);
        add(lblHP);
        txtHP = new JTextField();
        txtHP.setBounds(150, 240, 200, 25);
        add(txtHP);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(20, 280, 120, 25);
        add(lblEmail);
        txtEmail = new JTextField();
        txtEmail.setBounds(150, 280, 200, 25);
        add(txtEmail);

        JLabel lblAsal = new JLabel("Asal Sekolah:");
        lblAsal.setBounds(20, 320, 120, 25);
        add(lblAsal);
        txtAsal = new JTextField();
        txtAsal.setBounds(150, 320, 200, 25);
        add(txtAsal);

        JButton btnUpload = new JButton("Unggah Dokumen");
        btnUpload.setBounds(20, 360, 150, 25);
        add(btnUpload);
        lblPath = new JLabel("Belum ada file");
        lblPath.setBounds(180, 360, 280, 25);
        add(lblPath);

        btnUpload.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                lblPath.setText(selectedFile.getAbsolutePath());
            }
        });

        JButton btnSimpan = new JButton("Simpan");
        btnSimpan.setBounds(20, 400, 100, 25);
        add(btnSimpan);
        btnSimpan.addActionListener(e -> {
            try {
                simpanData();
            } catch (Exception ex) {
                Logger.getLogger(FormPendaftaran.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void simpanData() throws Exception {
        try {
            try (Connection conn = KoneksiDB.getConnection()) {
                String sql = "INSERT INTO pendaftaran (nama_lengkap, nisn, tempat_lahir, tanggal_lahir, alamat, asal_sekolah, no_hp, email, dokumen_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement pst = conn.prepareStatement(sql)) {
                    pst.setString(1, txtNama.getText());
                    pst.setString(2, txtNISN.getText());
                    pst.setString(3, txtTempatLahir.getText());
                    pst.setDate(4, new java.sql.Date(dateChooser.getDate().getTime()));
                    pst.setString(5, txtAlamat.getText());
                    pst.setString(6, txtAsalSekolah.getText());
                    pst.setString(7, txtHP.getText());
                    pst.setString(8, txtEmail.getText());
                    pst.setString(9, txtPath.getText());
                    pst.executeUpdate();
                    
                    JOptionPane.showMessageDialog(this, "Data berhasil disimpan.");
                }
            }
} catch (HeadlessException | SQLException e) {
    JOptionPane.showMessageDialog(this, "Error saat menyimpan data: " + e.getMessage());
}

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPendaftaran().setVisible(true));
    }
}
