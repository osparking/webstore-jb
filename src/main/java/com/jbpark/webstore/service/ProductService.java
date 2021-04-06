package com.jbpark.webstore.service;

import java.util.List;

import com.jbpark.webstore.domain.Product;

public interface ProductService {
	void updateAllStock();
	List<Product> getAllProducts();
	List<Product> getAllProducts(String arg);
	List<Product> getProductsByCategory(String category);
}
