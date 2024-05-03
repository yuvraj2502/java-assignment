package com.service;

import java.sql.SQLException;

import com.dao.ProductDao;
import com.daoImpl.ProductDaoImpl;
import com.model.Product;

public class ProductService {

	ProductDao dao = new ProductDaoImpl();
	
	public boolean udpateInfo(Product p) throws SQLException {
		return dao.updateProductInfo(p);	
	}

	public Product displayInfo(int productId) throws SQLException{
		return dao.getProductDetails(productId);
	}

	public boolean isAvailableInStock(int productId)throws SQLException {
		return dao.isProductInStock(productId);
	}

	

}
