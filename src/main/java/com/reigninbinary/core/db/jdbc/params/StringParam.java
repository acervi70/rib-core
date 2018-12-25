package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class StringParam extends CoreJdbcParam<String> {

	public StringParam(int index, String value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		ps.setString(getIndex(), getValue().toString());
	}
}
