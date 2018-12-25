package com.reigninbinary.core.db.jdbc.datasource;

import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import com.reigninbinary.core.db.CoreDatabaseException;


public class BasicJdbcDataSourceProvider implements DataSource, JdbcDataSourceProvider {
	
	private CoreDatabaseException exception;
	private Driver driver;
		
	public BasicJdbcDataSourceProvider() {
		
		try {
			driver = (Driver) Class
					.forName(JdbcDataSourceConfig.getJdbcDriver())
					.getDeclaredConstructor().newInstance();
			
			DriverManager.registerDriver(driver);
		} 
		catch (InstantiationException | IllegalAccessException | IllegalArgumentException | 
				InvocationTargetException| NoSuchMethodException | SecurityException | 
				ClassNotFoundException | SQLException e) {
			
			driver = null;
			exception = new CoreDatabaseException(e);
		}
	}

	@Override
	public DataSource getProviderDataSource() throws CoreDatabaseException {
		
		if (driver == null) {
			throw exception;
		}
		return this;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		
		return driver.getParentLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		
		// TODO: unimplemented
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {

		// TODO: unimplemented
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		
		final String USER = "user";
		final String PASS = "password";
		
		Properties info = new Properties();
		info.setProperty(USER, JdbcDataSourceConfig.getJdbcUser());
		info.setProperty(PASS, JdbcDataSourceConfig.getJdbcPassword());
        
		return driver.connect(JdbcDataSourceConfig.getJdbcUrl(), info);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		
		String url = JdbcDataSourceConfig.getJdbcUrl();
		
		return DriverManager.getConnection(url, username, password);
	}

	@Override
	public PrintWriter getLogWriter() throws SQLException {

		return DriverManager.getLogWriter();
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		
		DriverManager.setLogWriter(out);
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

		DriverManager.setLoginTimeout(seconds);
	}

	@Override
	public int getLoginTimeout() throws SQLException {

		return DriverManager.getLoginTimeout();
	}
}
