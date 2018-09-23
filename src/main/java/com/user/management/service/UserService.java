package com.user.management.service;

import java.util.List;

import com.user.management.model.User;

public interface UserService {
	
	 	User findById(long id);
     
	    void saveUser(User user);
	     
	    void updateUser(User user);
	     
	    void deleteUserById(long l);
	 
	    List<User> findAllUsers();
}
