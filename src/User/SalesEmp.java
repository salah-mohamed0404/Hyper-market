package User;

/**
 *
 * @author Ahmed
 */
import Database.ODB;
import Database.PDB;
import Product.Order;
import Product.Product;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalesEmp extends User {

    public SalesEmp() {
    }

    public SalesEmp(int userId, String name, String userName, String password, String type) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type);
    }
    
    public SalesEmp(int userId, String name, String userName, String password, String type, ArrayList<Action> actions) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type, actions);
    }

    public Product sreachProduct(int productId) throws SQLException, ClassNotFoundException {
        return PDB.search("id = " + productId).get(0);
    }
    
    public ArrayList<Product> sreachProduct(String condition) throws SQLException, ClassNotFoundException {
        return PDB.search(condition);
    }

    public ArrayList<Product> listProducts() throws SQLException, ClassNotFoundException {
        return PDB.search("*");
    }

    public ArrayList<Product> listProducts(String name) throws SQLException, ClassNotFoundException {
        return PDB.search("name = " + name);
    }

    public void makeOrder(Order order) throws SQLException, ClassNotFoundException {
        order.submitOrder();
    }

    public void cancelOrder(int orderId) throws SQLException, ClassNotFoundException {
        ODB.cancelOrder(orderId);
    }

    public ArrayList<Order> listOrders() throws SQLException, ClassNotFoundException {
        return ODB.search("*");
    }

}
