package com.dao;

import java.sql.SQLException;
import java.util.List;

import com.dto.OrderDetailDto;

public interface OrderDao {
	double calculateTotalAmount(int orderId) throws SQLException;

	List<OrderDetailDto> getOrderDetails(int orderId) throws SQLException;
}
