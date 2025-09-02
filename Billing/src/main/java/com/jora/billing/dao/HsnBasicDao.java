package com.jora.billing.dao;

import java.util.Map;

public interface HsnBasicDao {

	boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception;

	String chkHsnCode(Map<String, Object> mapHsn) throws Exception;

}
