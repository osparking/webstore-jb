package com.jbpark.webstore.domain.repository;

import java.util.List;

import com.jbpark.webstore.domain.User;

public interface UserRepository {
	List<User> getAllUsers();
}
