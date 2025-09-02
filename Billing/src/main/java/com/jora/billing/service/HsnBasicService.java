package com.jora.billing.service;

import java.util.Map;

public interface HsnBasicService {

	boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception;

	String chkHsnCode(Map<String, Object> mapHsn) throws Exception;

}
