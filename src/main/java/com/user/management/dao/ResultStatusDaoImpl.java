package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.ResultStatus;

@Repository
public class ResultStatusDaoImpl implements ResultStatusDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public ResultStatus findById(long id) {
		return sessionFactory.getCurrentSession().get(ResultStatus.class, id);
	}

	@Override
	public void saveResultStatus(ResultStatus resultstatus) {
		sessionFactory.getCurrentSession().save(resultstatus);
	}

	@Override
	public void deleteResultStatusById(long id) {
		ResultStatus resultstatus = findById(id);
		
		sessionFactory.getCurrentSession().delete(resultstatus);
	}

	@Override
	public List<ResultStatus> findAllResultStatus() {
		
		Session session = sessionFactory.openSession();
		
		
		CriteriaQuery<ResultStatus> criteriaQuery = session.getCriteriaBuilder().createQuery(ResultStatus.class);
		
		criteriaQuery.from(ResultStatus.class);
		
		List<ResultStatus> resultstatus = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return resultstatus;
	}

	@Override
	public void updateResultStatus(ResultStatus resultstatus) {
		sessionFactory.getCurrentSession().update(resultstatus);
	}
	
	

}
