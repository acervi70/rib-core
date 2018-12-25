package com.reigninbinary.core.db.jdbc.datasource;

import javax.sql.DataSource;

import com.reigninbinary.core.db.CoreDatabaseException;


public interface JdbcDataSourceProvider {

	public DataSource getProviderDataSource() throws CoreDatabaseException;
}
