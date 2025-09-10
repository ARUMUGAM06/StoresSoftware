package com.jora.billing.serviceimpl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.jora.billing.common.Connection;
import com.jora.billing.dao.CategoryDao;
import com.jora.billing.dao.CommonDao;
import com.jora.billing.service.CategoryService;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	
	private final CategoryDao categoryDao;
	
	private final CommonDao commonDao;

	private final HikariDataSource directoryDataSource;

	@Override
	public boolean saveCategory(Map<String, Object> saveMap) throws Exception {
		Connection connection = null;
		try {
			connection = new Connection(directoryDataSource);
			saveMap.put("catno", commonDao.getMaxNo(saveMap));
			categoryDao.save(saveMap);
			connection.commit();
			return true;
		} catch (Exception e) {
			if (null != connection) {
				connection.rollback();
			}
			throw e;
		}
	}

	@Override
	public List<Map<String, Object>> view() throws Exception {
		return categoryDao.view();
	}

	@Override
	public boolean update(Map<String, Object> saveMap) throws Exception {
		Connection connection = null;
		try {
			connection = new Connection(directoryDataSource);
			categoryDao.update(saveMap);
			connection.commit();
			return true;
		} catch (Exception e) {
			if (null != connection) {
				connection.rollback();
			}
			throw e;
		}
	}
}
