package com.jora.billing.daoimpl;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jora.billing.dao.HsnBasicDao;
import com.jora.billing.query.HsnBasicQuery;
import com.zaxxer.hikari.HikariDataSource;

@Repository
public class HsnBasicDaoImpl implements HsnBasicDao {

	@Autowired
	private HikariDataSource directoryDataSource;

	private final HsnBasicQuery hsnBasicQuery;

	public HsnBasicDaoImpl(HsnBasicQuery hsnBasicQuery) {
		this.hsnBasicQuery = hsnBasicQuery;
	}

	@Override
	public boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception {
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(directoryDataSource);
			namedParameterJdbcTemplate.update(hsnBasicQuery.saveHsnBasic(), mapHsn);
			return true;
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}

			throw e;
		}
	}

	@Override
	public String chkHsnCode(Map<String, Object> mapHsn) throws Exception {
		try {
			NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(directoryDataSource);
			return namedParameterJdbcTemplate.queryForObject(hsnBasicQuery.chkHsnCode(), mapHsn, String.class);
		} catch (EmptyResultDataAccessException e) {
			return "";
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}

			throw e;
		}
	}

}
