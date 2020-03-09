package util;

import java.sql.*;
import java.util.HashMap;

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
    private static HashMap<String, String> dbVersionNameMap = new HashMap<String, String>();

    private static String dbVersionName = "v1:vdsdb,v2:trap_v2_060117,v3:trap_v3";

    public static void init() throws Exception {
        if (connection == null || connection.isClosed()) {
            initDBVersion();

            initDataFromSystemConfig();
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
            statement = connection.createStatement();
        } else if (statement.isClosed()) {
            statement = connection.createStatement();
        }
    }

    private static void initDBVersion() {
        for (String str : dbVersionName.split(",")) {
            String version = str.split(":")[0];
            String name = str.split(":")[1];

            dbVersionNameMap.put(version, name);
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
//            dbUrl = "jdbc:mysql://localhost:3306/trap_v3";
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

    public static String getDBName(String dbVersion) {
        return dbVersionNameMap.get(dbVersion);
    }
}
