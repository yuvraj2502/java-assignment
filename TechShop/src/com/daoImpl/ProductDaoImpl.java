package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.ProductDao;
import com.model.Product;
import com.utility.DBConnection;

public class ProductDaoImpl implements ProductDao {

	@Override
	public Product getProductDetails(int productId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from products where productid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, productId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			int proId = rst.getInt("productid");
			String productName = rst.getString("productname");
			double price = rst.getDouble("price");
			String description = rst.getString("description");

			Product p = new Product(proId, productName, description, price);
			return p;
		}

		DBConnection.dbClose();
		return null;
	}

	@Override
	public boolean updateProductInfo(Product updatedProduct) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "update products set productname=?,description=?,price=? where productId=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		pstmt.setString(1, updatedProduct.getProductName());
		pstmt.setString(2, updatedProduct.getDescription());
		pstmt.setDouble(3, updatedProduct.getPrice());
		pstmt.setInt(4, updatedProduct.getProductId());
		boolean status = (pstmt.executeUpdate() == 1) ? true : false;

		DBConnection.dbClose();
		return status;

	}

	@Override
	public boolean isProductInStock(int productId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select inventoryid from inventory where productid = ? and quantityinstock>0;";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, productId);

		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			return true;
		}

		DBConnection.dbClose();
		return false;
	}

}
