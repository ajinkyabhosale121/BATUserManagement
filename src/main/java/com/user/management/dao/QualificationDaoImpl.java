package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.Qualification;

@Repository
public class QualificationDaoImpl implements QualificationDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Qualification findById(long id) {
		
		return sessionFactory.getCurrentSession().get(Qualification.class, id);
	}

	@Override
	public void saveQualification(Qualification qualification) {
		sessionFactory.getCurrentSession().save(qualification);
	}

	@Override
	public void deleteQualificationById(long id) {
		Qualification qualification = findById(id);
		
		sessionFactory.getCurrentSession().delete(qualification);
	}

	@Override
	public List<Qualification> findAllQualifications() {
		
		Session session = sessionFactory.openSession();
		
		
		CriteriaQuery<Qualification> criteriaQuery = session.getCriteriaBuilder().createQuery(Qualification.class);
		
		criteriaQuery.from(Qualification.class);
		
		List<Qualification> qualifications = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return qualifications;
	}

	@Override
	public void updateQualification(Qualification qualification) {
		sessionFactory.getCurrentSession().update(qualification);
	}

}
