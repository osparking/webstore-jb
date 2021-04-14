package com.jbpark.webstore.domain.repository;

import com.jbpark.webstore.domain.Cart;
import com.jbpark.webstore.dto.CartDto;

public interface CartRepository {
	void create(CartDto cartDto);
	Cart read(String id);
	void update(String id, CartDto cartDto);
	void delete(String id);
	void addItem(String cartId, String productId);
	void removeItem(String cartId, String productId);
}
