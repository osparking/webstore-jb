package com.jbpark.webstore.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CartTest {
	private Cart cart;

	@Before
	public void setup() {
		cart = new Cart("C1");
	}

	@Test
	public void cart_get_grand_total() {
		// 준비
		ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
		CartItem cItem = new CartItem("1");
		Product prod = new Product("P1234", "iPhone 5s", 50_0000);
		cItem.setProduct(prod);
		
		int qty_1 = 2;
		cItem.setQuantity(qty_1);
		cartItems.add(cItem);
		
		long expGTotal = cItem.getTotalPrice().longValue();
		cItem = new CartItem("2");
		prod = new Product("P1235", "H-Phone K9", 70_0000);
		cItem.setProduct(prod);
		
		int qty_2 = 1;
		cItem.setQuantity(qty_2);
		cartItems.add(cItem);
		expGTotal += cItem.getTotalPrice().longValue();
		cart.setCartItems(cartItems);
		
		// 실행
		BigDecimal grandTotal = cart.getGrandTotal();
		
		// 단언
		Assert.assertEquals(expGTotal, grandTotal.longValue());
	}
}
