package com.jbpark.webstore.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jbpark.webstore.service.ProductService;

@RequestMapping("market")
@Controller
public class ProductController {
	/*
	 * @RequestMapping("/products/laptop") public String laptop(Model model) {
	 * model.addAttribute("products", productService.getAllProducts("laptop"));
	 * return "products"; }
	 */

	@RequestMapping("/products/filter/{params}") // 6절 실습
	public String getProductsByFilter(
			@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams,
			Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}

	@RequestMapping("/products/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category") String category) {
		model.addAttribute("products", productService.getProductsByCategory(category));
		return "products";
	}

	@RequestMapping("/products")
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}

	@Autowired
	private ProductService productService;

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}
}
