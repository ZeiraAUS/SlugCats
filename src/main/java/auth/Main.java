package auth;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Testconnection {
    public static void main(String[] args) {
        Connection connection = DatabaseConnection.getInstance();
        try {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS bankAccounts (id INTEGER PRIMARY KEY, name VARCHAR, bankBalance INTEGER)");
            connection.close();
        } catch (SQLException sqle) {
            System.err.println(sqle);
        }
    }
}