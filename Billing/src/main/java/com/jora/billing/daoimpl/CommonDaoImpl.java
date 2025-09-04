package com.jora.billing.daoimpl;

import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jora.billing.dao.CommonDao;
import com.jora.billing.query.CommonQuery;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CommonDaoImpl implements CommonDao {

	private final HikariDataSource directoryDataSource;

	private final CommonQuery commonQuery;

	@Override
	public String getMaxNo(Map<String, Object> saveMap) throws Exception {
		try {
			return new NamedParameterJdbcTemplate(directoryDataSource).queryForObject(commonQuery.getMaxNo(), saveMap,
					String.class);
		} catch (EmptyResultDataAccessException e) {
			throw new Exception(
					String.format("No records found! \ncompanyTag : %s and companyflag : %s and table : %s",
							saveMap.get("companytag"), saveMap.get("companyflag"), saveMap.get("table")));
		} catch (Exception e) {
			throw new Exception(
					String.format("Multiple records found! \ncompanyTag : %s and companyflag : %s and table : %s",
							saveMap.get("companytag"), saveMap.get("companyflag"), saveMap.get("table")));
		}
	}

}
