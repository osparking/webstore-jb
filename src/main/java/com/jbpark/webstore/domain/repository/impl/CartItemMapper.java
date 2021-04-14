package com.jbpark.webstore.domain.repository.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jbpark.webstore.domain.CartItem;
import com.jbpark.webstore.service.ProductService;

public class CartItemMapper implements RowMapper<CartItem> {
	private ProductService productService;

	public CartItemMapper(ProductService productService) {
		super();
		this.productService = productService;
	}

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		CartItem cartItem = new CartItem(rs.getString("ID"));
		cartItem.setProduct(productService.getProductById(rs.getString("PRODUCT_ID")));
		cartItem.setQuantity(rs.getInt("QUANTITY"));
		BigDecimal bd = (new BigDecimal(rs.getInt("QUANTITY"))).multiply(
				cartItem.getProduct().getUnitPrice());
		cartItem.setTotalPrice(bd);
		return cartItem;
	}

}
