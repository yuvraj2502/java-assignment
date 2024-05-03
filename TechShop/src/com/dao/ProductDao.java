package com.dao;

import java.sql.SQLException;

import com.model.Product;

public interface ProductDao {
	Product getProductDetails(int productId) throws SQLException;

	boolean updateProductInfo(Product updatedProduct) throws SQLException;

	boolean isProductInStock(int productId) throws SQLException;
}
