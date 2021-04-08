package com.jbpark.webstore.domain.repository;

import java.util.List;

import com.jbpark.webstore.domain.Customer;

public interface CustomerRepository {
	List<Customer> getAllCustomers();
	void addCustomer(Customer customer);
}
