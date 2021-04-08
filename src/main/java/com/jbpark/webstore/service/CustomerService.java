package com.jbpark.webstore.service;

import java.util.List;

import com.jbpark.webstore.domain.Customer;

public interface CustomerService {
	List<Customer> getAllCustomers();
	void addCustomer(Customer customer);
}
