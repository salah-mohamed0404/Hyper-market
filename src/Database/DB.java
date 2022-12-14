package Database;

/**
 *
 * @author Salah
 */

import java.sql.*;

public abstract class DB {

    final private static String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final private static String CONNICTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=HyperMarket;encrypt=true;trustServerCertificate=true;";
    final private static String USER_NAME = "sa";
    final private static String PASSWORD = "1234";

    protected static Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName(CLASS_NAME);
        return DriverManager.getConnection(CONNICTION_URL, USER_NAME, PASSWORD);
    }

    protected static void DMLQuery(String query) throws SQLException, ClassNotFoundException {
        Connection conn = connect();
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
    }

    protected static ResultSet DQLQuery(String query) throws SQLException, ClassNotFoundException {
        Connection conn = connect();
        PreparedStatement stmt = conn.prepareStatement(query);

        return stmt.executeQuery();
    }

    protected static boolean isUnique(String query) throws SQLException, ClassNotFoundException {
        ResultSet r = DQLQuery(query);

        return !r.next();
    }

    protected static String formatCondition(String condition) {
        return condition.trim().equals("*") ? "id > -1" : condition;
    }

    protected static int getId(String tableName, String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT id FROM " + tableName + " WHERE " + condition;
        ResultSet res = DQLQuery(query);
        res.next();

        return res.getInt("id");
    }

    protected static int getLastRecordIdAdded(String tableName) throws SQLException, ClassNotFoundException {
        String query = "SELECT id FROM " + tableName
                + " ORDER BY id DESC"
                + " OFFSET 0 ROWS FETCH FIRST 1 ROW ONLY";
        
        ResultSet res = DQLQuery(query);
        res.next();

        return res.getInt("id");
    }
}
