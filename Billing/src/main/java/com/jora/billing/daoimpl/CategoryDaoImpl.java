package com.jora.billing.daoimpl;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
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

	@Override
	public List<Map<String, Object>> view() throws Exception {
		return new JdbcTemplate(directoryDataSource).queryForList(categoryQuery.view());
	}

	@Override
	public void update(Map<String, Object> saveMap) throws Exception {
		new NamedParameterJdbcTemplate(directoryDataSource).update(categoryQuery.update(), saveMap); 
	}
}
