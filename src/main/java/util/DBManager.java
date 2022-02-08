package util;

import java.sql.*;

/**
 *
 * @author nick
 */
public class DBManager {

    private static Connection connection;
    private static Statement statement;

    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    public static void init() throws Exception {
        if (connection == null || connection.isClosed()) {
 
            initDataFromSystemConfig();
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            statement = connection.createStatement();
        } else if (statement.isClosed()) {
            statement = connection.createStatement();
        }
    }

    private static void initDataFromSystemConfig() {
        try {
            // aws server config
            dbUrl = System.getProperty("TRAP_DB_URL");
            dbUser = System.getProperty("TRAP_DB_USER");
            dbPassword = System.getProperty("TRAP_DB_PASSWORD");

            // local config
//             the config below is for MySQL 8
//            dbUrl = "jdbc:mysql://127.0.0.1:3306/trap_v3?serverTimezone=UTC";
//            dbUser = "test";
//            dbPassword = "test";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sqlQuery) throws SQLException {
        return statement.executeQuery(sqlQuery);
    }

    public static PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
        return connection.prepareStatement(sqlQuery);
    }

    public static String getDBName(String version) {
        if(version.equals("hg19")) {
            return "trap_v3";
        } else {
            return "trap_v3_hg38";
        }
    }
}
