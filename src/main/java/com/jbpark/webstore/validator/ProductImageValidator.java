package com.jbpark.webstore.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jbpark.webstore.domain.Product;

@Component
public class ProductImageValidator implements Validator {
	private long allowedSize;

	public long getAllowedSize() {
		return allowedSize;
	}

	public void setAllowedSize(long allowedSize) {
		this.allowedSize = allowedSize;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Product.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		if (product.getProductImage() != null &&
				product.getProductImage().getSize() > allowedSize) {
			errors.rejectValue("productImage", "com.jbpark.webstore.validator.ProductImageValidator.message");
		}
	}

}
