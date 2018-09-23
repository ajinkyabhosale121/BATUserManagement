package com.user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.management.dao.QualificationDao;
import com.user.management.model.Qualification;

@Service("qualificationService")
@Transactional
public class QualificationServiceImpl implements QualificationService {

	@Autowired
    private QualificationDao dao;
	
	@Override
	public Qualification findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void saveQualification(Qualification qualification) {
		dao.saveQualification(qualification);
	}

	@Override
	public void deleteQualificationById(long id) {
		dao.deleteQualificationById(id);
	}

	@Override
	public List<Qualification> findAllQualifications() {
		return dao.findAllQualifications();
	}

	@Override
	public void updateQualification(Qualification qualification) {
		dao.updateQualification(qualification);
	}

}
