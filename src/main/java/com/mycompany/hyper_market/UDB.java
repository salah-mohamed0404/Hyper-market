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

    public static User login(String userName, String password) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE userName = '" + userName + "' and password = '" + password + "'";

        ResultSet res = DQLQuery(query);

        if (!res.next()) {
            return null;
        }

        return new User(
                res.getInt("id"),
                res.getNString("name"),
                res.getNString("userName"),
                res.getNString("password"),
                res.getNString("type")
        );
    }

    public static void add(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users VALUES("
                + user.id + ", '" + user.name + "', '" + user.userName + "', '" + user.password + "', '" + user.type + "')";

        DMLQuery(query);
    }

    public static void delete(String condition) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM users WHERE " + formatCondition(condition);

        DMLQuery(query);
    }

    public static void update(User user) throws SQLException, ClassNotFoundException {
        String query = "UPDATE users"
                + " SET name = '" + user.name + "'"
                + " SET userName = '" + user.userName + "'"
                + " SET password = '" + user.password + "'"
                + " SET type = '" + user.type + "'"
                + " WHERE id = " + user.id;

        DMLQuery(query);
    }

    public static ArrayList<User> search(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM users WHERE " + formatCondition(condition);

        ResultSet res = DQLQuery(query);
        
        ArrayList<User> usersRes = new ArrayList<>();
        while (res.next()) {
            usersRes.add(new User(
                    res.getInt("id"),
                    res.getNString("name"),
                    res.getNString("userName"),
                    res.getNString("password"),
                    res.getNString("type")
            ));
        }
        
        return usersRes;
    }
}
