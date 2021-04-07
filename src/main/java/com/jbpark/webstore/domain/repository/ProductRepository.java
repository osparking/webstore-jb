package com.jbpark.webstore.domain.repository;

import java.util.List;
import java.util.Map;

import com.jbpark.webstore.domain.Product;

public interface ProductRepository {
	List<Product> getAllProducts(String...args);
	void updateStock(String productId, long noOfUnits);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
}
