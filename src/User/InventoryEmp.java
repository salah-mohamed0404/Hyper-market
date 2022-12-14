package User;

/**
 *
 * @author Ahmed
 */

import Database.PDB;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import Product.Product;

public class InventoryEmp extends User {

    public InventoryEmp() {
    }

    public InventoryEmp(int userId, String name, String userName, String password, String type) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type);
    }

    public InventoryEmp(int userId, String name, String userName, String password, String type, ArrayList<Action> actions) throws SQLException, ClassNotFoundException {
        super(userId, name, userName, password, type, actions);
    }

    public Product addProduct(String name, double price, Date expireDate, String type) throws SQLException, ClassNotFoundException {
        return new Product(name, price, expireDate, type);
    }

    public void deleteProduct(int productId) throws SQLException, ClassNotFoundException {
        PDB.delete(productId);
    }

    public void updateProduct(Product product) throws SQLException, ClassNotFoundException {
        PDB.update(product);
    }

    public ArrayList<Product> listProducts() throws SQLException, ClassNotFoundException {
        return PDB.search("*");
    }

    public ArrayList<Product> listProducts(String type) throws SQLException, ClassNotFoundException {
        return PDB.search("type = " + type);
    }

    public Product searchProduct(int productId) throws SQLException, ClassNotFoundException {
        return PDB.search("id = " + productId).get(0);
    }

    public ArrayList<Product> searchProducts(String condition) throws SQLException, ClassNotFoundException {
        return PDB.search(condition);
    }

    public void returnProduct(Product product) throws SQLException, ClassNotFoundException {
        product.setType("returned");
    }

    public void getRidOfDamageProduct(Product product) throws SQLException, ClassNotFoundException {
        product.setType("damaged");
    }
}
