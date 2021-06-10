package app.twitter.com.database;

import java.sql.SQLException;

public class DatabaseUtil {
    private static DatabaseConnectionPool databaseConnectionPool;

    static {
        try {
            databaseConnectionPool = new DatabaseConnectionPool(
                    "jdbc:postgresql://localhost:5432/twitter", "postgres","postgres" , 350, 350);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseConnectionPool getDataseBaseConnectionPool() {
        return databaseConnectionPool;
    }
}