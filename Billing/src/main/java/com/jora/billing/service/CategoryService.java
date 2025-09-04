package com.jora.billing.service;

import java.util.List;
import java.util.Map;

public interface CategoryService {

	boolean saveCategory(Map<String, Object> saveMap) throws Exception;

	List<Map<String, Object>> view() throws Exception;

	boolean update(Map<String, Object> saveMap) throws Exception;

}
