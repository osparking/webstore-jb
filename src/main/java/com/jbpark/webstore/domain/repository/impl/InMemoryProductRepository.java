package com.jbpark.webstore.domain.repository.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jbpark.webstore.domain.Product;
import com.jbpark.webstore.domain.repository.ProductRepository;

@Repository
public class InMemoryProductRepository implements ProductRepository {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	public void updateStock(String productId, long noOfUnits) {
		String SQL = "UPDATE PRODUCTS SET " + "UNITS_IN_STOCK = :unitsInStock WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("unitsInStock", noOfUnits);
		params.put("id", productId);
		jdbcTemplate.update(SQL, params);
	}

	@Override
	public List<Product> getAllProducts(String... args) {
		Map<String, Object> params = new HashMap<String, Object>();
		String query = "SELECT * FROM products";

		if (args.length == 1) {
			query += " WHERE LCASE(CATEGORY) = :category";
			params.put("category", args[0]);
		}
		List<Product> result = jdbcTemplate.query(query, params, new ProductMapper());
		return result;

	}

	private static final class ProductMapper implements RowMapper<Product> {
		public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
			Product product = new Product();
			product.setProductId(rs.getString("ID"));
			product.setName(rs.getString("PROD_NAME"));
			product.setDescription(rs.getString("DESCRIPTION"));
			product.setUnitPrice(rs.getBigDecimal("UNIT_PRICE"));
			product.setManufacturer(rs.getString("MANUFACTURER"));
			product.setCategory(rs.getString("CATEGORY"));
			product.setCondition(rs.getString("PROD_CONDITION"));
			product.setUnitsInStock(rs.getLong("UNITS_IN_STOCK"));
			product.setUnitsInOrder(rs.getLong("UNITS_IN_ORDER"));
			product.setDiscontinued(rs.getBoolean("DISCONTINUED"));
			return product;
		}
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		String SQL = "SELECT * FROM PRODUCTS " + "WHERE LCASE(CATEGORY) = :category";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("category", category.toLowerCase());
		return jdbcTemplate.query(SQL, params, new ProductMapper());
	}

	@Override
	public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		String SQL = "SELECT * FROM PRODUCTS WHERE CATEGORY " + "IN (:categories) AND MANUFACTURER IN (:brands)";
		return jdbcTemplate.query(SQL, filterParams, new ProductMapper());
	}

	@Override
	public Product getProductById(String productID) {
		String SQL = "SELECT * FROM PRODUCTS WHERE ID = :id";
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", productID);
		return jdbcTemplate.queryForObject(SQL, params, new ProductMapper());
	}
}
