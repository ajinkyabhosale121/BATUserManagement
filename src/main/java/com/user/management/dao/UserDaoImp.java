package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.User;

@Repository
public class UserDaoImp implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findById(long id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id);
		
	}

	@Override
	public void saveUser(User user) {
		
		sessionFactory.getCurrentSession().save(user);
		
	}

	@Override
	public void deleteUserById(long id) {
		
		User user = findById(id);
		
		sessionFactory.getCurrentSession().delete(user);
		
	}

	@Override
	public List<User> findAllUsers() {
		
		Session session = sessionFactory.openSession();
		
		
		CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
		
		criteriaQuery.from(User.class);
		
		List<User> users = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return users;
	}
	
	@Override
	public void updateUser(User user) {
		
		sessionFactory.getCurrentSession().update(user);
		
	}
}
