package com.service;

import java.sql.SQLException;

import com.dao.OrderDetailDao;
import com.daoImpl.OrderDetailDaoImpl;
import com.dto.OrderDetailDto;

public class OrderDetailService {

	OrderDetailDao dao = new OrderDetailDaoImpl();

	public double getAmount(int orderDetailId) throws SQLException {
		return dao.calculateSubTotal(orderDetailId);
	}

	public OrderDetailDto displayInfo(int orderDetailId) throws SQLException {
		return dao.getOrderDetailInfo(orderDetailId);
	}

	public boolean updateQuantity(int quantity, int orderDetailId) throws SQLException {
		return dao.updateQuantity(orderDetailId, quantity);
	}

}
