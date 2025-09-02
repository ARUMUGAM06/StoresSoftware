package com.jora.billing.dao;

import java.util.List;
import java.util.Map;

public interface HsnBasicDao {

	boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception;

	String chkHsnCode(Map<String, Object> mapHsn) throws Exception;

	List<Map<String, Object>> getActiveHsnBasics() throws Exception;

}
