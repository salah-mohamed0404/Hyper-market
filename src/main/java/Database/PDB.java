package Database;

import java.sql.*;
import java.util.ArrayList;

public class PDB extends DB{

    public static int add(Product product) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO products VALUES("
                + "'" + product.name + "', "
                + product.price + ", "
                + product.offerPrice + ", "
                + "'" + product.expireDate + "', "
                + "'" + product.type + "', "
                + "'" + product.addedAt + "', "
                + ")";

        DMLQuery(query);

        return getLastRecordIdAdded("products");
    }

}
