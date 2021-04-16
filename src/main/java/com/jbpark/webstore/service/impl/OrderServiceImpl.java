package com.jbpark.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbpark.webstore.domain.Order;
import com.jbpark.webstore.domain.repository.OrderRepository;
import com.jbpark.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Long saveOrder(Order order) {
		return orderRepository.saveOrder(order);
	}

}
