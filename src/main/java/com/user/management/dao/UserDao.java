package com.user.management.dao;

import java.util.List;

import com.user.management.model.User;

public interface UserDao {
	
	User findById(long id);
	
	void saveUser(User user);
	
	void deleteUserById(long id);
	
	List<User> findAllUsers();
	
	void updateUser(User user);
	
}
