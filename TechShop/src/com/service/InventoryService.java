package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.InventoryDao;
import com.daoImpl.InventoryDaoImpl;
import com.model.Product;

public class InventoryService {

	InventoryDao dao = new InventoryDaoImpl();

	public Product getProduct(int inventoryId) throws SQLException {
		return dao.getProduct(inventoryId);
	}

	public int getQuantity(int productId) throws SQLException {
		return dao.getQuantityInStock(productId);
	}

	public boolean updateQuantity(int quantity, int productId) throws SQLException {
		return dao.updateStockQuantity(quantity, productId);
	}

	public double calculateTotalValue(int productId) throws SQLException {
		return dao.getInventoryValue(productId);
	}

	public List<Product> lowStockProduct(int threshold) throws SQLException {
		return dao.listLowStockProducts(threshold);
	}

	public List<Product> outOfStockProduct() throws SQLException {
		return dao.listOutOfStockProducts();
	}

}
