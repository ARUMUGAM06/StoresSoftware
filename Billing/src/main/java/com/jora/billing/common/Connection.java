package com.jora.billing.common;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class Connection {

	private DefaultTransactionDefinition defaultTransactionDefinition;
	private TransactionStatus transactionStatus = null;
	private PlatformTransactionManager transactionManager;

	public Connection(DataSource dataSource) {
		transactionManager = new DataSourceTransactionManager(dataSource);
		defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_READ_COMMITTED);
		transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);

	}

	public Connection(JdbcTemplate jdbcTemplate) {
		transactionManager = new DataSourceTransactionManager(jdbcTemplate.getDataSource());
		defaultTransactionDefinition = new DefaultTransactionDefinition();
		defaultTransactionDefinition.setIsolationLevel(DefaultTransactionDefinition.ISOLATION_READ_COMMITTED);
		transactionStatus = transactionManager.getTransaction(defaultTransactionDefinition);
	}

	public void commit() {
		if (transactionStatus != null) {
			transactionManager.commit(transactionStatus);
			transactionStatus = null;
		}
	}

	public void rollback() {
		if (transactionStatus != null) {
			transactionManager.rollback(transactionStatus);
			transactionStatus = null;
		}
	}

}
