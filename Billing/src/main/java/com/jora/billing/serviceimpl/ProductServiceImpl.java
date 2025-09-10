package com.jora.billing.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jora.billing.common.Connection;
import com.jora.billing.dao.CommonDao;
import com.jora.billing.dao.ProductDao;
import com.jora.billing.service.ProductService;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductDao productDao;
	private final CommonDao commonDao;

	@Autowired
	private HikariDataSource directoryDataSource;

	@Override
	public String saveProduct(Map<String, Object> mapProduct) throws Exception {
		Connection connection = null;
		try {
			connection = new Connection(directoryDataSource);
			String proNo = commonDao.getMaxNo(mapProduct);
			mapProduct.put("prono", proNo);
			productDao.save(mapProduct);
			connection.commit();
			return proNo;
		} catch (Exception e) {
			if (null != connection) {
				connection.rollback();
			}
			throw e;
		}
	}

}
