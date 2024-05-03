package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.OrderDao;
import com.dto.OrderDetailDto;
import com.utility.DBConnection;

public class OrderDaoImpl implements OrderDao {

	@Override
	public double calculateTotalAmount(int orderId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select sum(p.price * od.quantity) as total_price " + "from orderdetails od join "
				+ "products p on p.productid = od.productid " + "where od.orderid=?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, orderId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			double totalPrice = rst.getDouble("total_price");
			return totalPrice;
		}

		DBConnection.dbClose();
		return 0;
	}

	@Override
	public List<OrderDetailDto> getOrderDetails(int orderId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = " select p.productname,od.quantity from orderdetails od join products "
				+ "p on p.productid=od.productid where orderid = 1";

		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rst = pstmt.executeQuery();

		List<OrderDetailDto> list = new ArrayList<>();
		while (rst.next()) {
			String productName = rst.getString("productname");
			int quantity = rst.getInt("quantity");

			OrderDetailDto orderDetails = new OrderDetailDto(productName, quantity);
			list.add(orderDetails);
		}

		DBConnection.dbClose();
		return list;
	}



}
