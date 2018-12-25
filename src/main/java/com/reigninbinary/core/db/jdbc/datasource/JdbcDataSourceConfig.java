package com.reigninbinary.core.db.jdbc.datasource;

import com.reigninbinary.core.CoreConfig;

public class JdbcDataSourceConfig {

	public static String getJdbcUrl() {
		
		final String JDBC_URL = "JDBC_URL";
		
		return CoreConfig.getConfigParam(JDBC_URL, null);
	}

	public static String getJdbcUser() {
		
		final String JDBC_USER = "JDBC_USER";
		
		return CoreConfig.getConfigParam(JDBC_USER, null);
	}

	public static String getJdbcPassword() {
		
		final String JDBC_PASSWORD = "JDBC_PASSWORD";
		
		return CoreConfig.getConfigParam(JDBC_PASSWORD, null);
	}
	
	public static String getJdbcDriver() {
		
		final String JDBC_DRIVER = "JDBC_DRIVER";
		
		return CoreConfig.getConfigParam(JDBC_DRIVER, null);
	}
}
