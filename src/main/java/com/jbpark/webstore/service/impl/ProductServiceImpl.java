package com.jbpark.webstore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

	@Override
	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	@Override
	public List<Product> getProdsByMultiFilter(
			String productCategory,
			Map<String, String> price, String brand) {
		return productRepository.getProdsByMultiFilter(productCategory, price, brand);
	}

	@Override
	public void addProduct(Product product) throws DataAccessException {
		productRepository.addProduct(product);
	}

	@Override
	public void updateStock(String productId, Long stock) {
		productRepository.updateStock(productId, stock);
	}

}
