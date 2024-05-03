package com.service;

import java.sql.SQLException;
import java.util.List;

import com.dao.OrderDao;
import com.daoImpl.OrderDaoImpl;
import com.dto.OrderDetailDto;

public class OrderService {

	OrderDao dao = new OrderDaoImpl();
	
	public double totalAmount(int orderId) throws SQLException {
		return dao.calculateTotalAmount(orderId);		
	}

	public List<OrderDetailDto> displayInfo(int orderId)  throws SQLException {
		return dao.getOrderDetails(orderId);
	}
	
	

}
