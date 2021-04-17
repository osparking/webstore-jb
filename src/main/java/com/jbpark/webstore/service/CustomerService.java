package com.jbpark.webstore.service;

import java.util.List;

import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.domain.Customers;

public interface CustomerService {
	List<Customer> getAllCustomers();
	void addCustomer(Customer customer);
	
	List<Customer> getAllCustomerDetail();
	long saveCustomerDetail(Customer customer);
	Customer getCustomer(String customerId);
	Boolean isCustomerExist(String customerId);	
}
