import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataSource {
    public static final String DB_NAME = "login-page-fx.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String CREDENTIALS_TABLE = "credentials";

    public static final String ID_COLUMN = "_id";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";

    private Connection connection;

    private static final DataSource instance = new DataSource(); // lazy instantiation

    public static DataSource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void insertCredentials(String username, String password) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("INSERT INTO %s VALUES (\"%s\", \"%s\")", CREDENTIALS_TABLE, username, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
