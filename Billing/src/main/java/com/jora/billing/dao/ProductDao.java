package com.jora.billing.dao;

import java.util.Map;

public interface ProductDao {

	boolean save(Map<String, Object> mapProduct) throws Exception;

}
