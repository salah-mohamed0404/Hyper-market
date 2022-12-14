package User;

/**
 *
 * @author ِAhmed
 */
import Database.PDB;
import Product.Product;
import java.sql.SQLException;
import java.util.ArrayList;

public class MarketingEmp extends User {
    public MarketingEmp() {
    }
    public MarketingEmp(int userId, String name, String userName, String password, String type) throws SQLException, ClassNotFoundException {
        super(userId,name,userName,password,type);
    }
    public MarketingEmp(int userId, String name, String userName, String password, String type, ArrayList<Action> actions) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type, actions);
    }
    public ArrayList<Product> getProductsReport(String filter) throws SQLException, ClassNotFoundException{
        return PDB.search(filter);
    }
    public void MakeSpecialOffer(int productId ,double offerPrice) throws SQLException, ClassNotFoundException {
        PDB.search("id = " + productId).get(0).setOfferPrice(offerPrice);
    }
}
