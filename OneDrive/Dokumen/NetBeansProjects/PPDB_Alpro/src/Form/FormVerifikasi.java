package form;

import koneksi.KoneksiDB;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class FormVerifikasi extends JFrame {
    private final JTable table;
    private final DefaultTableModel model;

    public FormVerifikasi() {
        setTitle("Form Verifikasi");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Nama");
        model.addColumn("NISN");
        model.addColumn("Status");

        table = new JTable(model);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBounds(20, 20, 550, 200);
        add(scroll);

        JButton btnTerima = new JButton("Terima");
        btnTerima.setBounds(20, 230, 100, 25);
        add(btnTerima);
        btnTerima.addActionListener(e -> updateStatus("LULUS"));

        JButton btnTolak = new JButton("Tolak");
        btnTolak.setBounds(130, 230, 100, 25);
        add(btnTolak);
        btnTolak.addActionListener(e -> updateStatus("TIDAK LULUS"));

        JButton btnRefresh = new JButton("Refresh");
        btnRefresh.setBounds(240, 230, 100, 25);
        add(btnRefresh);
        btnRefresh.addActionListener(e -> loadData());

        loadData();
    }

    private void loadData() {
        model.setRowCount(0);
        try (Connection conn = KoneksiDB.getConnection()) {
            String sql = "SELECT c.id_calon, c.nama_lengkap, c.nisn, v.status FROM tb_validasi v " +
                         "JOIN tb_datacalonsiswa c ON v.id_calon = c.id_calon";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                model.addRow(new Object[] {
                    rs.getInt("id_calon"),
                    rs.getString("nama_lengkap"),
                    rs.getString("nisn"),
                    rs.getString("status")
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error load data: " + e.getMessage());
        }
    }

    private void updateStatus(String statusBaru) {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu.");
            return;
        }
        int id = (int) table.getValueAt(row, 0);
        try (Connection conn = KoneksiDB.getConnection()) {
            String sql = "UPDATE tb_validasi SET status = ? WHERE id_calon = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, statusBaru);
            pst.setInt(2, id);
            pst.executeUpdate();
            JOptionPane.showMessageDialog(this, "Status diperbarui.");
            loadData();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal update: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormVerifikasi().setVisible(true));
    }
}
