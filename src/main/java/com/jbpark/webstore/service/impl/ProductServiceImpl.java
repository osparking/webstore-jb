package com.jbpark.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jbpark.webstore.domain.Product;
import com.jbpark.webstore.domain.repository.ProductRepository;
import com.jbpark.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public void updateAllStock() {
		List<Product> allProducts = productRepository.getAllProducts();
		for (Product product : allProducts) {
			if (product.getUnitsInStock() < 500) {
				productRepository.updateStock(product.getProductId(), product.getUnitsInStock() + 1000);
			}
		}
	}
	
	@Override
	public List<Product> getProductsByFilter(
			Map<String, List<String>> filterParams) {
			return productRepository.getProductsByFilter(filterParams);
			}	

	@Override
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}
	
	@Override
	public List<Product> getAllProducts(String arg) {
		return productRepository.getAllProducts(arg);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}

}
