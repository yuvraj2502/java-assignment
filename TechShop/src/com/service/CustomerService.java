package com.service;

import java.sql.SQLException;

import com.dao.CustomerDao;
import com.daoImpl.CustomerDaoImpl;
import com.model.Customer;

public class CustomerService {
	CustomerDao dao = new CustomerDaoImpl();

	public int allOrders(int customerId) throws SQLException {
		return dao.calculateTotalOrders(customerId);
	}

	public Customer displayInfo(int customerId) throws SQLException {
		return dao.getCustomerDetails(customerId);
	}

	public boolean udpateInfo(Customer c) throws SQLException {
		return dao.updateCustomerInfo(c);
	}

}
