package com.user.management.dao;

import java.util.List;

import com.user.management.model.UserRole;

public interface UserRoleDao {
	
	UserRole findById(long id);
	
	void saveUserRole(UserRole user_role);
	
	void deleteUserRoleById(long id);
	
	List<UserRole> findAllUserRoles();
	
	void updateUserRole(UserRole user_role);
}
