package com.jbpark.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.domain.Customers;
import com.jbpark.webstore.domain.repository.CustomerRepository;
import com.jbpark.webstore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public List<Customers> getAllCustomers() {
		return customerRepository.getAllCustomers();
	}

	@Override
	public void addCustomer(Customers customer) throws DataAccessException {
		customerRepository.addCustomer(customer);
	}

	@Override
	public List<Customer> getAllCustomer() {
		return customerRepository.getAllCustomer();
	}

}
