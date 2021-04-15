package com.jbpark.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jbpark.webstore.domain.Address;
import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.domain.Order;
import com.jbpark.webstore.domain.ShippingDetail;
import com.jbpark.webstore.domain.repository.OrderRepository;
import com.jbpark.webstore.service.CartService;

@Repository
public class MariaOrderRepository implements OrderRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTempleate;
	@Autowired
	private CartService CartService;

	private long saveShippingDetail(ShippingDetail shippingDetail) {
		long addressId = saveAddress(shippingDetail.getShippingAddress());
		String SQL = "INSERT INTO SHIPPING_DETAIL ";
		SQL += "(NAME,SHIPPING_DATE,SHIPPING_ADDRESS_ID) ";
		SQL += "VALUES (:name, :shippingDate, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", shippingDetail.getName());
		params.put("shippingDate", shippingDetail.getShippingDate());
		params.put("addressId", addressId);
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	private long saveCustomer(Customer customer) {
		long addressId = saveAddress(customer.getBillingAddress());
		String SQL = "INSERT INTO CUSTOMER ";
		SQL += "(NAME,PHONE_NUMBER,BILLING_ADDRESS_ID) ";
		SQL += "VALUES (:name, :phoneNumber, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", addressId);
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	private long saveAddress(Address address) {
		String SQL = "INSERT INTO ADDRESS (ZIPCODE,";
		SQL += "WIDECIDO,CIGOONGU,STREETNAME,BUILDINGNO,UNITNO) ";
		SQL += "VALUES (:zipCode, :wideCido, :ciGoonGu,";
		SQL += ":streetName, :buildingNo, :unitNo)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("zipCode", address.getZipCode());
		params.put("wideCido", address.getWideCiDo());
		params.put("ciGoonGu", address.getCiGoonGu());
		params.put("streetName", address.getStreetName());
		params.put("buildingNo", address.getBuildingNo());
		params.put("unitNo", address.getUnitNo());
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	private long createOrder(Order order) {
		String SQL = "INSERT INTO ORDERS";
		SQL += "(CART_ID,CUSTOMER_ID,SHIPPING_DETAIL_ID) ";
		SQL += "VALUES (:cartId, :customerId, :shippingDetailId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", order.getOrderId());
		params.put("cartId", order.getCart().getId());
		params.put("customerId", order.getCustomer().getCustomerId());
		params.put("shippingDetailId", order.getShippingDetail().getId());
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTempleate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	@Override
	public long saveOrder(Order order) {
		Long customerId = saveCustomer(order.getCustomer());
		Long shippingDetailId = saveShippingDetail(order.getShippingDetail());
		order.getCustomer().setCustomerId(customerId);
		order.getShippingDetail().setId(shippingDetailId);
		long createdOrderId = createOrder(order);
		CartService.clearCart(order.getCart().getId());
		return createdOrderId;
	}

}
