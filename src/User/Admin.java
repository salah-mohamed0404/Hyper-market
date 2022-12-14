package User;

/**
 *
 * @author Abd El-Halim
 */
import Database.UDB;
import java.sql.SQLException;
import java.util.ArrayList;

public class Admin extends User {

    /* constractor */
    public Admin(int userId, String name, String userName, String password, String type) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type);
    }

    public Admin(int userId, String name, String userName, String password, String type, ArrayList<Action> actions) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type, actions);
    }

    /* Admin mathods   */

 /* update Admin mathod   */
    public void update(String userName, String password) throws SQLException, ClassNotFoundException {
        setName(userName);
        setPassword(password);
        // this will matche with data dase in the super class
    }

    /* adduser mathod */
    public void addUser(int userId, String name, String userName, String password, String type) throws SQLException, ClassNotFoundException {
        UDB.add(new User(userId, name, userName, password, type, new ArrayList<>()));
    }

    /*deleteuser */

    public void deleteUser(int userId) throws SQLException, ClassNotFoundException {
        // this from UDB
        UDB.delete("id = " + userId);

    }

    /* updateuser  */
    public void updetUser(User user) throws SQLException, ClassNotFoundException {
        // this from UDB
        UDB.update(user);
    }

    /*list users  */
    public ArrayList<User> listUsers() throws SQLException, ClassNotFoundException {
        return UDB.search("*");
    }

    /*list users  */
    public ArrayList<User> listUsers(String type) throws SQLException, ClassNotFoundException {
        return UDB.search("type = " + type);
    }

    /* search user   */
    public ArrayList<User> searchUser(String condition) throws SQLException, ClassNotFoundException {
        return UDB.search(condition);
    }

}
