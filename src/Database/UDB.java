package Database;

/**
 *
 * @author Salah
 */

import User.Action;
import User.User;
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
                res.getNString("type"),
                ADB.search("userId = " + res.getInt("id"))
        );
    }

    public static void add(User user) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users (id, name, userName, password, type) "
                + "VALUES("
                + user.getId() + ", " 
                + "'" + user.getName() + "', " 
                + "'" + user.getUserName() + "', " 
                + "'" + user.getPassword() + "', " 
                + "'" + user.getType() +"'"
                + ")";

        DMLQuery(query);
        
        if(user.getActions().isEmpty()) return;
        
        ArrayList<Action> actions = user.getActions();
        int userId = user.getId();
        for(int i = 0; i < actions.size(); i++) {
            ADB.add(actions.get(i), userId);
        }
    }

    public static void delete(String condition) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM users WHERE " + formatCondition(condition);

        DMLQuery(query);
    }

    public static void update(User user) throws SQLException, ClassNotFoundException {
        String query = "UPDATE users SET "
                + " name = '" + user.getName() + "', "
                + " userName = '" + user.getUserName() + "', "
                + " password = '" + user.getPassword() + "', "
                + " type = '" + user.getType() + "'"
                + " WHERE id = " + user.getId();

        DMLQuery(query);
        
        if(user.getActions().isEmpty()) return;
        
        ArrayList<Action> actions = user.getActions();
        for(int i = 0; i < actions.size(); i++) {
            ADB.update(actions.get(i));
        }
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
                    res.getNString("type"),
                    ADB.search("userId = " + res.getInt("id"))
            ));
        }
        
        return usersRes;
    }
}
