package com.jbpark.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbpark.webstore.domain.CartItem;
import com.jbpark.webstore.domain.Order;
import com.jbpark.webstore.domain.repository.OrderRepository;
import com.jbpark.webstore.domain.repository.ProductRepository;
import com.jbpark.webstore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Long saveOrder(Order order) {
		// @formatter:off
		/**
		 * Change(=decrease) product stock.
		 */
		/**
		 * first trial : console debugging
		for (CartItem item : order.getCart().getCartItems()) {
			System.out.println(
					item.getProduct().getProductId() + " " + -item.getQuantity());
			
		}
		 */
		/**
		 * second trial : Java 7 approach		
		for (CartItem item : order.getCart().getCartItems()) {
			productRepository.changeStock(
					item.getProduct().getProductId(), -item.getQuantity());
			
		}
		*/
		/**
		 *  Java 8 lambda expression 
		 */
		order.getCart().getCartItems().forEach(item-> {
			productRepository.changeStock(
					item.getProduct().getProductId(), 
					-item.getQuantity());});
		return orderRepository.saveOrder(order);
		// @formatter:on
	}

}






