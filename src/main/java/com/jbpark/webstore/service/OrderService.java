package com.jbpark.webstore.service;

import com.jbpark.webstore.domain.Order;

public interface OrderService {
	Long saveOrder(Order order);
}
