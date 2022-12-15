package Database;

import Product.Order;
import Product.Product;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Salah
 */
public class ODB extends DB{
    public static int add(Order order) throws SQLException, ClassNotFoundException {
        if(order.getProducts().isEmpty()) ;
        
        String query = "INSERT INTO orders(createdAt, userId) "
                + "values (" + order.getCreateAt() + ", " + order.getUserId() + ")";
        
        DMLQuery(query);
        
        int orderId = getLastRecordIdAdded("orders");
        
        ArrayList<Product> orderProducts = order.getProducts();
        for (int i = 0; i < orderProducts.size(); i++) {
            PDB.addProductToOrder(orderProducts.get(i).getId(), orderId);
        }
        
        return orderId;
    }
    
    public static void delete(int orderId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM orders WHERE id = " + orderId;

        DMLQuery(query);
    }
    
    private static ArrayList<Order> basicSearch(String query) throws SQLException, ClassNotFoundException {
        ResultSet res = DQLQuery(query);
        
        ArrayList<Order> ordersRes = new ArrayList<>();
        while(res.next()) {
            int orderId = res.getInt("id");
            
            ordersRes.add(new Order(
                    orderId,
                    res.getDate("createdAt"),
                    PDB.searchInOrders("orderId = " + orderId),
                    res.getInt("userId")));
        }
        
        return ordersRes;
    }
    
    public static ArrayList<Order> search(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM orders WHERE isCanceled = 0 and " + formatCondition(condition);

        return basicSearch(query);
    }
    
    public static ArrayList<Order> searchCanceledOrders(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM orders WHERE isCanceled = 1 and " + formatCondition(condition);

        return basicSearch(query);
    }
    
    public static void cancelOrder(int orderId) throws SQLException, ClassNotFoundException {
        String query = "UPDATE orders SET isCanceled = 1 WHERE id = " + orderId;
        
        DMLQuery(query);
    }
    
}
