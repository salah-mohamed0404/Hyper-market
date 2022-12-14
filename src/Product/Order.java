/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Product;

/**
 *
 * @author Ahmed
 */
import Database.ODB;
import Database.PDB;
import java.util.ArrayList ;
import java.sql.Date ;
import java.sql.SQLException;
public class Order {
    //*************Fields****************//
    private int id ;
    private ArrayList<Product> products = new ArrayList<Product> () ;
    private Date createdAt ;
    private int userId ;
    //************constructors**************//

    public Order() {
        this.createdAt=new Date(System.currentTimeMillis());
    }

    public Order(ArrayList<Product> products,int userId) {
        this.products = products;
        this.userId = userId;
        this.createdAt=new Date(System.currentTimeMillis());
    }

    public Order(int id, Date createdAt, ArrayList<Product>products,int userId) {
        this.products =products;
        this.userId= userId;
        this.createdAt = createdAt;
    }

    //**************Getters*************//
    public ArrayList<Product> getProducts() {
        return products;
    }

    public Date getCreateAt() {
        return createdAt;
    }

    public int getUserId() {
        return userId;
    }
    //***************setters***************//
    public void setUserId(int userId) {
        this.userId = userId;
    }
    //***************methods****************//
    public void addproduct(int productId) throws SQLException, ClassNotFoundException {
        products.add(PDB.search("id = " + productId).get(0));
    }
    public void submitOrder() throws SQLException, ClassNotFoundException{
        this.id = ODB.add(this);
    }
}
