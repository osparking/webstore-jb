package com.jbpark.webstore.service;

import java.util.List;
import java.util.Map;

import com.jbpark.webstore.domain.Product;

public interface ProductService {
	void updateAllStock();
	void updateStock(String productId, Long stock);
	List<Product> getAllProducts();
	List<Product> getAllProducts(String arg);
	List<Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(
			Map<String, List<String>> filterParams);
	Product getProductById(String productID);
	List<Product> getProdsByMultiFilter(String productCategory, 
			Map<String, String> price, String brand);	
	void addProduct(Product product);
}
