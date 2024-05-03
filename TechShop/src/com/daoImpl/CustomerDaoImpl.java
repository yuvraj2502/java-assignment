package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.CustomerDao;
import com.model.Customer;
import com.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao {

	@Override
	public int calculateTotalOrders(int customerId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select count(orderId)as total_orders from customers c "
				+ "join orders o on o.customerid = c.customerid " + "where c.customerid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, customerId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			int count = rst.getInt("total_orders");
			return count;
		}

		DBConnection.dbClose();
		return 0;
	}

	@Override
	public Customer getCustomerDetails(int customerId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from customers where CustomerID = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, customerId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			int cusId = rst.getInt("customerid");
			String fname = rst.getString("firstname");
			String lname = rst.getString("lastname");
			String email = rst.getString("email");
			int phone = rst.getInt("phone");
			String address = rst.getString("address");
			Customer c = new Customer(cusId, fname, lname, email, phone, address);
			return c;
		}

		DBConnection.dbClose();
		return null;

	}

	@Override
	public boolean updateCustomerInfo(Customer updatedCustomer) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "update customers set firstname=?,lastname=?,phone=?,address=? where customerid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, updatedCustomer.getFirstName());
		pstmt.setString(2, updatedCustomer.getLastName());
		pstmt.setInt(3, updatedCustomer.getPhone());
		pstmt.setString(4, updatedCustomer.getAddress());
		pstmt.setInt(5,updatedCustomer.getCustomerId());

		boolean status = (pstmt.executeUpdate() == 1) ? true : false;

		DBConnection.dbClose();
		return status;

	}

}
