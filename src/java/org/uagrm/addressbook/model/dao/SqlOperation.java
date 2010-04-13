package org.uagrm.addressbook.model.dao;

import org.uagrm.addressbook.util.Configuration;

public enum SqlOperation {

	SQL_INSERT("sql.insert"), SQL_UPDATE_ALL("sql.update_all"), SQL_UPDATE("sql.update"), SQL_SELECT_ALL("sql.select_all"), SQL_SELECT("sql.select"), SQL_SELECT_LAST_ID(
			"sql.select.last_id"), SQL_DELETE_ALL("sql.delete_all"), SQL_DELETE("sql.delete");

	private final String configKey;

	private SqlOperation(String configKey) {
		this.configKey = configKey;
	}

	public String getQuery() {
		return Configuration.getConfigValue(configKey).trim();
	}

}
