package com.user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.management.dao.ResultStatusDao;
import com.user.management.model.ResultStatus;

@Service("resultStatusService")
@Transactional
public class ResultStatusServiceImpl implements ResultStatusService {

	@Autowired
    private ResultStatusDao dao;
	
	@Override
	public ResultStatus findById(long id) {
		return dao.findById(id);
	}

	@Override
	public void saveResultStatus(ResultStatus resultstatus) {
		dao.saveResultStatus(resultstatus);
	}

	@Override
	public void deleteResultStatusById(long id) {
		dao.deleteResultStatusById(id);
	}

	@Override
	public List<ResultStatus> findAllResultStatus() {
		return dao.findAllResultStatus();
	}

	@Override
	public void updateResultStatus(ResultStatus resultstatus) {
		dao.updateResultStatus(resultstatus);
	}

}
