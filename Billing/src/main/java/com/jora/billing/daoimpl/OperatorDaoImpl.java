package com.jora.billing.daoimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.SQLErrorCodes;

import com.jora.billing.dao.OperatorDao;
import com.jora.billing.query.OperatorQuery;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class OperatorDaoImpl implements OperatorDao {
	private final OperatorQuery operatorQuery;

	@Autowired
	private HikariDataSource directoryDataSource;

	public OperatorDaoImpl(OperatorQuery operatorQuery) {
		this.operatorQuery = operatorQuery;
	}

	@Override
	public List<Map<String, Object>> getOperatorDetails() throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(directoryDataSource);
			return jdbcTemplate.queryForList(operatorQuery.getOperatorDetails());
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}

			throw e;
		}
	}

	@Override
	public Map<String, Object> checkOperator(int operatorCode) throws Exception {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(directoryDataSource);
			return jdbcTemplate.queryForMap(operatorQuery.checkOperator(), new Object[] { operatorCode });
		} catch (EmptyResultDataAccessException e) {
			throw new Exception("Invalid Operator...!");
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}
			throw e;
		}
	}

}
