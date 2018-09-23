package com.user.management.dao;

import java.util.List;

import com.user.management.model.ResultStatus;

public interface ResultStatusDao {
	
	ResultStatus findById(long id);
	
	void saveResultStatus(ResultStatus resultstatus);
	
	void deleteResultStatusById(long id);
	
	List<ResultStatus> findAllResultStatus();
	
	void updateResultStatus(ResultStatus resultstatus);

}
