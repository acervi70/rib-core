package com.reigninbinary.core.db.jdbc.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.reigninbinary.core.CoreLogging;
import com.reigninbinary.core.db.CoreDatabaseException;
import com.reigninbinary.core.db.jdbc.datasource.JdbcDataSource;
import com.reigninbinary.core.db.jdbc.mappers.ResultSetMapper;
import com.reigninbinary.core.db.jdbc.params.PreparedStatementParam;


public class CoreJdbcQuery {

	public static String getErrorMessage(String sql) {
		
		return String.format("%s: %s", "BLOODSCRIBE SQL ERROR", sql);		
	}

	public static <T> List<T> executeQuery(String sql, 
			ResultSetMapper<T> mapper) throws CoreDatabaseException {
		
		DataSource ds = JdbcDataSource.getInstance().getProviderDataSource();

		Connection connection = null;
		Statement statement = null;
		
		try {
			connection = ds.getConnection();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			List<T> list = new ArrayList<>();
			while (rs.next()) {
				list.add(mapper.map(rs));
			}
			return list;
		} 
		catch (SQLException e) {
			throw new CoreDatabaseException(getErrorMessage(sql), e);
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					CoreLogging.logSevere(getErrorMessage(sql), e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					CoreLogging.logSevere(getErrorMessage(sql), e);
				}
			}
		}
	}

	public static <T> List<T> executeQuery(String sql, 
			List<PreparedStatementParam> params, 
			ResultSetMapper<T> mapper) throws CoreDatabaseException {
		
		DataSource ds = JdbcDataSource.getInstance().getProviderDataSource();
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			connection = ds.getConnection();
			statement = connection.prepareStatement(sql);
			
			for (PreparedStatementParam param : params) {

				param.setParam(statement);
			}
			
			ResultSet rs = statement.executeQuery();
			List<T> list = new ArrayList<>();
			while (rs.next()) {
				list.add(mapper.map(rs));
			}
			return list;
		} 
		catch (SQLException e) {
			throw new CoreDatabaseException(getErrorMessage(sql), e);
		}
		finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					CoreLogging.logSevere(getErrorMessage(sql), e);
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					CoreLogging.logSevere(getErrorMessage(sql), e);
				}
			}
		}
	}
}
