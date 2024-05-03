package com.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dao.OrderDetailDao;
import com.dto.OrderDetailDto;
import com.utility.DBConnection;

public class OrderDetailDaoImpl implements OrderDetailDao {

	@Override
	public double calculateSubTotal(int orderDetailId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select p.price*od.quantity as total_price from orderdetails od join "
				+ "products p on p.productid=od.productid where orderdetailid=?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, orderDetailId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			double totalPrice = rst.getDouble("total_price");
			return totalPrice;
		}

		DBConnection.dbClose();
		return 0;
	}

	@Override
	public OrderDetailDto getOrderDetailInfo(int orderDetailId) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "select * from products p join orderdetails od on od.productid=p.productid where orderDetailId = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, orderDetailId);
		ResultSet rst = pstmt.executeQuery();

		if (rst.next()) {
			String productName = rst.getString("productname");
			int quantity = rst.getInt("quantity");

			OrderDetailDto orderDetail = new OrderDetailDto(productName, quantity);
			return orderDetail;
		}

		DBConnection.dbClose();
		return null;
	}

	@Override
	public boolean updateQuantity(int orderDetailId, int quantity) throws SQLException {
		Connection con = DBConnection.dbConnect();
		String sql = "update orderdetails set quantity=? where orderdetailid=?";
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, quantity);
		pstmt.setInt(2, orderDetailId);

		boolean status = (pstmt.executeUpdate() == 1) ? true : false;

		DBConnection.dbClose();
		return status;

	}

}
