package com.user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.management.dao.RecruitmentRequestDao;
import com.user.management.model.RecruitmentRequests;

@Service("requestService")
@Transactional
public class RecruitmentRequestsServiceImpl implements RecruitmentRequestsService{

	@Autowired
    private RecruitmentRequestDao dao;
	
	@Override
	public RecruitmentRequests findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void saveRequest(RecruitmentRequests request) {
		dao.saveRequest(request);
	}

	@Override
	public void deleteRequestById(long id) {
		dao.deleteRequestById(id);
	}

	@Override
	public List<RecruitmentRequests> findAllRequests() {
		return dao.findAllRequests();
	}

	@Override
	public void updateRequest(RecruitmentRequests request) {
		dao.updateRequest(request);
	}

}
