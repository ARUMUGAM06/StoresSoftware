package com.jora.billing.dao;

import java.util.List;
import java.util.Map;

public interface CategoryDao {

	void save(Map<String, Object> saveMap) throws Exception;

	List<Map<String, Object>> loadCategory() throws Exception;

}
