package com.jbpark.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jbpark.webstore.domain.Customers;
import com.jbpark.webstore.domain.repository.CustomerRepository;

@Repository
public class MariaCustomerRepository implements CustomerRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public List<Customers> getAllCustomers() {
		Map<String, Object> params = new HashMap<String, Object>();
		List<Customers> result = jdbcTemplate.query("SELECT * FROM customers", params, new CustomerMapper());
		return result;
	}

	private static final class CustomerMapper implements RowMapper<Customers> {
		public Customers mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customers customer = new Customers();
			customer.setCustomerId(rs.getString("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setAddress(rs.getString("address"));
			customer.setNoOfOrdersMade(rs.getInt("noOfOrdersMade"));
			return customer;
		}
	}

	@Override
	public void addCustomer(Customers customer) throws DataAccessException{
		String sql = "INSERT INTO CUSTOMERS (ID, " + "NAME, address, noOfOrdersMade) "
				+ "VALUES (:id, :name, :address, :noOfOrdersMade)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("address", customer.getAddress());
		params.put("noOfOrdersMade", customer.getNoOfOrdersMade());
		jdbcTemplate.update(sql, params);
	}
}
