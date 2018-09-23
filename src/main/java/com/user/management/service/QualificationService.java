package com.user.management.service;

import java.util.List;

import com.user.management.model.Qualification;

public interface QualificationService {
	
	Qualification findById(long id);
	
	void saveQualification(Qualification qualification);
	
	void deleteQualificationById(long id);
	
	List<Qualification> findAllQualifications();
	
	void updateQualification(Qualification qualification);
	

}
