package com.user.management.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.management.model.SelectedCandidates;

@Repository
public class SelectedCandidatesDaoImpl implements SelectedCandidatesDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public SelectedCandidates findById(long id) {
		
		return sessionFactory.getCurrentSession().get(SelectedCandidates.class, id);
		
	}

	@Override
	public void saveCandidate(SelectedCandidates candidate) {
		
		sessionFactory.getCurrentSession().save(candidate);
		
	}

	@Override
	public void deleteCandidateById(long id) {
		
		SelectedCandidates candidate = findById(id);
		
		sessionFactory.getCurrentSession().delete(candidate);
		
	}

	@Override
	public List<SelectedCandidates> findAllCandidates() {
		
		Session session = sessionFactory.openSession();
		
		CriteriaQuery<SelectedCandidates> criteriaQuery = session.getCriteriaBuilder().createQuery(SelectedCandidates.class);
		
		criteriaQuery.from(SelectedCandidates.class);
		
		List<SelectedCandidates> candidates = session.createQuery(criteriaQuery).getResultList();
        
		session.close();

        return candidates;

	}

	@Override
	public void updateCandidate(SelectedCandidates candidate) {
		sessionFactory.getCurrentSession().update(candidate);
	}

}
