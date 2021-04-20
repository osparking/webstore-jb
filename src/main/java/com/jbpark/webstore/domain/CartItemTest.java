package com.jbpark.webstore.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartItemTest {
	private CartItem cartItem;

	@Before
	public void setup() {
		cartItem = new CartItem("1"); // 1: ID
	}

	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
		// @formatter:off
		// Arrange
		Product iphone = new Product("P1234", "iPhone 5s", 
				50_0000);
		cartItem.setProduct(iphone);
		cartItem.setQuantity(1);
		// Act
		BigDecimal totalPrice = cartItem.getTotalPrice();
		// Assert
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
		// @formatter:on
	}
}
