package com.jbpark.webstore.domain.repository;

import com.jbpark.webstore.domain.Order;

public interface OrderRepository {
	long saveOrder(Order order);
}
