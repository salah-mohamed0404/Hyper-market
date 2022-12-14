package Database;

/**
 *
 * @author Salah
 */

import User.Action;
import java.sql.*;
import java.util.ArrayList;

public class ADB extends DB {

    public static int add(Action action, int userId) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO user_actions ([action], createdAt, userId) "
                + "VALUES("
                + "'" + action.getAction() + "', "
                + "'" + action.getCreatedAt() + "', "
                + userId
                + ")";

        DMLQuery(query);

        return getLastRecordIdAdded("user_actions");
    }

    public static void delete(String condition) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM user_actions WHERE " + formatCondition(condition);

        DMLQuery(query);
    }

    public static void update(Action action) throws SQLException, ClassNotFoundException {
        String query = "UPDATE user_actions"
                + " SET action = '" + action.getAction() + "'"
                + " WHERE id = " + action.getId();

        DMLQuery(query);
    }

    public static ArrayList<Action> search(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM user_actions WHERE " + formatCondition(condition);

        ResultSet res = DQLQuery(query);

        ArrayList<Action> usersRes = new ArrayList<>();
        while (res.next()) {
            usersRes.add(new Action(
                    res.getInt("id"),
                    res.getNString("action"),
                    res.getDate("createdAt")
            ));
        }

        return usersRes;
    }
}
