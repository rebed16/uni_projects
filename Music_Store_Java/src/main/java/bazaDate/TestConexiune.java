package bazaDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Am folosit aceasta clasa pentru a testa conexiunea la baza de date
 */
public class TestConexiune {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/magazinmuzica?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "rebe7323";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            System.out.println("Conexiune reușită!");
        } catch (SQLException e) {
            System.err.println("Eroare la conexiune: " + e.getMessage());
        }
    }
}


