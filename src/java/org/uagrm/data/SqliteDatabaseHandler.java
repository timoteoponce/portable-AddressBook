package org.uagrm.data;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.uagrm.addressbook.util.ConfigKeys;
import org.uagrm.addressbook.util.Configuration;

/**
 * @author Timoteo Ponce
 * 
 */
public class SqliteDatabaseHandler extends AbstractDatabaseHandler {
	private static final Logger LOG = Logger
			.getLogger(SqliteDatabaseHandler.class);	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.uagrm.data.AbstractDatabaseHandler#connect()
	 */
	@Override
	public void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			setConnection(DriverManager.getConnection("jdbc:sqlite:"
					+ Configuration
							.getConfigKey(ConfigKeys.DATASOURCE_LOCATION)));
		} catch (SQLException e) {
			LOG.error("Database error", e);
		} catch (ClassNotFoundException e) {
			LOG.error("JDBC definition not found", e);
		}
	}	

}
