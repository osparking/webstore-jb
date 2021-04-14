package com.jbpark.webstore.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6968015051623187076L;
	private String id;
	private List<CartItemDto> cartItems;

	public CartDto() {
	} // 기본 생성자

	public CartDto(String id) {
		this.id = id;
		cartItems = new ArrayList<>();
	}

	public void addCartItem(CartItemDto cartItemDto) {
		this.cartItems.add(cartItemDto);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<CartItemDto> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItemDto> cartItems) {
		this.cartItems = cartItems;
	}
	
}
