package com.jora.billing.daoimpl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.jora.billing.dao.ProductDao;
import com.jora.billing.query.ProductQuery;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ProductDaoImpl implements ProductDao {
	private final ProductQuery productQuery;
	
	

	@Override
	public boolean save(Map<String, Object> mapProduct) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
