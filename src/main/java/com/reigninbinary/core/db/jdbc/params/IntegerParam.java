package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class IntegerParam extends CoreJdbcParam<Integer> {

	public IntegerParam(int index, Integer value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		ps.setInt(getIndex(), getValue().intValue());
	}
}
