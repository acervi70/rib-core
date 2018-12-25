package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class BooleanParam extends CoreJdbcParam<Boolean> {

	public BooleanParam(int index, Boolean value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		ps.setBoolean(getIndex(), getValue().booleanValue());
	}
}
