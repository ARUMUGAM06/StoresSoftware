package com.jora.billing.daoimpl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
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
			throw new Exception(String.format("No records found! \ncompanyTag : %s and companyflag : %s and table : %s",
					saveMap.get("companytag"), saveMap.get("companyflag"), saveMap.get("table")));
		} catch (Exception e) {
			throw new Exception(
					String.format("Multiple records found! \ncompanyTag : %s and companyflag : %s and table : %s",
							saveMap.get("companytag"), saveMap.get("companyflag"), saveMap.get("table")));
		}
	}

	@Override
	public List<Map<String, Object>> loadFlagDetails(String type) throws Exception {
		try {
			return new JdbcTemplate(directoryDataSource).queryForList(commonQuery.loadFlagDetails(),
					new Object[] { type });
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}
			throw e;
		}
	}

	@Override
	public Map<String, Object> loadCategoryHsnDetails(int catNo) throws Exception {
		try {
			return new JdbcTemplate(directoryDataSource).queryForMap(commonQuery.loadCatgeoryHsnDetails(),
					new Object[] { catNo });
		} catch (Exception e) {
			if (e.getCause() instanceof SQLException) {
				throw new Exception(e.getCause().getMessage());
			}
			throw e;
		}
	}

}
