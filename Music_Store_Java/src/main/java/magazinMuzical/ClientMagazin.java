package magazinMuzical;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import bazaDate.ConectareBD;
/**
 * Am folosit aceasta clasa pentru a stoca date referitoare la clientii magazinului
 */
public class ClientMagazin {

    private int id;
    private String username;
    private String parola;

    public ClientMagazin() {
        id = 0;
        username = null;
        parola = null;
    }

    public static boolean autentific(String username, String parola) {
        String sql = "SELECT * FROM clienti WHERE username = ? AND parola = ?";
        try (Connection conn = ConectareBD.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, username);
            pstmt.setString(2, parola);
            ResultSet rs = pstmt.executeQuery();

            // Dacă există un rezultat, autentificarea a reușit
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Eroare la autentificare: " + e.getMessage());
        }
        return false;
    }
}
