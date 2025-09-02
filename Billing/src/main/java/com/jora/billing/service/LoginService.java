package com.jora.billing.service;

import java.util.List;
import java.util.Map;

public interface LoginService {

	List<Map<String, Object>> getOperator() throws Exception;

	Map<String, Object> checkOperator(int operatorCode) throws Exception;

}
