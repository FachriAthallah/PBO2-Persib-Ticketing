package koneksi;

import java.sql.Connection;
import java.sql.SQLException;
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException
     */
     public static void main(String[] args) throws SQLException, Exception {
        Connection conn = koneksi.KoneksiDB.getConnection();
        if (conn != null) {
            System.out.println("Database terkoneksi dengan baik.");
        } else {
            System.out.println("Tidak bisa konek ke database.");
        }
     }
}