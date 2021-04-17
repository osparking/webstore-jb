package com.jbpark.webstore.domain.repository;

import java.util.List;

import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.domain.Customers;

public interface CustomerRepository {
	List<Customer> getAllCustomers();
	List<Customer> getAllCustomersDetail();
	void addCustomer(Customer customer);
	Customer getCustomer(String customerId);
	long saveCustomerDetail(Customer customer);
	Boolean isCustomerExist(String customerId);
}
