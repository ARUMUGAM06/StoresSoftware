package com.jora.billing.connection;

import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class ConnectionConfig {

	private final BuildProperties buildProperties;

	@SuppressWarnings("deprecation")
	@Bean("directoryDataSource")
	@Primary
	public HikariDataSource getHoMasterConn() throws Exception {

		HikariDataSource dataSource = getDataSource(ApplicationConfig.serverName, ApplicationConfig.portNo,
				ApplicationConfig.password, ApplicationConfig.userName, ApplicationConfig.companyTag + "Directory");
		ApplicationConfig.companyFlag = new JdbcTemplate(dataSource).queryForObject(
				"select CompanyFlag from HoDetails where CompanyTag=?", new Object[] { ApplicationConfig.companyTag },
				String.class);

		if (ApplicationConfig.companyFlag.equalsIgnoreCase("")) {
			throw new Exception("CompanyFlag Details not Found...! \n Please Contact Jora ");
		}

		return dataSource;
	}

//	@Bean(name = "VbHoTranDbDataSource")
//	@DependsOn("VbHoMaindbDataSource")
//	public DataSource getHOTranDataSource() throws Exception {
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(getHoMasterConn());
//		StringBuilder builder = new StringBuilder("");
//		builder.append(
//				"select FileName from fileMain where cast(GETDATE() as date) between FromDATE and ToDATE and filetype='AC'\n");
//
//		FileMain filemain = jdbcTemplate.queryForObject(builder.toString(),
//				BeanPropertyRowMapper.newInstance(FileMain.class));
//		TransferIni.vbTrandbName = filemain.getFilename();
//
//		builder = new StringBuilder("");
//		builder.append(
//				"select  ISNULL(COSTCODE,'') AS COSTCODE,ISNULL(CostName,'') AS COSTNAME,isnull(CompId,'') as CompId,ISNULL(IPAddress,0) AS IPAddress,ISNULL(IPAddress1,0) AS IPAddress1,ISNULL(IPAddress2,0) AS IPAddress2\n");
//		builder.append(
//				",ISNULL(IPAddress3,0) AS IPAddress3,ISNULL(Portnumber,0) AS Portnumber,ISNULL(Username,'') AS Username,ISNULL(Pass,'') AS Pass  from CostCentre as c\n");
//		builder.append("where HOBOFLAG='H' and Active='Y'\n");
//
//		ServiceCommon.hoCostCentre = jdbcTemplate.queryForObject(builder.toString(),
//				BeanPropertyRowMapper.newInstance(CostCentre.class));
//
//		DataSource dataSource = getDataSource(TransferIni.serverName, TransferIni.portNo, TransferIni.passWord,
//				TransferIni.userName, filemain.getFilename());
//
//		return dataSource;
//
//	}

	public HikariDataSource getDataSource(String servername, String portno, String password, String userName,
			String dbName) {
		servername = "Sixface";
		password = "six";
		HikariConfig hikariConfig = null;
		try {
			hikariConfig = new HikariConfig();
			hikariConfig.setPoolName("Billing");
			hikariConfig.setJdbcUrl("jdbc:sqlserver://" + servername + ":" + portno + ";databaseName=" + dbName
					+ ";useSSL=false;encrypt=false; trustServerCertificate=true;autoReconnect=true;;applicationName="
					+ buildProperties.getName());
			hikariConfig.setUsername(userName);
			hikariConfig.setPassword(password);
			hikariConfig.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			hikariConfig.setConnectionInitSql("select 1");
			hikariConfig.setConnectionTestQuery("select 1");
			hikariConfig.setMaximumPoolSize(10);
			hikariConfig.setConnectionTimeout(30000);
			hikariConfig.setIdleTimeout(60000);
			hikariConfig.setMaxLifetime(60000);
			return new HikariDataSource(hikariConfig);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
