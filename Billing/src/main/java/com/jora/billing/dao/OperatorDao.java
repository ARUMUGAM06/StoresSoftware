package com.jora.billing.dao;

import java.util.List;
import java.util.Map;

public interface OperatorDao {

	List<Map<String, Object>> getOperatorDetails() throws Exception;

	Map<String, Object> checkOperator(int operatorCode) throws Exception;

}
