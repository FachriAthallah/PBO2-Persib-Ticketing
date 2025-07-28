package koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class KoneksiDB {
    private static Connection mysqlconfig;

    public static Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3307/db_ppdb_alpro";
            String user = "root";
            String pass = ""; // Ganti jika pakai password
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            mysqlconfig = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("Koneksi Gagal: " + e.getMessage());
        }
        return mysqlconfig;
    }
}
