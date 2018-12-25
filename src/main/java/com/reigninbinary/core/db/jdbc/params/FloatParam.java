package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;


public class FloatParam extends CoreJdbcParam<Float> {

	public FloatParam(int index, Float value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		ps.setFloat(getIndex(), getValue().floatValue());
	}
}
