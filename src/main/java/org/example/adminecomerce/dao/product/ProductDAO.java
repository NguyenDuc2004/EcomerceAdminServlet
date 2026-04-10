package org.example.adminecomerce.dao.product;

import org.example.adminecomerce.dbConnection.DbConnection;
import org.example.adminecomerce.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements IProductDAO {
    public static final String SELECT_LIST_PRODUCT = "SELECT p.*,c.name AS category_name FROM products p JOIN categories c ON p.category_id = c.id;";

    @Override
    public List<Product> getAllProduct() {
        List<Product> products = new ArrayList<>();
        try(Connection conn = DbConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(SELECT_LIST_PRODUCT)
        ) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                double discountPrice = rs.getDouble("discount_price");
                String image = rs.getString("image");
                int stockQuantity = rs.getInt("stock_quantity");
                int categoryId = rs.getInt("category_id");
                String categoryName = rs.getString("category_name");
                int status = rs.getInt("status");
                String createdAt = rs.getString("created_at");
                Product product = new Product(id, name, description, price, discountPrice,image,stockQuantity,
                        categoryId, categoryName,status, createdAt);
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
