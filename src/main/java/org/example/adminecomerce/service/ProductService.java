package org.example.adminecomerce.service;

import org.example.adminecomerce.dao.product.IProductDAO;
import org.example.adminecomerce.dao.product.ProductDAO;
import org.example.adminecomerce.model.Product;

import java.util.List;

public class ProductService {
    IProductDAO productDAO = new ProductDAO();

    public List<Product> getListProduct(){
        return productDAO.getAllProduct();
    }
}
