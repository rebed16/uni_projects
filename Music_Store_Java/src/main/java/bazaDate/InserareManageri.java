package bazaDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
/**
 * Am folosit aceasta clasa pentru a insera in baza de date informatii referitoare la manageri
 */
public class InserareManageri {
    public static void main(String[] args) {
        String sql = "INSERT INTO manageri (id, username, parola) VALUES (?, ?, ?)";

        try (Connection conn = ConectareBD.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Utilizator 1
            pstmt.setInt(1, 1); // ID
            pstmt.setString(2, "admin"); // Username
            pstmt.setString(3, "admin123"); // Parola
            pstmt.executeUpdate();

            // Utilizator 2
            pstmt.setInt(1, 2);
            pstmt.setString(2, "user1");
            pstmt.setString(3, "parola1");
            pstmt.executeUpdate();

            // Utilizator 3
            pstmt.setInt(1, 3);
            pstmt.setString(2, "user2");
            pstmt.setString(3, "parola2");
            pstmt.executeUpdate();
            
            pstmt.setInt(1, 4);
            pstmt.setString(2, "rebeca");
            pstmt.setString(3, "rebi");
            pstmt.executeUpdate();

            System.out.println("Datele au fost inserate cu succes!");

        } catch (SQLException e) {
            System.err.println("Eroare la inserarea datelor: " + e.getMessage());
        }
    }
}


