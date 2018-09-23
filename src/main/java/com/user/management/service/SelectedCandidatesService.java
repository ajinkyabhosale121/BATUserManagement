package com.user.management.service;

import java.util.List;

import com.user.management.model.SelectedCandidates;

public interface SelectedCandidatesService {
	
	SelectedCandidates findById(long id);
	void saveCandidate(SelectedCandidates candidate);
	void deleteCandidateById(long id);
	List<SelectedCandidates> findAllCandidates();
	void updateCandidate(SelectedCandidates candidate);

}
