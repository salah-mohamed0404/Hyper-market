package Product;

/**
 *
 * @author Abd El-Halim
 */
import Database.PDB;
import java.sql.Date;
import java.sql.SQLException;

public class Product {

    /* Data field     */
    private int id;
    private String name;
    private double price;
    private double offerPrice = -1;
    private Date expireDate;
    private Date addedAt;
    private String type;

    /*  constractors  */

    /* constractor with name && price && expireDate  */
    public Product(String name, double price, Date expireDate, String type) throws SQLException, ClassNotFoundException {
        this.name = name;
        this.price = price;
        this.expireDate = expireDate;
        this.addedAt = new Date(System.currentTimeMillis());
        this.type = type;

        // this with data base 
        this.id = PDB.add(this);
    }

    public Product(int id, String name, double price, double offerPrice, Date expireDate, Date addedAt, String type) 
            throws SQLException, ClassNotFoundException {
        this.id = id;
        this.name = name;
        this.price = price;
        this.offerPrice = offerPrice;
        this.expireDate = expireDate;
        this.addedAt = addedAt;
        this.type = type;
    }

    /* the normal methods */

    /* Id methods */
    public int getId() {
        return this.id;
    }

    /*name mathods */
    public String getName() {
        return this.name;
    }

    public void setName(String name) throws SQLException, ClassNotFoundException {
        this.name = name;
        // this to updata at data base
         PDB.update(this);
    }

    /*price methods */
    public double getPrice() {
        return this.price;
    }
    
    public void setPrice(double price) throws SQLException, ClassNotFoundException {
        this.price = price;
        // this to updata at data base
         PDB.update(this);
    }

    /*offer price */
    public double getOfferPrice() {
        return this.offerPrice;
    }

    public void setOfferPrice(double offerPrice) throws SQLException, ClassNotFoundException {
        this.offerPrice = offerPrice;
        // this to updata at data base
         PDB.update(this);
    }

    /* only get expire Date*/
    public Date getEpireDate() {
        return this.expireDate;
    }

    /*only get add Date */
    public Date getAddedAtDate() {
        return this.addedAt;
    }
    /* type mathods*/

    public String getType() {
        return this.type;
    }

    public void setType(String type) throws SQLException, ClassNotFoundException {
        this.type = type;
        // this to updata at data base
         PDB.update(this);
    }
}
