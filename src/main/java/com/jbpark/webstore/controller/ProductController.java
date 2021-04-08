package com.jbpark.webstore.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jbpark.webstore.domain.Product;
import com.jbpark.webstore.service.ProductService;

@RequestMapping("market")
@Controller
public class ProductController {

	@RequestMapping(value = "/products/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}

	@RequestMapping(value = "/products/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE
			+ "; charset=utf-8")
	public String processAddNewProductForm(@ModelAttribute("newProduct") Product newProduct, BindingResult result,
			Model model, HttpServletRequest request) {
		try {
			String[] suppressedFields = result.getSuppressedFields();
			if (suppressedFields.length > 0) {
				throw new RuntimeException(
						"허용되지 않은 항목을 엮어오려고함: " + StringUtils.arrayToCommaDelimitedString(suppressedFields));
			} else {
				/**
				 * 상품 영상 메모리 내용 정한 폴더에 파일로 보관
				 */
				MultipartFile productImage = newProduct.getProductImage();
				String rootDirectory = request.getSession().getServletContext().getRealPath("/");
				if (productImage != null && !productImage.isEmpty()) {
					try {
						productImage.transferTo(
								new File(rootDirectory + "resources\\images\\" + newProduct.getProductId() + ".png"));
					} catch (Exception e) {
						throw new RuntimeException("Product Image saving failed", e);
					}
				}
				/**
				 * 상품 설명서 메모리 내용 정한 폴더에 파일로 보관
				 */
				MultipartFile productManual = newProduct.getProductManual();
				if (productManual != null && !productManual.isEmpty()) {
					try {
						productManual.transferTo(
								new File(rootDirectory + "resources\\pdf\\" + newProduct.getProductId() + ".pdf"));
					} catch (Exception e) {
						throw new RuntimeException("상품 설명서 저장 실패", e);
					}
				}
				productService.addProduct(newProduct);
			}
			return "redirect:/market/products";
		} catch (DataAccessException e) {
			String msg = e.getMessage();
			int idx = msg.lastIndexOf("Duplicate");
			model.addAttribute("errorMsg", msg.substring(idx));
			return "addProduct";
		}
	}

	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productId", "name", "unit*", "description", "manufacturer", "category", "condition",
				"productImage", "productManual");
	}

	@RequestMapping("/products/filter/{params}") // 6절 실습
	public String getProductsByFilter(@MatrixVariable(pathVar = "params") Map<String, List<String>> filterParams,
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

	@RequestMapping("/product") // 7절 실습
	public String getProductById(@RequestParam("id") String productId, Model model) {
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}

	@RequestMapping("/update/stock")
	public String updateStock(Model model) {
		productService.updateAllStock();
		return "redirect:/market/products";
	}
}
