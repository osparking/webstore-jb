package com.jbpark.webstore.validator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.jbpark.webstore.config.WebStoreContextConfig;
import com.jbpark.webstore.domain.Product;

import binder.ProductValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebStoreContextConfig.class)
@WebAppConfiguration
public class ProductValidatorTest {
	@Autowired
	private ProductValidator productValidator;

	@Test
	public void product_without_UnitPrice_should_be_invalid() {

		// Arrange
		Product product = new Product();
		BindException bindException = new BindException(product, "product");

		// Act
		// @formatter:off
		ValidationUtils.invokeValidator(productValidator, 
				product, bindException);
		
		// Assert
		Assert.assertEquals(4, bindException.getErrorCount());
		Assert.assertTrue(bindException.getLocalizedMessage()
				.contains("상품 가격 누락."));
		// @formatter:on
	}

	@Test
	public void product_with_existing_productId_invalid() {
		// Arrange
		Product product = new Product("P1234", "iPhone 5s", 500, 1000);
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(1, bindException.getErrorCount());
		String resultString = bindException.getLocalizedMessage();
		Assert.assertTrue(bindException.getLocalizedMessage().contains("같은 ID를 가지는 상품이 존재함"));
	}

	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
		// Arrange
		Product product = new Product("P9876", "iPhone 5s", 500, 1000);
		product.setCategory("Tablet");
		BindException bindException = new BindException(product, "product");
		// Act
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		// Assert
		Assert.assertEquals(0, bindException.getErrorCount());
	}
}
