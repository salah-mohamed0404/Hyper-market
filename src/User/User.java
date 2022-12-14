package User;

import Database.ADB;
import Database.UDB;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Abd El-halim
 */
public abstract class User {

    /* data field */
    private int id;
    private String name;
    private String userName;
    private String password;
    // this array from class Action 
    private final ArrayList<Action> actions;
    private String type;

    /*constractors  */

 /* no_arg constractor  */
    public User() {
        this.actions = new ArrayList<>();

    }

    /* constractor with user name && password && userId */
    public User(int userId, String name, String userName, String password, String type) throws SQLException, ClassNotFoundException {
        this.id = userId;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.actions = new ArrayList<>();

        // this with data base
        UDB.add(this);
    }

    public User(int userId, String name, String userName, String password, String type, ArrayList<Action> actions) {
        this.id = userId;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.type = type;
        this.actions = actions;
    }

    /* normal methods */
 /* user name mathods*/
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) throws SQLException, ClassNotFoundException {
        this.userName = userName;
        // this with data base
        UDB.update(this);
    }

    /* user id methods */
    public int getId() {
        return this.id;
    }

    public void setId(int userId) throws SQLException, ClassNotFoundException {
        this.id = userId;
        // this with data base
        UDB.update(this);
    }

    /* user password methods */
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) throws SQLException, ClassNotFoundException {
        this.password = password;
        // this with data base
        UDB.update(this);
    }

    /* type mathods */
    public String getType() {
        return this.type;
    }

    public void setType(String type) throws SQLException, ClassNotFoundException {
        this.type = type;
        // this with data base
        UDB.update(this);
    }

    /*  name mathods*/
    public String getName() {
        return this.name;
    }

    public void setName(String name) throws SQLException, ClassNotFoundException {
        this.name = name;
        // this with data base
        UDB.update(this);
    }

    /* Action mathod  */
    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public void addAction(String action) throws SQLException, ClassNotFoundException {
        this.actions.add(new Action(action, id));
    }

}
