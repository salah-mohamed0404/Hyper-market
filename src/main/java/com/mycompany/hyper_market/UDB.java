package com.mycompany.hyper_market;

import java.sql.*;
import java.util.ArrayList;

public class UDB extends DB {

    public static boolean isUserNameUnique(String userName) throws SQLException, ClassNotFoundException {
        String query = "SELECT userName FROM users WHERE userName = '" + userName + "'";

        return isUnique(query);
    }

    public static boolean isUserIdUnique(int userId) throws SQLException, ClassNotFoundException {
        String query = "SELECT id FROM users WHERE id = '" + userId + "'";

        return isUnique(query);
    }
    
}
