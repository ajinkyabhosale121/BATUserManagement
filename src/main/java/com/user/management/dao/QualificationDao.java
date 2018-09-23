package com.user.management.dao;

import java.util.List;

import com.user.management.model.Qualification;

public interface QualificationDao {
	
	Qualification findById(long id);
	
	void saveQualification(Qualification qualification);
	
	void deleteQualificationById(long id);
	
	List<Qualification> findAllQualifications();
	
	void updateQualification(Qualification qualification);
	

}
