package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.model.Product;

public interface InventoryDao {
	Product getProduct(int productId) throws SQLException;

	int getQuantityInStock(int productId) throws SQLException;

	boolean updateStockQuantity(int newQuantity, int productId) throws SQLException;

	List<Product> listLowStockProducts(int threshold) throws SQLException;

	List<Product> listOutOfStockProducts() throws SQLException;

	double getInventoryValue(int productId) throws SQLException;
}
