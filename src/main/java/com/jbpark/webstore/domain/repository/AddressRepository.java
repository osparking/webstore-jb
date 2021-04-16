package com.jbpark.webstore.domain.repository;

import com.jbpark.webstore.domain.Address;

public interface AddressRepository {
	long saveAddress(Address address);
}
