package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DoubleParam extends CoreJdbcParam<Double> {

	public DoubleParam(int index, Double value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		ps.setDouble(getIndex(), getValue().doubleValue());
	}
}
