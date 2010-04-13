package org.uagrm.data;

import org.uagrm.addressbook.util.Configuration;

public enum DatabaseProperty {
	DB_CONNECTION_CLASS("db.connection.class"), DB_CONNECTION_LOCATION("db.connection.location"), DAO_PREFIX("dao.prefix");
	private final String configKey;

	private DatabaseProperty(String configKey) {
		this.configKey = configKey;
	}

	public String getValue() {
		return Configuration.getConfigValue(configKey).trim();
	}

}
