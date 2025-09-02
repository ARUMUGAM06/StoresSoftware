package com.jora.billing.daoimpl;

import java.util.Map;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jora.billing.dao.CategoryDao;
import com.jora.billing.query.CategoryQuery;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CategoryDaoImpl implements CategoryDao {

	private final HikariDataSource directoryDataSource;

	private final CategoryQuery categoryQuery;

	@Override
	public void save(Map<String, Object> saveMap) throws Exception {
		new NamedParameterJdbcTemplate(directoryDataSource).update(categoryQuery.save(), saveMap);
	}

}
