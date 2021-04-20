package com.jbpark.webstore.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.jbpark.webstore.config.WebStoreContextConfig;
import com.jbpark.webstore.dto.CartDto;
import com.jbpark.webstore.dto.CartItemDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebStoreContextConfig.class)
@WebAppConfiguration
public class CartRestControllerTest {
	@Autowired
	private WebApplicationContext wac;
	@Autowired
	MockHttpSession session;
	private MockMvc mockMvc;

	// @formatter:off
	@Test
	public void update_return_200_session() throws Exception {
		// Arrange
		CartDto cart = new CartDto(session.getId());
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setId("Ci_1235");
		cartItemDto.setProductId("P1235");
		cart.addCartItem(cartItemDto);
		
		RequestBuilder req = post("/rest/cart")
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session);
		this.mockMvc.perform(req).andExpect(status().isCreated());
		
		// Act
		cartItemDto = new CartItemDto();
		cartItemDto.setId("Ci_2000");
		cartItemDto.setProductId("P1236");
		cart.addCartItem(cartItemDto);
		req = put("/rest/cart/" + session.getId())
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session);
		
		this.mockMvc.perform(req).andExpect(status().isOk());
		
		req = get("/rest/cart/" + session.getId());
		this.mockMvc.perform(req).andExpect(
					jsonPath("$.cartItems[0].product.productId")
				.value("P1235"))
				.andExpect(jsonPath("$.cartItems[1].product.productId")
				.value("P1236"));
	}		
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders
				.webAppContextSetup(this.wac).build();
	}
	
	@Test
	public void create_method_should_return_200() throws Exception {
		// Arrange
		CartDto cart = new CartDto("C124");
		// Act
		this.mockMvc.perform(
				post("/rest/cart").content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void update_method_should_return_200() throws Exception {
		// Arrange
		CartDto cart = new CartDto("C123");
		this.mockMvc.perform(post("/rest/cart")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(cart))
				.session(session))
				.andExpect(status().isCreated());
		// Act
		CartItemDto cartItemDto = new CartItemDto();
		cartItemDto.setId("P4321");
		cart.addCartItem(cartItemDto);
		this.mockMvc.perform(put("/rest/cart/" + session.getId())
				.content(asJsonString(cart))
				.contentType(MediaType.APPLICATION_JSON)
				.session(session)).andExpect(status().isOk());
	}	
	// @formatter:on

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void delete_method_should_return_ok() throws Exception {
		// Arrange
		this.mockMvc.perform(put("/rest/cart/add/P1234").session(session)).andExpect(status().is(200));
		// Act
		this.mockMvc.perform(delete("/rest/cart/" + session.getId()).session(session)).andExpect(status().isOk());
	}

	@Test
	public void remove_method_should_return_ok() throws Exception {
		// Arrange
		this.mockMvc.perform(put("/rest/cart/add/P1234").session(session)).andExpect(status().is(200));
		// Act
		this.mockMvc.perform(put("/rest/cart/remove/P1234").session(session)).andExpect(status().isOk());
	}

	// @formatter:off
	@Test
	public void addItem_method_should_return_200() throws Exception {
		// Arrange
		// Act
		this.mockMvc.perform(put("/rest/cart/add/P1234")
				.session(session)).andExpect(status().is(200));
	}

	@Test
	public void read_method_should_return_correct_cart_Json_object() throws Exception {
		// Arrange
		this.mockMvc.perform(put("/rest/cart/add/P1234")
				.session(session)).andExpect(status().is(200));
		// Act
		this.mockMvc.perform(get("/rest/cart/" + session.getId())
				.session(session)).andExpect(status().isOk()) 
				// refer : https://jsonpath.com/
				.andExpect(jsonPath("$.cartItems[0].product.productId")
						.value("P1234"));
	}
	// @formatter:on
}
