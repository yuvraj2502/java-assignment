package com.dao;

import java.sql.SQLException;

import com.dto.OrderDetailDto;

public interface OrderDetailDao {
	double calculateSubTotal(int orderDetailId) throws SQLException;

	OrderDetailDto getOrderDetailInfo(int orderDetailId) throws SQLException;

	
	boolean updateQuantity(int orderDetailId, int quantity) throws SQLException;
}
