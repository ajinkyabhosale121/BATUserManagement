package com.user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.management.dao.SelectedCandidatesDao;
import com.user.management.model.SelectedCandidates;

@Service("candidateService")
@Transactional
public class SelectedCandidatesServiceImpl implements SelectedCandidatesService{

	@Autowired
    private SelectedCandidatesDao dao;
	
	@Override
	public SelectedCandidates findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void saveCandidate(SelectedCandidates candidate) {
		dao.saveCandidate(candidate);
	}

	@Override
	public void deleteCandidateById(long id) {
		dao.deleteCandidateById(id);
	}

	@Override
	public List<SelectedCandidates> findAllCandidates() {
		return dao.findAllCandidates();
	}

	@Override
	public void updateCandidate(SelectedCandidates candidate) {
		dao.updateCandidate(candidate);		
	}

}
