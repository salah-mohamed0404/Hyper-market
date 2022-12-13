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

}
