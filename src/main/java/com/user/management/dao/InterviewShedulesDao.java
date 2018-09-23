package com.user.management.dao;

import java.util.List;

import com.user.management.model.InterviewShedules;

public interface InterviewShedulesDao {

	InterviewShedules findById(long id);
	
	void saveShedule(InterviewShedules shedule);
	
	void deleteSheduleById(long id);
	
	List<InterviewShedules> findAllShedules();
	
	void updateShedule(InterviewShedules shedule);
	
}
