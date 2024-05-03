package com.dao;

import java.sql.SQLException;

import com.model.Customer;

public interface CustomerDao {
	int calculateTotalOrders(int customerId) throws SQLException;

	Customer getCustomerDetails(int customerId) throws SQLException;

	boolean updateCustomerInfo(Customer updatedCustomer) throws SQLException;
}
