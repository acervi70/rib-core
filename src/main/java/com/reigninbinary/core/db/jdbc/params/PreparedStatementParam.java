package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementParam {

	public void setParam(PreparedStatement ps) throws SQLException;

}
