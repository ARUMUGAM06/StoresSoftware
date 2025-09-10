package com.jora.billing.dao;

import java.util.List;
import java.util.Map;

public interface CommonDao {

	String getMaxNo(Map<String, Object> saveMap) throws Exception;

	List<Map<String, Object>> loadFlagDetails(String type) throws Exception;

	Map<String, Object> loadCategoryHsnDetails(int catNo) throws Exception;

}
