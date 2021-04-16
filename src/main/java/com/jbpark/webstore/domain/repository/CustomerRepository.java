package com.jbpark.webstore.domain.repository;

import java.util.List;

import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.domain.Customers;

public interface CustomerRepository {
	List<Customers> getAllCustomers();
	void addCustomer(Customers customer);
	List<Customer> getAllCustomerDetail();
	Customer getAcustomer(String customerId);
	long saveCustomer(Customer customer);
	Boolean isCustomerExist(String customerId);
}
