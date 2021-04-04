package com.jbpark.webstore.domain.repository;

import java.util.List;

import com.jbpark.webstore.domain.Product;

public interface ProductRepository {
	List<Product> getAllProducts();
}
