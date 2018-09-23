package com.user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.management.dao.UserRoleDao;
import com.user.management.model.UserRole;

@Service("userRoleService")
@Transactional
public class UserRoleSeviceImpl implements UserRoleSevice {
	
	@Autowired
    private UserRoleDao dao;

	@Override
	public UserRole findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void saveUserRole(UserRole user_role) {
		dao.saveUserRole(user_role);
	}

	@Override
	public void deleteUserRoleById(long id) {
		dao.deleteUserRoleById(id);
	}

	@Override
	public List<UserRole> findAllUserRoles() {
		return dao.findAllUserRoles();
	}

	@Override
	public void updateUserRole(UserRole user_role) {
		dao.updateUserRole(user_role);
	}

}
