package com.jora.billing.service;

import java.util.List;
import java.util.Map;

public interface HsnBasicService {

	boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception;

	String chkHsnCode(Map<String, Object> mapHsn) throws Exception;

	List<Map<String, Object>> getActiveHsnBasics() throws Exception;

}
