package com.jbpark.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jbpark.webstore.config.WebStoreContextConfig;
import com.jbpark.webstore.domain.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebStoreContextConfig.class)
@WebAppConfiguration
public class ProductControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void testGetProducts() throws Exception {
		this.mockMvc.perform(get("/market/products")).andExpect(model().attributeExists("products"));
	}

	@Test
	public void testGetProductById() throws Exception {
		// Arrange
		Product product = new Product("P1234", "iPhone 5s", 500, 1000);
		// Act & Assert
		this.mockMvc.perform(get("/market/product")
				.param("id", "P1234")).andExpect(model()
				.attributeExists("product"))
				.andExpect(model().attribute("product", product));
	}
}











