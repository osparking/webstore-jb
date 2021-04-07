package com.jbpark.webstore.service;

import java.util.List;
import java.util.Map;

import com.jbpark.webstore.domain.Product;

public interface ProductService {
	void updateAllStock();
	List<Product> getAllProducts();
	List<Product> getAllProducts(String arg);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(
			Map<String, List<String>> filterParams);
}
