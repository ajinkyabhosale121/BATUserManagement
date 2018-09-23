package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.RecruitmentRequests;

@Repository
public class RecruitmentRequestDaoImpl implements RecruitmentRequestDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public RecruitmentRequests findById(long id) {
		
		return sessionFactory.getCurrentSession().get(RecruitmentRequests.class, id);
		
	}

	@Override
	public void saveRequest(RecruitmentRequests request) {
		
		sessionFactory.getCurrentSession().save(request);
		
	}

	@Override
	public void deleteRequestById(long id) {
		
		RecruitmentRequests request = findById(id);
		
		sessionFactory.getCurrentSession().delete(request);	
		
	}

	@Override
	public List<RecruitmentRequests> findAllRequests() {
		
		Session session = sessionFactory.openSession();
		
		CriteriaQuery<RecruitmentRequests> criteriaQuery = session.getCriteriaBuilder().createQuery(RecruitmentRequests.class);
		
		criteriaQuery.from(RecruitmentRequests.class);
		
		List<RecruitmentRequests> requests = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return requests;
	}

	@Override
	public void updateRequest(RecruitmentRequests request) {
		sessionFactory.getCurrentSession().update(request);		
	}

}
