package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.InterviewShedules;

@Repository
public class InterviewShedulesDaoImpl implements InterviewShedulesDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public InterviewShedules findById(long id) {
		return sessionFactory.getCurrentSession().get(InterviewShedules.class, id);
	}

	@Override
	public void saveShedule(InterviewShedules shedule) {
		
		sessionFactory.getCurrentSession().save(shedule);
		
	}

	@Override
	public void deleteSheduleById(long id) {
		
		InterviewShedules shedule = findById(id);
		
		sessionFactory.getCurrentSession().delete(shedule);
	}

	@Override
	public List<InterviewShedules> findAllShedules() {
		
		Session session = sessionFactory.openSession();
		
		CriteriaQuery<InterviewShedules> criteriaQuery = session.getCriteriaBuilder().createQuery(InterviewShedules.class);
		
		criteriaQuery.from(InterviewShedules.class);
		
		List<InterviewShedules> shedules = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return shedules;
	}

	@Override
	public void updateShedule(InterviewShedules shedule) {
		
		sessionFactory.getCurrentSession().update(shedule);		
	}

}
