package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.InventoryDao;
import com.model.Product;
import com.utility.DBConnection;

public class InventoryDaoImpl implements InventoryDao {

	@Override
	public Product getProduct(int inventoryId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select p.* from inventory i join products p on i.productid=p.productid where inventoryid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, inventoryId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			int prodId = rst.getInt("productid");
			String name = rst.getString("name");
			String description = rst.getString("description");
			double price = rst.getDouble("price");

			Product p = new Product(prodId, name, description, price);
			return p;
		}

		DBConnection.dbClose();
		return null;
	}

	@Override
	public int getQuantityInStock(int inventoryId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select quantity from inventory where inventoryid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, inventoryId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			int quantity = rst.getInt("quantity");
			return quantity;
		}

		DBConnection.dbClose();
		return 0;
	}

	@Override
	public boolean updateStockQuantity(int newQuantity, int productId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "update inventory set quantity=? inventoryid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, newQuantity);
		pstmt.setInt(2, productId);

		boolean status = (pstmt.executeUpdate() == 1) ? true : false;

		DBConnection.dbClose();
		return status;
	}

	@Override
	public List<Product> listLowStockProducts(int threshold) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select p.* from inventory i join products p on "
				+ "p.productid = i.productid where i.quantityinstock < ?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, threshold);
		ResultSet rst = pstmt.executeQuery();

		List<Product> list = new ArrayList<>();
		while (rst.next()) {
			int proId = rst.getInt("productid");
			String productName = rst.getString("productname");
			double price = rst.getDouble("price");
			String description = rst.getString("description");

			Product p = new Product(proId, productName, description, price);
			list.add(p);
		}

		DBConnection.dbClose();
		return list;
	}

	@Override
	public List<Product> listOutOfStockProducts() throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select p.* from inventory i join products p on "
				+ "p.productid = i.productid where i.quantityinstock = 0";

		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();

		List<Product> list = new ArrayList<>();
		while (rst.next()) {
			int proId = rst.getInt("productid");
			String productName = rst.getString("productname");
			double price = rst.getDouble("price");
			String description = rst.getString("description");

			Product p = new Product(proId, productName, description, price);
			list.add(p);
		}

		DBConnection.dbClose();
		return list;

	}

	@Override
	public double getInventoryValue(int productId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select price*quantityinstock from inventory i join products p "
				+ "on p.productid = i.productid where p.productid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, productId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			int totalValue = rst.getInt("quantity");
			return totalValue;
		}

		DBConnection.dbClose();
		return 0;
	}

}
