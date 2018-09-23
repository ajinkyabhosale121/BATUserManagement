package com.user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.management.dao.InterviewShedulesDao;
import com.user.management.model.InterviewShedules;

@Service("interviewService")
@Transactional
public class InterviewShedulesServiceImpl implements InterviewShedulesService {

	@Autowired
    private InterviewShedulesDao dao;
	
	@Override
	public InterviewShedules findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void saveShedule(InterviewShedules shedule) {
		dao.saveShedule(shedule);
	}

	@Override
	public void deleteSheduleById(long id) {
		dao.deleteSheduleById(id);		
	}

	@Override
	public List<InterviewShedules> findAllShedules() {
		return dao.findAllShedules();
	}

	@Override
	public void updateShedule(InterviewShedules shedule) {
		dao.updateShedule(shedule);
	}

}
