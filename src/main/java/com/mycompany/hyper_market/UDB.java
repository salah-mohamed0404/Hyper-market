package com.mycompany.hyper_market;

import java.sql.*;
import java.util.ArrayList;

public class UDB extends DB {

    static public boolean isUserNameUnique(String userName) throws SQLException, ClassNotFoundException {
        String query = "SELECT userName FROM users WHERE userName = '" + userName + "'";

        return isUnique(query);
    }

    static public boolean isUserIdUnique(int userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT id FROM users WHERE id = '" + userId + "'";

        return isUnique(query);
    } 
    
    static public String login(String userName, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE userName = '" + userName + "' and password = '" + password + "'";
        
        ResultSet res = DQLQuery(query);
        
        if(!res.next()) return null;
        
        return "" + res.getInt("id") + " " + res.getNString("name") + " " + res.getNString("userName")
                + " " + res.getNString("password") + " " + res.getNString("type");
        
    }
}
