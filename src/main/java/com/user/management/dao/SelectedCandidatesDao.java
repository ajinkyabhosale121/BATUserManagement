package com.user.management.dao;

import java.util.List;

import com.user.management.model.SelectedCandidates;

public interface SelectedCandidatesDao {
	
	SelectedCandidates findById(long id);
	
	void saveCandidate(SelectedCandidates candidate);
	
	void deleteCandidateById(long id);
	
	List<SelectedCandidates> findAllCandidates();
	
	void updateCandidate(SelectedCandidates candidate);

}
