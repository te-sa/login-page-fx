import java.sql.*;

public class DataSource {
    public static final String DB_NAME = "login-page-fx.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:" + DB_NAME;

    public static final String CREDENTIALS_TABLE = "credentials";

    public static final String CREDENTIALS_ID_COLUMN = "_id";
    public static final String CREDENTIALS_USERNAME_COLUMN = "username";
    public static final String CREDENTIALS_PASSWORD_COLUMN = "password";

    public static final String LOGIN_INFO_TABLE = "login_info";

    public static final String LOGIN_INFO_ID_COLUMN = "_id";
    public static final String LOGIN_INFO_TIMES_LOGGED_IN_COLUMN = "times_logged_in";

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
                    CREDENTIALS_TABLE, CREDENTIALS_USERNAME_COLUMN, CREDENTIALS_PASSWORD_COLUMN, username, password));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean usernameExists(String username) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = \"%s\"",
                     CREDENTIALS_USERNAME_COLUMN, CREDENTIALS_TABLE, CREDENTIALS_USERNAME_COLUMN, username))) {
            // is there a way to do this without a ResultSet?
            return resultSet.isBeforeFirst(); // https://stackoverflow.com/questions/867194/java-resultset-how-to-check-if-there-are-any-results
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean passwordMatches(String username, String password) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = \"%s\"",
                     CREDENTIALS_PASSWORD_COLUMN, CREDENTIALS_TABLE, CREDENTIALS_USERNAME_COLUMN, username))) {
            return password.equals(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int getId(String username) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(String.format("SELECT %s FROM %s WHERE %s = \"%s\"",
                     CREDENTIALS_ID_COLUMN, CREDENTIALS_TABLE, CREDENTIALS_USERNAME_COLUMN, username))) {
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public int getTimesLoggedIn(String username) {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(
                     String.format("SELECT %s FROM %s INNER JOIN %s ON %s.%s = %s.%s WHERE %s = \"%s\"",
                             LOGIN_INFO_TIMES_LOGGED_IN_COLUMN, CREDENTIALS_TABLE, LOGIN_INFO_TABLE,
                             CREDENTIALS_TABLE, CREDENTIALS_ID_COLUMN, LOGIN_INFO_TABLE, LOGIN_INFO_ID_COLUMN,
                             CREDENTIALS_USERNAME_COLUMN, username))) {
            return resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void setTimesLoggedIn(String username) {
        try (Statement statement = connection.createStatement()) {
            statement.execute(String.format("UPDATE %s SET %s = %s + 1 WHERE %s = (SELECT %s FROM %s WHERE %s = \"%s\")",
                    LOGIN_INFO_TABLE, LOGIN_INFO_TIMES_LOGGED_IN_COLUMN, LOGIN_INFO_TIMES_LOGGED_IN_COLUMN,
                    LOGIN_INFO_ID_COLUMN, CREDENTIALS_ID_COLUMN, CREDENTIALS_TABLE, CREDENTIALS_USERNAME_COLUMN, username));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
