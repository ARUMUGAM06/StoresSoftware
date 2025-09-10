package com.jora.billing.service;

import java.util.List;
import java.util.Map;

public interface CommonService {

	List<Map<String, Object>> loadCategory() throws Exception;

	List<Map<String, Object>> loadFlagDetails(String type) throws Exception;

	Map<String, Object> loadCategoryHsnDetails(int catNo) throws Exception;

}
