package util;

import java.sql.*;
import java.util.HashMap;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

/**
 *
 * @author nick
 */
public class DBManager {

    private static DataSource dataSource;

    public static String dbVersion;
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;
    private static HashMap<String, String> dbVersionNameMap = new HashMap<String, String>();

    private static String dbVersionName = "v1:vdsdb,v2:trap_v2_060117,v3:trap_v3";

    public static void init() throws Exception {
        initDBVersion();

        initDataFromSystemConfig();

        if (dataSource == null) {
            PoolProperties p = new PoolProperties();
            p.setDriverClassName("com.mysql.cj.jdbc.Driver");
            p.setUrl(dbUrl);
            p.setUsername(dbUser);
            p.setPassword(dbPassword);
            p.setJmxEnabled(true);
            p.setTestWhileIdle(false);
            p.setTestOnBorrow(true);
            p.setValidationQuery("SELECT 1");
            p.setTestOnReturn(false);
            p.setValidationInterval(30000);
            p.setTimeBetweenEvictionRunsMillis(30000);
            p.setMaxActive(100);
            p.setInitialSize(10);
            p.setMaxWait(10000);
            p.setRemoveAbandonedTimeout(60);
            p.setMinEvictableIdleTimeMillis(30000);
            p.setMinIdle(10);
            p.setLogAbandoned(true);
            p.setRemoveAbandoned(true);
            p.setJdbcInterceptors(
                    "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
                    + "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
            dataSource = new DataSource();
            dataSource.setPoolProperties(p);
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
//            dbUrl = "jdbc:mysql://localhost:3306/trap_v3?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=GMT";
//            dbUser = "test";
//            dbPassword = "test";
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(String sqlQuery) throws SQLException {
        return dataSource.getConnection().createStatement().executeQuery(sqlQuery);
    }

    public static PreparedStatement prepareStatement(String sqlQuery) throws SQLException {
        return dataSource.getConnection().prepareStatement(sqlQuery);
    }

    public static String getDBName() {
        return dbVersionNameMap.get(dbVersion);
    }
}
