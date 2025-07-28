package form;

import javax.swing.*;
import java.sql.*;
import koneksi.KoneksiDB;

public class FormDashboardPPDB extends JFrame {

    private final JLabel lblTotal;
    private final JLabel lblLulus;
    private final JLabel lblTolak;
    private final JLabel lblMenunggu;

    public FormDashboardPPDB() {
        setTitle("Dashboard PPDB");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        lblTotal = new JLabel();
        lblTotal.setBounds(20, 20, 300, 25);
        add(lblTotal);

        lblLulus = new JLabel();
        lblLulus.setBounds(20, 60, 300, 25);
        add(lblLulus);

        lblTolak = new JLabel();
        lblTolak.setBounds(20, 100, 300, 25);
        add(lblTolak);

        lblMenunggu = new JLabel();
        lblMenunggu.setBounds(20, 140, 300, 25);
        add(lblMenunggu);

        tampilkanStatistik();
    }

    private void tampilkanStatistik() {
        try (Connection conn = KoneksiDB.getConnection()) {
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM tb_validasi");
            rs.next();
            lblTotal.setText("Total Pendaftar: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM tb_validasi WHERE status = 'LULUS'");
            rs.next();
            lblLulus.setText("Diterima: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM tb_validasi WHERE status = 'TIDAK LULUS'");
            rs.next();
            lblTolak.setText("Ditolak: " + rs.getInt(1));

            rs = stmt.executeQuery("SELECT COUNT(*) FROM tb_validasi WHERE status = 'MENUNGGU'");
            rs.next();
            lblMenunggu.setText("Belum Diverifikasi: " + rs.getInt(1));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load statistik: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormDashboardPPDB().setVisible(true));
    }
}
