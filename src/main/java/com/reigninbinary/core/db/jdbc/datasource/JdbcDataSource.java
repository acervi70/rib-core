package com.reigninbinary.core.db.jdbc.datasource;

import java.lang.reflect.InvocationTargetException;

import javax.sql.DataSource;

import com.reigninbinary.core.CoreConfig;
import com.reigninbinary.core.db.CoreDatabaseException;


public class JdbcDataSource implements JdbcDataSourceProvider {
	
	@Override
	public DataSource getProviderDataSource() throws CoreDatabaseException {
		
		if (null == dataSourceProvider) {
			throw providerException;
		}
		return dataSourceProvider.getProviderDataSource();
	}
		
	public static JdbcDataSource getInstance() {
		
		return JdbcDataSourceInstance.INSTANCE;
	}

	private static class JdbcDataSourceInstance {
		
		private static JdbcDataSource INSTANCE = new JdbcDataSource();
	}

	private static CoreDatabaseException providerException = null; 
	private static JdbcDataSourceProvider dataSourceProvider = null;
	
	private JdbcDataSource() {
		
		try {
			dataSourceProvider = (JdbcDataSourceProvider) Class
					.forName(getDataSourceProvider())
					.getDeclaredConstructor().newInstance();
		} 
		catch (InstantiationException | IllegalAccessException | 
				IllegalArgumentException | InvocationTargetException | 
				NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			
			providerException = new CoreDatabaseException(e);
		}
	}
	
	private static String getDataSourceProvider() {
		
		final String JDBC_DATASOURCE = "JDBC_DATASOURCE";
		
		return CoreConfig.getConfigParam(JDBC_DATASOURCE, null);
	}
}
