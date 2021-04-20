package com.jbpark.webstore.domain.repository;

import java.util.List;

import com.jbpark.webstore.domain.UserWS;

public interface UserRepository {
	List<UserWS> getAllUsers();
}
