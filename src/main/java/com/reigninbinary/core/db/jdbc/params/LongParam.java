package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LongParam extends CoreJdbcParam<Long> {

	public LongParam(int index, Long value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		ps.setLong(getIndex(), getValue().longValue());
	}
}
