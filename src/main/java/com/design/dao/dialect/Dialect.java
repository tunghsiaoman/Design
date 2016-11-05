package com.design.dao.dialect;

public abstract class Dialect {
	
	public abstract String getCountSql(String sql);

	public String getLimitSql(String sql, int offset, int limit) {
		return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
	}

	public abstract String getLimitString(String sql, int offset, String offsetPlaceholder, int limit,
			String limitPlaceholder);
}
