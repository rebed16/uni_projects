package bazaDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Am folosit aceasta clasa pentru a ma conecta la baza de date
 */
public class ConectareBD {

	 /** The database URL for connecting to the MySQL database. */
    private static final String DB_URL = "jdbc:mysql://localhost:3306/magazinmuzica";

    /** The database user for authentication. */
    private static final String USER = "root";

    /** The database password for authentication. */
    private static final String PASS = "rebe7323";

    /**
     * Connects to the MySQL database using the provided database URL, username, and password.
     *
     * @return a {@link Connection} object representing the connection to the database.
     * @throws SQLException if a database access error occurs or the URL is incorrect.
     */
    public static Connection connect() throws SQLException {
        // Return the connection to the MySQL database
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

}
