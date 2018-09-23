package com.user.management.dao;

import java.util.List;

import com.user.management.model.RecruitmentRequests;

public interface RecruitmentRequestDao {
	
	RecruitmentRequests findById(long id);
	
	void saveRequest(RecruitmentRequests request);
	
	void deleteRequestById(long id);
	
	List<RecruitmentRequests> findAllRequests();
	
	void updateRequest(RecruitmentRequests request);
}
