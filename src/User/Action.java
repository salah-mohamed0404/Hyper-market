package User;

/**
 *
 * @author ِAbd El-halim
 */
import Database.ADB;
import java.sql.Date;
import java.sql.SQLException;

public class Action {

    /* Data filed */
    private final int id;
    private String action;
    private final Date createdAt;

    /* constractors  */
    public Action(String action, int userId) throws SQLException, ClassNotFoundException {
        this.action = action;
        this.createdAt = new Date(System.currentTimeMillis());
        
        // this with database class 
        this.id = ADB.add(this, userId);
    }

    public Action(int id, String action, Date createdAt) {
        this.id = id;
        this.action = action;
        this.createdAt = createdAt;
    }

    /*normal mathods */

    /* only get id  */
    public int getId() {
        return this.id;
    }

    /* action mathods*/
    public String getAction() {
        return this.action;
    }

    public void setAction(String action) throws SQLException, ClassNotFoundException {
        this.action = action;
        
        // this with data base class
        ADB.update(this);
    }

    /* only get time created*/
    public Date getCreatedAt() {
        return this.createdAt;
    }

}
