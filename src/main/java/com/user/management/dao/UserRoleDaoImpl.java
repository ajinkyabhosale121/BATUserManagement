package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.UserRole;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public UserRole findById(long id) {
		
		return sessionFactory.getCurrentSession().get(UserRole.class, id);
	}

	@Override
	public void saveUserRole(UserRole user_role) {
		
		sessionFactory.getCurrentSession().save(user_role);
		
	}

	@Override
	public void deleteUserRoleById(long id) {
		
		UserRole user_role = findById(id);
		
		sessionFactory.getCurrentSession().delete(user_role);
		
	}

	@Override
	public List<UserRole> findAllUserRoles() {
		
		Session session = sessionFactory.openSession();
		
		
		CriteriaQuery<UserRole> criteriaQuery = session.getCriteriaBuilder().createQuery(UserRole.class);
		
		criteriaQuery.from(UserRole.class);
		
		List<UserRole> user_roles = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return user_roles;
	}

	@Override
	public void updateUserRole(UserRole user_role) {
		
		sessionFactory.getCurrentSession().update(user_role);		
	}

}
