package com.jbpark.webstore.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, 
	reason = "이 범주에 속하는 상품은 없습니다.")
public class NoProductsFoundUnderCategoryException 
	extends RuntimeException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8105975003020851770L;
}
