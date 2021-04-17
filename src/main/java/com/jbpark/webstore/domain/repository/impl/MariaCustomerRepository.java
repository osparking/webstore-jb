package com.jbpark.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.jbpark.webstore.domain.Address;
import com.jbpark.webstore.domain.Customer;
import com.jbpark.webstore.domain.Customers;
import com.jbpark.webstore.domain.repository.AddressRepository;
import com.jbpark.webstore.domain.repository.CustomerRepository;

@Repository
public class MariaCustomerRepository implements CustomerRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Autowired
	private AddressRepository addressRepository;

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
	public void addCustomer(Customers customer) throws DataAccessException {
		String sql = "INSERT INTO CUSTOMERS (ID, " + "NAME, address, noOfOrdersMade) "
				+ "VALUES (:id, :name, :address, :noOfOrdersMade)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customer.getCustomerId());
		params.put("name", customer.getName());
		params.put("address", customer.getAddress());
		params.put("noOfOrdersMade", customer.getNoOfOrdersMade());
		jdbcTemplate.update(sql, params);
	}

	@Override
	public List<Customer> getAllCustomerDetail() {
		Map<String, Object> params = new HashMap<String, Object>();
		String qry = null;
		qry = "Select C.ID, C.name, C.phone_number,";
		qry += " A.ZIPCODE, A.WIDECIDO, A.CIGOONGU, A.STREETNAME, ";
		qry += " A.BUILDINGNO, A.UNITNO ";
		qry += "From customers C";
		qry += " Join address A on C.billing_address_id = A.ID";
		List<Customer> result = jdbcTemplate.query(qry, params, new CustomerMapper2());
		return result;
	}
	
	@Override
	public Customer getCustomer(String customerId) {
		String qry = "";
		qry += "Select C.ID, C.name, C.phone_number,";
		qry += " A.ZIPCODE, A.WIDECIDO, A.CIGOONGU, A.STREETNAME,";
		qry += " A.BUILDINGNO, A.UNITNO ";
		qry += "From customers C";
		qry += " Join address A on C.billing_address_id = A.ID ";
		qry += "WHERE C.ID = :id";
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		
		Customer result = null;
		
		try {
			result = jdbcTemplate.queryForObject(qry, params, new CustomerMapper2());
		} catch (EmptyResultDataAccessException e) {
			if (result == null) {
				result = new Customer();
				result.setWrongId(true);
				result.setCustomerIdLong((long) Integer.parseInt(customerId));
			}
		}		
		return result;
	}

	private static final class CustomerMapper2 implements RowMapper<Customer> {
		public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
			Customer customer = new Customer();
			customer.setCustomerIdLong(rs.getLong("ID"));
			customer.setName(rs.getString("NAME"));
			customer.setPhoneNumber(rs.getString("PHONE_NUMBER"));
			Address billAddress = new Address();
			billAddress.setZipCode(rs.getString("ZIPCODE"));
			billAddress.setWideCiDo(rs.getString("WIDECIDO"));
			billAddress.setCiGoonGu(rs.getString("CIGOONGU"));
			billAddress.setStreetName(rs.getString("STREETNAME"));
			billAddress.setBuildingNo(rs.getString("BUILDINGNO"));
			billAddress.setUnitNo(rs.getString("UNITNO"));
			customer.setBillingAddress(billAddress);
			return customer;
		}
	}

	/**
	 * 고객 정보를 주소 포함하여 DB 에 저장한다.
	 */
	@Override
	public long saveCustomerDetail(Customer customer) {
		long addressId = addressRepository.saveAddress(customer.getBillingAddress());
		String SQL = "INSERT INTO CUSTOMERS ";
		SQL += "(NAME,PHONE_NUMBER,BILLING_ADDRESS_ID) ";
		SQL += "VALUES (:name, :phoneNumber, :addressId)";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("name", customer.getName());
		params.put("phoneNumber", customer.getPhoneNumber());
		params.put("addressId", addressId);
		SqlParameterSource paramSource = new MapSqlParameterSource(params);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}

	@Override
	public Boolean isCustomerExist(String customerId) {
		String sql = "SELECT count(*) FROM customers";
		sql += " WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", customerId);
		int result = jdbcTemplate.queryForObject(sql, params, Integer.class);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

}
