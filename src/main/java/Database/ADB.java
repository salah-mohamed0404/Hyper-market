package Database;

import java.sql.*;
import java.util.ArrayList;

public class ADB extends DB {
    public static int add(Action action, int userId) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO users VALUES("
                + action.id + ", '" + action.action + "', " + action.createdAt + ", " + userId + ")";

        DMLQuery(query);
        
        return getId("user_actions", "id = " + userId);
    }

    public static void delete(String condition) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM user_actions WHERE " + formatCondition(condition);

        DMLQuery(query);
    }

    public static void update(Action action) throws SQLException, ClassNotFoundException {
        String query = "UPDATE users"
                + " SET action = '" + action.action + "'"
                + " WHERE id = " + action.userId;

        DMLQuery(query);
    }

    public static ArrayList<User> search(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM user_actions WHERE " + formatCondition(condition);

        ResultSet res = DQLQuery(query);
        
        ArrayList<Action> usersRes = new ArrayList<>();
        while (res.next()) {
            usersRes.add(new Action(
                    res.getInt("id"),
                    res.getNString("action"),
                    Date.valueOf(res.getNString("createdAt"))
            ));
        }
        
        return usersRes;
    }
}
