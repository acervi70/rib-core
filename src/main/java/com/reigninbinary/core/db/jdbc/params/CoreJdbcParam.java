package com.reigninbinary.core.db.jdbc.params;

public abstract class CoreJdbcParam<T> implements PreparedStatementParam {
	
	private int index;
	private T value;

	public CoreJdbcParam(int index, T value) {
		
		setIndex(index);
		setValue(value);
	}
	
	protected int getIndex() {
		return index;
	}

	protected void setIndex(int index) {
		this.index = index;
	}

	protected T getValue() {
		return value;
	}

	protected void setValue(T value) {
		this.value = value;
	}	
}
