package org.uagrm.addressbook.model.dao.impl;

import org.apache.commons.lang.text.StrBuilder;
import org.uagrm.addressbook.model.dao.SqlOperation;

/**
 * @author Timoteo Ponce
 * 
 */
public class QueryBuilder {

	static private final QueryBuilder instance;
	static {
		instance = new QueryBuilder();
	}

	private final StrBuilder buffer;

	private QueryBuilder() {
		buffer = new StrBuilder();
	}

	public static QueryBuilder createQuery(SqlOperation operation) {
		return instance.init(operation);
	}

	public QueryBuilder init(SqlOperation operation) {
		buffer.clear();
		buffer.append(operation.getQuery());
		return this;
	}

	public QueryBuilder setVariable(final String var, final String value) {
		buffer.replaceAll(var, value);
		return this;
	}

	public QueryBuilder append(final String extra) {
		buffer.append(extra);
		return this;
	}

	public String getQuery() {
		return buffer.toString();
	}

}
