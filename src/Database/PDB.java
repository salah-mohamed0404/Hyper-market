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
    
    public static void removeFromOrderProductToOrder(int productId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE products SET orderId = NULL " + "WHERE id = " + productId;
        
        DMLQuery(query);
    }
    
    public static ArrayList<Product> getAlmostWeekToexpire() throws SQLException, ClassNotFoundException {
        return search("expireDate between (SELECT dateadd(WEEK, 0, getdate())) and (SELECT dateadd(WEEK, 1, getdate())) "
                + "AND type != 'damaged'");
    }
    
    public static int getQantity(String productName) throws SQLException, ClassNotFoundException {
        String query = "SELECT COUNT(name) FROM products WHERE orderId IS NULL AND type != 'damaged' AND name = '" + productName + "'";
        
        ResultSet res = DQLQuery(query);
        if(res.next()) return res.getInt(1);
        
        return -1;
    }
    
    public static ArrayList<ArrayList> getAlmostRunOut() throws SQLException, ClassNotFoundException {
        String query = "SELECT DISTINCT name FROM products WHERE orderId IS NULL AND type != 'damaged'";
        
        ResultSet uniqueProductNames = DQLQuery(query);
        
        ArrayList<ArrayList> productNameAndQantity = new ArrayList<>();
        productNameAndQantity.add(new ArrayList<String>());
        productNameAndQantity.add(new ArrayList<Integer>());
        
        while(uniqueProductNames.next()) {
            String productName = uniqueProductNames.getNString("name");
            productNameAndQantity.get(0).add(productName);
            
            int productQantity = getQantity(productName);
            productNameAndQantity.get(1).add(productQantity);
        }
        
        return productNameAndQantity;
    }
    
}
