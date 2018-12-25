package com.reigninbinary.core.db.jdbc.params;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;


public class DateParam extends CoreJdbcParam<Date> {

	public DateParam(int index, Date value) {
		super(index, value);
	}

	@Override
	public void setParam(PreparedStatement ps) throws SQLException {
		
		// TODO: review possible alternate date and time classes
		ps.setDate(getIndex(), new java.sql.Date(getValue().getTime()));
	}
}
