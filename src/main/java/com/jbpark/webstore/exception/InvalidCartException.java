package com.jbpark.webstore.exception;

import java.io.Serializable;

public class InvalidCartException extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 475288901983928397L;
	private String cartId;

	public InvalidCartException(String cartId) {
		this.cartId = cartId;
	}

	public String getCartId() {
		return cartId;
	}

}
