package Database;

/**
 *
 * @author Salah
 */

import Product.Product;
import java.sql.*;
import java.util.ArrayList;

public class PDB extends DB{

    public static int add(Product product) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO products(name, price, offerPrice, expireDate, type, addedAt) VALUES("
                + "'" + product.getName() + "', "
                + product.getPrice() + ", "
                + product.getOfferPrice() + ", "
                + "'" + product.getEpireDate() + "', "
                + "'" + product.getType() + "', "
                + "'" + product.getAddedAtDate() + "' "
                + ")";

        DMLQuery(query);

        return getLastRecordIdAdded("products");
    }

    public static void delete(int productId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM products WHERE id = " + productId;

        DMLQuery(query);
    }

    public static void update(Product product) throws SQLException, ClassNotFoundException {
        String query = "UPDATE products SET "
                + " name = '" + product.getName() + "', "
                + " price = " + product.getPrice() + ", "
                + " offerPrice = " + product.getOfferPrice() + ", "
                + " expireDate = '" + product.getEpireDate() + "', "
                + " type = '" + product.getType() + "', "
                + " addedAt = '" + product.getAddedAtDate() + "' "  
                + " WHERE id = " + product.getId();

        DMLQuery(query);
    }
    
    private static ArrayList<Product> basicSearch(String query) throws SQLException, ClassNotFoundException {
        ResultSet res = DQLQuery(query);
        
        ArrayList<Product> productsRes = new ArrayList<>();
        while (res.next()) {
            productsRes.add(new Product(
                    res.getInt("id"),
                    res.getNString("name"),
                    res.getFloat("price"),
                    res.getFloat("offerPrice"),
                    res.getDate("expireDate"),
                    res.getDate("addedAt"),
                    res.getNString("type")
            ));
        }
        
        return productsRes;
    }

    public static ArrayList<Product> search(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM products WHERE orderId IS NULL and " + formatCondition(condition);

        return basicSearch(query);
    }
    
    public static ArrayList<Product> searchInOrders(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM products WHERE orderId IS NOT NULL and " + formatCondition(condition);

        return basicSearch(query);
    }
    
    public static void addProductToOrder(int productId, int orderId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE products SET orderId = " + orderId + "WHERE id = " + productId;
        
        DMLQuery(query);
    }
    
}
