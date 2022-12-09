package com.mycompany.hyper_market;

import java.sql.*;

final public class DB {
    final static private String CLASS_NAME = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    final static private String CONNICTION_URL = "jdbc:sqlserver://localhost:1433;databaseName=HyperMarket;encrypt=true;trustServerCertificate=true;";
    final static private String USER_NAME = "sa";
    final static private String PASSWORD = "1234";
    
    static private Connection connect() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(CONNICTION_URL, USER_NAME, PASSWORD);
        } catch (Exception e) {
            return null;
        }
    }
    
    static private boolean DMLQuery(String query) {
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
        
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
            return false;
        }
    }
    
    static private ResultSet DQLQuery(String query) {
        try {
            Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);

            return stmt.executeQuery();
        } catch (Exception e) {
            System.out.println(e.getMessage()); 
            return null;
        }
    }
    
    static public boolean isUserNameUnique(String userName) {
        String query = "SELECT userName FROM users WHERE userName = '" + userName + "'";
        
        try {
            ResultSet r = DQLQuery(query);
            return !r.next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
