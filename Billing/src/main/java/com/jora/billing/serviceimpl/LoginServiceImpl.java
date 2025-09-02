package com.jora.billing.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jora.billing.dao.OperatorDao;
import com.jora.billing.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	private final OperatorDao operatorDao;

	public LoginServiceImpl(OperatorDao operatorDao) {
		this.operatorDao = operatorDao;
	}

	@Override
	public List<Map<String, Object>> getOperator() throws Exception {
		return operatorDao.getOperatorDetails();
	}

	@Override
	public Map<String, Object> checkOperator(int operatorCode) throws Exception {
		return operatorDao.checkOperator(operatorCode);
	}

}
