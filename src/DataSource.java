import java.sql.*;

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
            statement.execute(String.format("INSERT INTO %s (%s, %s) VALUES (\"%s\", \"%s\")",
                    CREDENTIALS_TABLE, USERNAME_COLUMN, PASSWORD_COLUMN, username, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String username) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = \"%s\"",
                     USERNAME_COLUMN, CREDENTIALS_TABLE, USERNAME_COLUMN, username))) {
            // is there a way to do this without a ResultSet?
            return resultSet.isBeforeFirst(); // https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean passwordMatches(String username, String password) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = \"%s\"",
                    PASSWORD_COLUMN, CREDENTIALS_TABLE, USERNAME_COLUMN, username));
            return password.equals(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
