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
import com.jbpark.webstore.domain.repository.AddressRepository;

@Repository
public class MariaAddressRepository implements AddressRepository {
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	@Override
	public long saveAddress(Address address) {
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
		jdbcTemplate.update(SQL, paramSource, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().longValue();
	}
}
