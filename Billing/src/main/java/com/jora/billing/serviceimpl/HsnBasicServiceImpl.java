package com.jora.billing.serviceimpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jora.billing.common.Connection;
import com.jora.billing.dao.HsnBasicDao;
import com.jora.billing.service.HsnBasicService;
import com.zaxxer.hikari.HikariDataSource;

@Service
public class HsnBasicServiceImpl implements HsnBasicService {

	private final HsnBasicDao hsnBasicDao;

	@Autowired
	private HikariDataSource directoryDataSource;

	public HsnBasicServiceImpl(HsnBasicDao hsnBasicDao) {
		this.hsnBasicDao = hsnBasicDao;
	}

	@Override
	public boolean saveHsnBasic(Map<String, Object> mapHsn) throws Exception {
		Connection connection = null;
		try {
			connection = new Connection(directoryDataSource);

			hsnBasicDao.saveHsnBasic(mapHsn);

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
	public String chkHsnCode(Map<String, Object> mapHsn) throws Exception {
		return hsnBasicDao.chkHsnCode(mapHsn);
	}

}
